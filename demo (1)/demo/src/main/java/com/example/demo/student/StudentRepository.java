package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This interface is responsible for data access
/* We use this repo inside of student service to talk to the db */
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {


    public Student findStudentByName(String name);
    

    Optional<Student> findStudentByEmail(String email);
}



/*  


    These 2 statements do the same thing:
        * JPQL 
        * @Query("SELECT s FROM Student s WHERE s.email = ?1");
        * 
        * Optional<Student> findStudentByEmail(String email);
 */