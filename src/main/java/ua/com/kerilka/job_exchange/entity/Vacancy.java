package ua.com.kerilka.job_exchange.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String position;
    private String workingConditions;
    private Double salary;
    private String requirements;
    private boolean housingProvided;
    private boolean isArchived;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Profiles author;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "vacancy")
    private List<ProfileHasVacancy> profileHasVacancies = new ArrayList<>();
}
