package ua.com.kerilka.job_exchange.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidates candidate;

    @ManyToOne
    @JoinColumn(name = "vacancy_id", nullable = false)
    private Vacancy vacancy; // Твоя сутність вакансії

    // Хто надіслав запит: "COMPANY" (запрошення) або "CANDIDATE" (відгук)
    @Column(name = "initiated_by", nullable = false)
    private String initiatedBy;

    // Статус: "PENDING" (очікує), "ACCEPTED" (прийнято), "REJECTED" (відхилено)
    @Column(nullable = false)
    private String status = "PENDING";
}