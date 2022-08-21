package com.example.demo;

import com.example.demo.data.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args ->{

            Student nadeen = new Student(
                    "nadeen",
                    "Alaraj",
                    "Email.aa@da",
                    24
            );
            studentRepository.save(nadeen);
        };
    }

}