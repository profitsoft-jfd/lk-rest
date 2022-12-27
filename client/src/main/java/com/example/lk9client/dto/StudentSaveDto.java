package com.example.lk9client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Getter
@Builder
@Jacksonized
public class StudentSaveDto {

  private String name;

  private String surname;

  private LocalDate birthDate;

  private Integer groupId;

}
