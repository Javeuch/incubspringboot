	package com.backend.incubspringboot.student;
	
	import java.time.LocalDate;
	import java.time.Period;
	import java.util.Set;
	
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.JoinColumn;
	import javax.persistence.JoinTable;
	import javax.persistence.ManyToMany;
	import javax.persistence.SequenceGenerator;
	import javax.persistence.Table;
	import javax.persistence.Transient;
	
	import com.backend.incubspringboot.courses.Course;
	
	@Entity // pour Hibernate
	@Table // pour la BDD
	public class Student {
	@Id
	@SequenceGenerator(
				name = "student_sequence",
				sequenceName = "student_sequence",
				allocationSize = 1)
	@GeneratedValue(
				strategy = GenerationType.SEQUENCE, 
				generator = "student_sequence")
	
		private Long id;
		private String name;
		private String email;
		private LocalDate dob; // "date of birth"
		@Transient // On fait intervenir javax.persistence
		private Integer age;
	
		/* Mapping relationnel */
	@ManyToMany
	@JoinTable(
				name = "course_like",
				joinColumns = @JoinColumn(name = "student_id"),
				inverseJoinColumns = @JoinColumn(name = "course_id"))
	Set<Course> likedCourses;
	
		/* On génère 3 constructors différents */
	
	// Constructor vide par défaut
		public Student() {
	
		}
	
	// Constructor avec tous les attributs
		public Student(Long id, String name, String email, LocalDate dob) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.dob = dob;
		}
	
	//Constructor avec l'id en moins 
		public Student(String name, String email, LocalDate dob) {
			super();
			this.name = name;
			this.email = email;
			this.dob = dob;
		}
	
		/* On génère les accesseurs */
		public Long getId() {
			return id;
		}
	
		public void setId(Long id) {
			this.id = id;
		}
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		public Integer getAge() {
			return Period.between(this.dob, LocalDate.now()).getYears();
		}
	
		public void setAge(Integer age) {
			this.age = age;
		}
	
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}
	
		public LocalDate getDob() {
			return dob;
		}
	
		public void setDob(LocalDate dob) {
			this.dob = dob;
		}
	
		/* On génère la méthode toString() */
	
		@Override
		public String toString() {
			return "Student [" + "id=" + id + "," + "\n" + " name=" + name + "," + "\n" + " age=" + age + "," + "\n"
					+ " email=" + email + "," + "\n" + " dob=" + dob + "]";
		}
	
	}
