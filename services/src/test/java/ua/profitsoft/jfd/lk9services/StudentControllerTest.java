package ua.profitsoft.jfd.lk9services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.profitsoft.jfd.lk9services.data.StudentData;
import ua.profitsoft.jfd.lk9services.dto.RestResponse;
import ua.profitsoft.jfd.lk9services.repository.StudentRepository;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = Lk9servicesApplication.class)
@AutoConfigureMockMvc
public class StudentControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @AfterEach
  public void afterEach() {
    studentRepository.deleteAll();
  }

  @Test
  public void testCreateStudent() throws Exception {
    String name = "Oleksandr";
    String surname = "Rudenko";
    int groupId = 2;
    String body = """
          {
              "name": "%s",
              "surname": "%s",
              "birthDate": "2001-12-12",
              "groupId": %d
          }               
        """.formatted(name, surname, groupId);
    MvcResult mvcResult = mvc.perform(post("/api/students")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
        )
        .andExpect(status().isCreated())
        .andReturn();

    RestResponse response = parseResponse(mvcResult, RestResponse.class);
    int studentId = Integer.parseInt(response.getResult());
    assertThat(studentId).isGreaterThanOrEqualTo(1);

    StudentData student = studentRepository.get(studentId).orElse(null);
    assertThat(student).isNotNull();
    assertThat(student.getName()).isEqualTo(name);
    assertThat(student.getSurname()).isEqualTo(surname);
    assertThat(student.getGroup().getId()).isEqualTo(groupId);
  }

  @Test
  public void testCreateStudent_validation() throws Exception {
    mvc.perform(post("/api/students")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}")
        )
        .andExpect(status().isBadRequest());
  }

  private <T>T parseResponse(MvcResult mvcResult, Class<T> c) {
    try {
      return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), c);
    } catch (JsonProcessingException | UnsupportedEncodingException e) {
      throw new RuntimeException("Error parsing json", e);
    }
  }

}
