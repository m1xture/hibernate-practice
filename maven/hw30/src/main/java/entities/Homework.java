package entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "homework")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString(exclude = "student")
@Getter
@Setter
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "mark")
    private int mark;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;
}

