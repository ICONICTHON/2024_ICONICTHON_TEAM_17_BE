package Iconicthon.moadream.domain.Credit.controller;

import Iconicthon.moadream.domain.Credit.dto.request.CreditRequest;
import Iconicthon.moadream.domain.Credit.dto.response.CreditResponse;
import Iconicthon.moadream.domain.Credit.dto.response.EnglishResponse;
import Iconicthon.moadream.domain.Credit.dto.response.LectureListResponse;
import Iconicthon.moadream.domain.Credit.dto.response.TermCreditResponse;
import Iconicthon.moadream.domain.Credit.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit")
public class CreditController {

    private final CreditService creditService;
    @GetMapping("/{userId}/getCredit")
    public CreditResponse getCredit(@PathVariable Long userId) {
        return creditService.getCredits(userId);
    }

    @GetMapping("/{userId}/getEnglish")
    public EnglishResponse getEnglish(@PathVariable Long userId) {
        return creditService.getEnglish(userId);
    }

    @GetMapping("/{userId}/getLecture")
    public LectureListResponse getLiberal(@PathVariable Long userId, @RequestBody CreditRequest creditRequest) {
        return creditService.getLiberal(userId, creditRequest.type(), creditRequest.major());
    }


    @GetMapping("/{scheduleId}/getTermCredit")
    public TermCreditResponse getTermCredit(@PathVariable Long scheduleId) {
        return creditService.getTermCredit(scheduleId);
    }



}
