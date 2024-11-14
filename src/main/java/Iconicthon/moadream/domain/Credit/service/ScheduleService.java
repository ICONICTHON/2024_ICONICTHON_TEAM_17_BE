package Iconicthon.moadream.domain.Credit.service;

import Iconicthon.moadream.domain.Credit.domain.Lecture;
import Iconicthon.moadream.domain.Credit.domain.Schedule;
import Iconicthon.moadream.domain.Credit.domain.ScheduleLecture;
import Iconicthon.moadream.domain.Credit.dto.request.LectureRequest;
import Iconicthon.moadream.domain.Credit.dto.request.ScheduleRequest;
import Iconicthon.moadream.domain.Credit.repository.LectureRepository;
import Iconicthon.moadream.domain.Credit.repository.ScheduleLectureRepository;
import Iconicthon.moadream.domain.Credit.repository.ScheduleRepository;
import Iconicthon.moadream.domain.user.domain.User;
import Iconicthon.moadream.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final LectureRepository lectureRepository;
    private final ScheduleLectureRepository scheduleLectureRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public void postSchedule(ScheduleRequest request){
        User user = userRepository.findById(request.userId()).orElseThrow(IllegalAccessError::new);
        scheduleRepository.save(new Schedule(request.name(), user));
    }

    public void postLecture (LectureRequest request){
        Schedule schedule = scheduleRepository.findById(request.scheduleId()).orElseThrow(IllegalAccessError::new);
        Lecture lecture = lectureRepository.findById(request.lectureId()).orElseThrow(IllegalAccessError::new);

        scheduleLectureRepository.save(new ScheduleLecture(schedule, lecture));
    }
}
