package ru.innopolis.jobsearch.repository;


import ru.innopolis.jobsearch.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
