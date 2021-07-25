package ru.innopolis.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.innopolis.jobsearch.entity.CV;
import ru.innopolis.jobsearch.entity.Company;

import java.util.List;

public interface CVRepository extends JpaRepository<CV, Integer> {
    @Query("FROM CV c where c.user.id = :userId")
    List<CV> findAllByUserId(@Param("userId") long userId);
}
