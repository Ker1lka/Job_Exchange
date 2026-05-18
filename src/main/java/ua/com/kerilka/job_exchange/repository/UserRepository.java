package ua.com.kerilka.job_exchange.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.kerilka.job_exchange.entity.Users;

import java.beans.Transient;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users_roles (users_id, roles_id) VALUES (:userId, :roleId)", nativeQuery = true)
    void addRoleToUser(@Param ("userId") Long userId, @Param ("roleId") Long roleId);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM users_roles WHERE users_id = :userId AND roles_id = :roleId", nativeQuery = true)
    void deleteRoleFromUser(@Param ("userId") Long userId, @Param ("roleId") Long roleId);
}
