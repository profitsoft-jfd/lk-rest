package ua.profitsoft.jfd.lk9services.repository;

import org.springframework.stereotype.Repository;
import ua.profitsoft.jfd.lk9services.data.StudentData;
import ua.profitsoft.jfd.lk9services.dto.StudentQueryDto;

import java.util.List;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class StudentRepository {

  private NavigableMap<Integer, StudentData> data = new ConcurrentSkipListMap<>();

  private AtomicInteger idGenerator = new AtomicInteger();

  public int save(StudentData student) {
    if (student.getId() < 1) {
      student.setId(idGenerator.incrementAndGet());
    }
    data.put(student.getId(), student);
    return student.getId();
  }

  public Optional<StudentData> get(int id) {
    return Optional.ofNullable(data.get(id));
  }

  public List<StudentData> search(StudentQueryDto query) {
    return data.values().stream()
        .filter(stud -> isMatch(stud, query))
        .toList();
  }

  private boolean isMatch(StudentData student, StudentQueryDto query) {
    boolean result = true;
    if (query.getName() != null && !query.getName().isBlank()) {
      result &= query.getName().equalsIgnoreCase(student.getName());
    }
    if (result && query.getGroupId() != null) {
      result &= student.getGroup() != null && query.getGroupId().equals(student.getGroup().getId());
    }
    return result;
  }

  public boolean delete(int id) {
    return data.remove(id) != null;
  }

  public void deleteAll() {
    data.clear();
  }

}
