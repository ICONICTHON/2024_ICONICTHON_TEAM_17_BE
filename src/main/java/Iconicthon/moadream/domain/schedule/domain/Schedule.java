package Iconicthon.moadream.domain.schedule.domain;

import Iconicthon.moadream.domain.lecture.domain.Major;
import Iconicthon.moadream.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "term")
    private Integer term;

    @Column(name = "term_credit")
    private Long termCredit;

    @Column(name = "term_grade")
    private Float ermGrade;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Major> majors = new ArrayList<>();

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Major> liberalArts = new ArrayList<>();
}
