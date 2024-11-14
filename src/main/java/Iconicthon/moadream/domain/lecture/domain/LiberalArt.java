package Iconicthon.moadream.domain.lecture.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "liberal_art")
public class LiberalArt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "course_number")
    private String courseNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "professor")
    private String professor;

    @Column(name = "start_at")
    private LocalTime startAt;

    @Column(name = "end_at")
    private LocalTime endAt;

    @Column(name = "day")
    private Integer day;

    @Column(name = "credit")
    private Integer credit;

    @Column(name = "grade")
    private Float grade;

    @Column(name = "type")
    private Integer type;

    @Column(name = "english")
    private Boolean english;
}
