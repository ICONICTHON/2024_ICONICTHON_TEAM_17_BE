package Iconicthon.moadream.domain.Credit.service;

import Iconicthon.moadream.domain.Credit.domain.Language;
import Iconicthon.moadream.domain.Credit.domain.Lecture;
import Iconicthon.moadream.domain.Credit.domain.Schedule;
import Iconicthon.moadream.domain.Credit.domain.ScheduleLecture;
import Iconicthon.moadream.domain.Credit.dto.request.LectureRequest;
import Iconicthon.moadream.domain.Credit.dto.request.ScheduleRequest;
import Iconicthon.moadream.domain.Credit.dto.response.*;
import Iconicthon.moadream.domain.Credit.repository.LanguageRepository;
import Iconicthon.moadream.domain.Credit.repository.LectureRepository;
import Iconicthon.moadream.domain.Credit.repository.ScheduleLectureRepository;
import Iconicthon.moadream.domain.Credit.repository.ScheduleRepository;
import Iconicthon.moadream.domain.user.domain.User;
import Iconicthon.moadream.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final LanguageRepository languageRepository;
    private final LectureRepository lectureRepository;
    private final ScheduleLectureRepository scheduleLectureRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public CreditResponse getCredits(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        List<Schedule> schedules = scheduleRepository.findByUser(user).orElseThrow(IllegalAccessError::new);

        float totalCredits = schedules.stream()
                .flatMap(schedule -> schedule.getScheduleLectures().stream())
                .map(ScheduleLecture::getLecture)
                .map(Lecture::getCredit)
                .reduce(0, Integer::sum);

        float totalGrades = schedules.stream()
                .flatMap(schedule -> schedule.getScheduleLectures().stream())
                .map(scheduleLecture -> scheduleLecture.getLecture().getCredit() * (scheduleLecture.getGrade() != null ? scheduleLecture.getGrade() : 0)) // 학점 * 평점
                .reduce(0f, Float::sum); // 총 평점 합계

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
                .averageGrade(totalGrades/totalCredits)
                .englishCount(schedules.stream()
                        .flatMap(schedule -> schedule.getScheduleLectures().stream())
                        .map(ScheduleLecture::getLecture)
                        .filter(Lecture::getEnglish)
                        .count())
                .build();
        return creditResponse;
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

    public EnglishResponse getEnglish(Long userId){
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);

        Language language = languageRepository.findByUser(user).orElseThrow(IllegalAccessError::new);
        List<Schedule> schedules = scheduleRepository.findByUser(user).orElseThrow(IllegalAccessError::new);

        EnglishResponse englishResponse = EnglishResponse.builder()
                .grade(language.getGrade())
                .type(language.getType())
                .lectureListResponse(
                        LectureListResponse.builder()
                                .lectureResponses(
                                        schedules.stream()
                                                .flatMap(schedule -> schedule.getScheduleLectures().stream())
                                                .map(ScheduleLecture::getLecture)
                                                .filter(Lecture::getEnglish)
                                                .map(lecture -> LectureResponse.builder() // lecture 변수 선언
                                                        .name(lecture.getName())
                                                        .credit(lecture.getCredit())
                                                        .build())
                                                .collect(Collectors.toList())
                                ).build()
                ).build();

        return englishResponse;
    }

    public LectureListResponse getLiberal(Long userId, Integer type, Boolean major){
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);

        Language language = languageRepository.findByUser(user).orElseThrow(IllegalAccessError::new);
        List<Schedule> schedules = scheduleRepository.findByUser(user).orElseThrow(IllegalAccessError::new);


        LectureListResponse lectureListResponse = LectureListResponse.builder()
                .lectureResponses(
                        schedules.stream()
                                .flatMap(schedule -> schedule.getScheduleLectures().stream())
                                .map(ScheduleLecture::getLecture)
                                .filter(lecture -> Objects.equals(lecture.getType(), type) && Objects.equals(lecture.getMajor(), major))
                                .map(lecture -> LectureResponse.builder() // lecture 변수 선언
                                        .name(lecture.getName())
                                        .credit(lecture.getCredit())
                                        .build())
                                .collect(Collectors.toList())).build();
        return lectureListResponse;
    }
}
