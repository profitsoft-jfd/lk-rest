package com.example.lk9client;

import com.example.lk9client.client.StudentsClient;
import com.example.lk9client.dto.StudentSaveDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Lk9clientApplication implements CommandLineRunner {

  @Autowired
  private StudentsClient studentsClient;

  public static void main(String[] args) {
    SpringApplication.run(Lk9clientApplication.class, args);
  }

  @Override
  public void run(String... args) {
    StudentSaveDto student = StudentSaveDto.builder()
        .name("Ivan")
        .surname("Test")
        .groupId(2)
        .build();
    int studentId = studentsClient.createStudent(student);
    log.info("Created student with id {}", studentId);
  }

}
