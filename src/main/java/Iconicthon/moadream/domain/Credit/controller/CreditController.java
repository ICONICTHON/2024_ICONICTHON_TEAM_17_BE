package Iconicthon.moadream.domain.Credit.controller;

import Iconicthon.moadream.domain.Credit.dto.response.CreditResponse;
import Iconicthon.moadream.domain.Credit.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CreditController {

    private final CreditService creditService;
    @GetMapping("/{userId}/credit")
    public CreditResponse getCredit(@PathVariable Long userId) {
        return creditService.getCredits(userId);
    }



}
