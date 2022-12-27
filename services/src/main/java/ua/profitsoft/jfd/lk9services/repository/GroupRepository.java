package ua.profitsoft.jfd.lk9services.repository;

import org.springframework.stereotype.Repository;
import ua.profitsoft.jfd.lk9services.data.GroupData;

import javax.annotation.PostConstruct;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;

@Repository
public class GroupRepository {

  private NavigableMap<Integer, GroupData> data = new ConcurrentSkipListMap<>();

  @PostConstruct
  public void init() {
    data.put(1, new GroupData(1, "Group1", true));
    data.put(2, new GroupData(2, "Group2", true));
    data.put(3, new GroupData(3, "Group3", true));
    data.put(4, new GroupData(4, "Group4", false));
    data.put(5, new GroupData(5, "Group5", true));
  }

  public Optional<GroupData> get(int id) {
    return Optional.ofNullable(data.get(id));
  }

}
