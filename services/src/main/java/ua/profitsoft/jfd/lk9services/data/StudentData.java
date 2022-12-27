package ua.profitsoft.jfd.lk9services.data;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class StudentData {

  private int id;

  private String name;

  private String surname;

  private LocalDate birthDate;

  private GroupData group;

  private Instant lastUpdateTime;

}
