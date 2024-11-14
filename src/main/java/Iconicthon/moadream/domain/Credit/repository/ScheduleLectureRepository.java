package Iconicthon.moadream.domain.Credit.repository;

import Iconicthon.moadream.domain.Credit.domain.Schedule;
import Iconicthon.moadream.domain.Credit.domain.ScheduleLecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleLectureRepository extends JpaRepository<ScheduleLecture, Long> {
    Optional<List<ScheduleLecture>> findBySchedule(Schedule schedule);
}
