package ua.com.kerilka.job_exchange.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String position;
    private String reasonForLeaving;
    private int experienceTime = getTimeOfExperience();
    private LocalDate startDate;
    private LocalDate endDate;


    public int getTimeOfExperience() {
        if (startDate == null || endDate == null) {
            return 0;
        }
        return Period.between(startDate, endDate).getYears();
    }


    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Candidates profile;
}
