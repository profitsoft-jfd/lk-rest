package com.example.lk9client.client;

import com.example.lk9client.dto.RestResponse;
import com.example.lk9client.dto.StudentSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class StudentsClient {

  public static final String ENDPOINT_STUDENTS_CREATE = "/students";
  private final RestTemplate restTemplate;

  @Value("${studentsService.baseUri:http://localhost:8080/api}")
  private String studentsServiceBaseUri;

  public int createStudent(StudentSaveDto dto) {
    ResponseEntity<RestResponse> response = restTemplate.exchange(
        studentsServiceBaseUri + ENDPOINT_STUDENTS_CREATE,
        HttpMethod.POST,
        new HttpEntity<>(
            dto
        ),
        ParameterizedTypeReference.forType(RestResponse.class)
    );
    String id = response.getBody().getResult();
    return Integer.parseInt(id);
  }

}
