package ua.com.kerilka.job_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.kerilka.job_exchange.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    @Query(value = "INSERT INTO `users_roles` (`users_id`, `roles_id`) VALUES (:userId, :roleId)", nativeQuery = true)
    void addRoleToUser(@Param ("userId") Long userId, @Param ("roleId") Long roleId);

    @Query(value="DELETE FROM `users_roles` WHERE `users_id` = :userId AND `roles_id` = :roleId", nativeQuery = true)
    void deteleRoleFromUser(@Param ("userId") Long userId, @Param ("roleId") Long roleId);

    @Query(value="UPDATE `users_roles` SET `roles_id` = :roleId WHERE `users_id` = :userId", nativeQuery = true)
    void updateRoleFromUser(@Param ("userId") Long userId, @Param ("roleId") Long roleId);

    @Query(value="SELECT * FROM `users_roles` WHERE `users_id` = :userId AND `roles_id` = :roleId", nativeQuery = true)
    void selectAllUsersAndRoles(@Param ("userId") Long userId, @Param ("roleId") Long roleId);
}
