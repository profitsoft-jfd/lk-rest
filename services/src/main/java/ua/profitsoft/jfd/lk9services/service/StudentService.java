package ua.profitsoft.jfd.lk9services.service;

import ua.profitsoft.jfd.lk9services.dto.StudentDetailsDto;
import ua.profitsoft.jfd.lk9services.dto.StudentInfoDto;
import ua.profitsoft.jfd.lk9services.dto.StudentQueryDto;
import ua.profitsoft.jfd.lk9services.dto.StudentSaveDto;

import java.util.List;

public interface StudentService {

  int createStudent(StudentSaveDto dto);

  StudentDetailsDto getStudent(int id);

  void updateStudent(int id, StudentSaveDto dto);

  List<StudentInfoDto> search(StudentQueryDto query);

}
