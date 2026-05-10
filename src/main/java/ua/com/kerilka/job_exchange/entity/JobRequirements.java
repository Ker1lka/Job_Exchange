package ua.com.kerilka.job_exchange.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "job_requirements")
public class JobRequirements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double minSalary;
    private String preferredLocation;
    private boolean housingRequired;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profiles profile;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;
}
