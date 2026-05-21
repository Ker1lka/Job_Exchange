package ua.com.kerilka.job_exchange.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "profile_has_profession")
public class ProfileHasProfession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skillLevel;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Candidates profile;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;


}
