package Iconicthon.moadream.domain.Credit.repository;


import Iconicthon.moadream.domain.Credit.domain.Language;
import Iconicthon.moadream.domain.Credit.domain.Schedule;
import Iconicthon.moadream.domain.Credit.domain.ScheduleLecture;
import Iconicthon.moadream.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByUser(User user);
}
