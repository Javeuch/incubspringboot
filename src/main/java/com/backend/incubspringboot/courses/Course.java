package com.backend.incubspringboot.courses;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.backend.incubspringboot.student.Student;

@Entity // pour Hibernate
@Table // pour la BDD
public class Course {

@Id
@SequenceGenerator(
			name = "course_sequence", 
			sequenceName = "course_sequence", 
			allocationSize = 1)
@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "course_sequence")

	private Long id;
	private String subject;

/* Mapping relationnel */
@ManyToMany(mappedBy = "likedCourses")
	Set<Student> likes;

	public Course(Long id, String subject) {
		super();
		this.id = id;
		this.subject = subject;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", subject=" + subject + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, subject);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(id, other.id) && Objects.equals(subject, other.subject);
	}

}
