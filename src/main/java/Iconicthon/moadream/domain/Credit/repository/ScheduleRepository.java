package Iconicthon.moadream.domain.Credit.repository;

import Iconicthon.moadream.domain.Credit.domain.Schedule;
import Iconicthon.moadream.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    Optional<List<Schedule>> findByUser(User user);
}
