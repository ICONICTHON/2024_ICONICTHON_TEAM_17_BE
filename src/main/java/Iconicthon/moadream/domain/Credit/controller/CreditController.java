package Iconicthon.moadream.domain.Credit.controller;

import Iconicthon.moadream.domain.Credit.dto.request.LectureRequest;
import Iconicthon.moadream.domain.Credit.dto.request.ScheduleRequest;
import Iconicthon.moadream.domain.Credit.dto.response.CreditResponse;
import Iconicthon.moadream.domain.Credit.dto.response.TermCreditResponse;
import Iconicthon.moadream.domain.Credit.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PatchExchange;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CreditController {

    private final CreditService creditService;
    @GetMapping("/{userId}/credit")
    public CreditResponse getCredit(@PathVariable Long userId) {
        return creditService.getCredits(userId);
    }

    @PostMapping("/postSchedule")
    public void postSchedule(@RequestBody ScheduleRequest request) {
        creditService.postSchedule(request);
    }

    @PostMapping("/postLecture")
    public void postLecture(@RequestBody LectureRequest request) {
        creditService.postLecture(request);
    }

    @GetMapping("/{scheduleId}/getTermCredit")
    public TermCreditResponse getTermCredit(@PathVariable Long scheduleId) {
        return creditService.getTermCredit(scheduleId);
    }


}
