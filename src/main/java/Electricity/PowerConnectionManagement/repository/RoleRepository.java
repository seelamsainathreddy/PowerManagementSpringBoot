package Electricity.PowerConnectionManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Electricity.PowerConnectionManagement.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}