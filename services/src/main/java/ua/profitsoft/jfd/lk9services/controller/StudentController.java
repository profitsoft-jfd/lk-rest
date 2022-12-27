package ua.profitsoft.jfd.lk9services.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.profitsoft.jfd.lk9services.dto.RestResponse;
import ua.profitsoft.jfd.lk9services.dto.StudentDetailsDto;
import ua.profitsoft.jfd.lk9services.dto.StudentInfoDto;
import ua.profitsoft.jfd.lk9services.dto.StudentQueryDto;
import ua.profitsoft.jfd.lk9services.dto.StudentSaveDto;
import ua.profitsoft.jfd.lk9services.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RestResponse createStudent(@Valid @RequestBody StudentSaveDto dto) {
    int id = studentService.createStudent(dto);
    return new RestResponse(String.valueOf(id));
  }

  @GetMapping("/{id}")
  public StudentDetailsDto getStudent(@PathVariable int id) {
    return studentService.getStudent(id);
  }

  @PutMapping("/{id}")
  public RestResponse updateStudent(@Valid @PathVariable int id, @RequestBody StudentSaveDto dto) {
    studentService.updateStudent(id, dto);
    return new RestResponse("OK");
  }

  @PostMapping("_search")
  public List<StudentInfoDto> search(@RequestBody StudentQueryDto query) {
    return studentService.search(query);
  }

}
