package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity //for hibernate
@Table //for table in db
public class Student {
    @Id
    @SequenceGenerator(
        name="student_sequence",
        sequenceName="student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )

    private int id;
    private String name;
    private String email;
    private LocalDate DOB;
    @Transient
    private Integer age;


    public Student() {
    }

    public Student(int id,String name,String email, LocalDate DOB){

        this.id = id;
        this.name = name;
        this.email = email;
        this.DOB = DOB;
        
    }
    public Student(String name,String email, LocalDate DOB){
        this.name = name;
        this.email = email;
        this.DOB = DOB;
        
    }

    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return this.DOB;
    }

    public void setDob(LocalDate DOB) {
        this.DOB = DOB;
    }

    public Integer getAge() {
        return Period.between(DOB,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
