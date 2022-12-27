package ua.profitsoft.jfd.lk9services.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class StudentInfoDto {

  private int id;

  private String fullName;

  private String groupName;

}
