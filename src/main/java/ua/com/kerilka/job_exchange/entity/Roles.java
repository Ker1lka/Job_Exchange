package ua.com.kerilka.job_exchange.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "roles")
public class Roles implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Users> users =  new HashSet<>();

    public Roles(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public @Nullable String getAuthority() {
        return getName();
    }
}
