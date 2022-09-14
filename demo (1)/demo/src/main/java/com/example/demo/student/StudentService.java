package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


// @Service tells spring this is a class that will need to be instantiated at some point for use somewhere
@Service
public class StudentService{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
		// return List.of(
		// 	new Student(
		// 		1,
		// 		"Mariam",
		// 		"gmail.com",
		// 		LocalDate.of(2000,Month.APRIL,5),
		// 		21
		// 	)
		// );
            //returns list of students from DB
            return studentRepository.findAll();
    	}
	

	
	public Student findStudentByName(String name){
		return studentRepository.findStudentByName(name);
	}

	public void addNewStudent(Student student){
		
		Optional <Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

		if (studentByEmail.isPresent()){
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId){
		studentRepository.existsById(studentId);
		if(!exists){
			throw new IllegalStateException("student with id " + studentId + "does not exist");
		}
		studentRepository.deleteById(studentId);
	}
	@Transactional
	public void updateStudent(Long studentId,String name,String email){
		Student student = studentRepository.findById(studentId)
			.orElseThrow(()-> new IllegalStateException(
				"student with id " + studentId + "does not exist");
			)

		if(name != null && !Objects.equals(student.getName(),name)){
			student.setName(name);
		}

		if(email != null && email.length() > 0 && 
			!Objects.equals(student.getEmail(),email)){

			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if(studentOptional.isPresent()){
				throw new IllegalSateException("email taken");
			}
			student.setEmail(email);
		}
	}
}