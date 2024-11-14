package Iconicthon.moadream.domain.Credit.service;

import Iconicthon.moadream.domain.Credit.domain.Lecture;
import Iconicthon.moadream.domain.Credit.dto.response.CreditResponse;
import Iconicthon.moadream.domain.Credit.repository.LectureRepository;
import Iconicthon.moadream.domain.Credit.repository.ScheduleLectureRepository;
import Iconicthon.moadream.domain.user.domain.User;
import Iconicthon.moadream.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final LectureRepository lectureRepository;
    private final ScheduleLectureRepository scheduleLectureRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public CreditResponse getCredit(Long userId){
        User user = userRepository.findById(userId).orElseThrow((IllegalAccessError::new));
    }
}
