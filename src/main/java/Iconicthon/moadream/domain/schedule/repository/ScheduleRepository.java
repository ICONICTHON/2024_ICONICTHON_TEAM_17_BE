package Iconicthon.moadream.domain.schedule.repository;

import Iconicthon.moadream.domain.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
