package Iconicthon.moadream.domain.Credit.domain;

import Iconicthon.moadream.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private Integer type;

    @Column(name = "grade")
    private Integer grade;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
