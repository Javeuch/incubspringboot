package com.backend.incubspringboot.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// @Component fait la mm chose mais pour la sémantique, c'est mieux
@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired // injection de dépendances dans le constructor pour instancier un
				// StudentRepository
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudent() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("email déjà pris !");
		}
		studentRepository.save(student);

	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if (!exists) {
			throw new IllegalStateException("Student avec l'id " + studentId + " n'existe pas !");
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(Long studentId, String name, String email) {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalStateException(" student avec l'id " + studentId + "n'existe pas !"));

		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}

		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if (studentOptional.isPresent()) {
				throw new IllegalStateException("Cet email est déjà pris !");
			}
			
			student.setEmail(email);
		}
	}
}
