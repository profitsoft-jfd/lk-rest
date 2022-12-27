package ua.profitsoft.jfd.lk9services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentQueryDto {

  private String name;

  private Integer groupId;

  private int from;

  private int size;

}
