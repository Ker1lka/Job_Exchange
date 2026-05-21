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
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private String contactInfo;
    private String address;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Users user;

    @OneToMany(mappedBy = "company")
    private List<Vacancy> vacancies = new ArrayList<>();
}
