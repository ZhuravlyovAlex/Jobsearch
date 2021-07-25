package ru.innopolis.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.innopolis.jobsearch.entity.Vacancy;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
    @Query("FROM Vacancy vacancy where vacancy.company.id = :companyId")
    List<Vacancy> findAllByCompanyId(@Param("companyId") int companyId);
}

