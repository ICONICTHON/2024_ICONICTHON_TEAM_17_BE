package Iconicthon.moadream.domain.Credit.controller;

import Iconicthon.moadream.domain.Credit.dto.request.LectureRequest;
import Iconicthon.moadream.domain.Credit.dto.request.ScheduleRequest;
import Iconicthon.moadream.domain.Credit.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    @PostMapping("/postSchedule")
    public void postSchedule(@RequestBody ScheduleRequest request) {
        scheduleService.postSchedule(request);
    }

    @PostMapping("/postLecture")
    public void postLecture(@RequestBody LectureRequest request) {
        scheduleService.postLecture(request);
    }
}
