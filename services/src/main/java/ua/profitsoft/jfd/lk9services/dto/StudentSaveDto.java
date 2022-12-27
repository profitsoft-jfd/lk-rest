package ua.profitsoft.jfd.lk9services.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
@Jacksonized
public class StudentSaveDto {

  @NotBlank(message = "name is required")
  private String name;

  @NotBlank(message = "surname is required")
  private String surname;

  private LocalDate birthDate;

  @NotNull(message = "groupId is required")
  private Integer groupId;

}
