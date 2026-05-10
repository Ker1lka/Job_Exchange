package ua.com.kerilka.job_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.kerilka.job_exchange.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
}
