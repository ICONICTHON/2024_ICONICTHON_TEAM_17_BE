package Iconicthon.moadream.domain.Credit.service;

import Iconicthon.moadream.domain.Credit.domain.Lecture;
import Iconicthon.moadream.domain.Credit.domain.Schedule;
import Iconicthon.moadream.domain.Credit.domain.ScheduleLecture;
import Iconicthon.moadream.domain.Credit.dto.request.LectureRequest;
import Iconicthon.moadream.domain.Credit.dto.request.ScheduleRequest;
import Iconicthon.moadream.domain.Credit.dto.response.CreditResponse;
import Iconicthon.moadream.domain.Credit.dto.response.TermCreditResponse;
import Iconicthon.moadream.domain.Credit.repository.LectureRepository;
import Iconicthon.moadream.domain.Credit.repository.ScheduleLectureRepository;
import Iconicthon.moadream.domain.Credit.repository.ScheduleRepository;
import Iconicthon.moadream.domain.user.domain.User;
import Iconicthon.moadream.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final LectureRepository lectureRepository;
    private final ScheduleLectureRepository scheduleLectureRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public CreditResponse getCredits(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        List<Schedule> schedules = scheduleRepository.findByUser(user).orElseThrow(IllegalAccessError::new);

        CreditResponse creditResponse = CreditResponse.builder()
                .commonLiberal(
                        schedules.stream()
                                .flatMap(schedule -> schedule.getScheduleLectures().stream()) // ScheduleLecture 리스트 평탄화
                                .map(ScheduleLecture::getLecture) // ScheduleLecture에서 Lecture 가져오기
                                .filter(lecture -> lecture.getType() == 1 && !lecture.getMajor())
                                .map(Lecture::getCredit) // Lecture의 credit 값 가져오기
                                .reduce(0, Integer::sum))
                .basicLiberal(schedules.stream()
                        .flatMap(schedule -> schedule.getScheduleLectures().stream())
                        .map(ScheduleLecture::getLecture)
                        .filter(lecture -> lecture.getType() == 2 && !lecture.getMajor())
                        .map(Lecture::getCredit)
                        .reduce(0, Integer::sum))
                .majorCommon(schedules.stream()
                        .flatMap(schedule -> schedule.getScheduleLectures().stream())
                        .map(ScheduleLecture::getLecture)
                        .filter(lecture -> lecture.getType() == 3 && lecture.getMajor())
                        .map(Lecture::getCredit)
                        .reduce(0, Integer::sum))
                .majorRequired(schedules.stream()
                        .flatMap(schedule -> schedule.getScheduleLectures().stream())
                        .map(ScheduleLecture::getLecture)
                        .filter(lecture -> lecture.getType() == 1 && lecture.getMajor())
                        .map(Lecture::getCredit)
                        .reduce(0, Integer::sum))
                .bsm(schedules.stream()
                        .flatMap(schedule -> schedule.getScheduleLectures().stream())
                        .map(ScheduleLecture::getLecture)
                        .filter(lecture -> (lecture.getType() == 3 || lecture.getType() == 4) && !lecture.getMajor())
                        .map(Lecture::getCredit)
                        .reduce(0, Integer::sum))
                .englishCount(schedules.stream()
                        .flatMap(schedule -> schedule.getScheduleLectures().stream())
                        .map(ScheduleLecture::getLecture)
                        .filter(Lecture::getEnglish)
                        .count())
                .build();
        return creditResponse;
    }

    public void postSchedule(ScheduleRequest request){
        User user = userRepository.findById(request.userId()).orElseThrow(IllegalAccessError::new);
        scheduleRepository.save(new Schedule(request.name(), user));
    }

    public void postLecture (LectureRequest request){
        Schedule schedule = scheduleRepository.findById(request.scheduleId()).orElseThrow(IllegalAccessError::new);
        Lecture lecture = lectureRepository.findById(request.lectureId()).orElseThrow(IllegalAccessError::new);

        scheduleLectureRepository.save(new ScheduleLecture(schedule, lecture));
    }

    public TermCreditResponse getTermCredit(Long scheduleId){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();
        List<ScheduleLecture> scheduleLectures = scheduleLectureRepository.findBySchedule(schedule).orElseThrow(IllegalAccessError::new);

        TermCreditResponse termCreditResponse = TermCreditResponse
                .builder()
                .termCredit(
                        scheduleLectures.stream()
                        .map(ScheduleLecture::getLecture) // ScheduleLecture에서 Lecture 가져오기
                        .map(Lecture::getCredit) // Lecture의 credit 값 가져오기
                        .reduce(0, Integer::sum)
                )
                .build();

        return termCreditResponse;
    }

}
