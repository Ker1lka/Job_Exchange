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
@Table(name = "profiles")
public class Candidates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String middleName;
    private int age;
    private String contactInfo;
    private String address;
    private String avatarName;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Users user;

    @OneToMany(mappedBy = "profile")
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "profile")
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "profile")
    private List<ProfileHasProfession> profileHasProfessions = new ArrayList<>();

    @OneToMany(mappedBy = "profile")
    private List<ProfileHasVacancy> profileHasVacancies = new ArrayList<>();

    @OneToOne(mappedBy = "profile")
    private JobRequirements jobRequirements;
}
