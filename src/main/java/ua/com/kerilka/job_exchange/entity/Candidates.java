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
@Table(name = "candidates")
public class Candidates {
    @Id
    private Long id;

    //Registration info
    private String firstName;
    private String lastName;
    private String middleName;
    private int age;
    private String contactInfo;
    private String address;
    private String familyStatus;
    private String housingConditions;
    private String avatarName;

    //Profession
    private String profession;
    private String lastJobPlace;
    private String lastJobPosition;
    private String leavingReason;

    //Education
    private String institution;
    private String specialization;
    private String degree;
    private String educationYears;

    //Other
    private String jobRequirements;
    private boolean isArchived;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Users user;

    @OneToMany(mappedBy = "candidate")
    public List<JobApplication> jobApplications = new ArrayList<>();
}
