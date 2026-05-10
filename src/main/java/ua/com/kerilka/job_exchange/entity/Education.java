package ua.com.kerilka.job_exchange.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String institution;
    private String degree;
    private String specialization;
    private Integer graduationYear;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profiles profile;
}
