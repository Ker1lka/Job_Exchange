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
    private Double salary;
    private String requirements;
    private String conditions;
    private String housingConditions;
    private boolean isClosed;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "vacancy")
    public List<JobApplication> jobApplications = new ArrayList<>();
}
