package ru.innopolis.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.innopolis.jobsearch.entity.CV;
import ru.innopolis.jobsearch.entity.Decision;

import java.util.List;
import java.util.Optional;

public interface DecisionRepository extends JpaRepository<Decision, Integer> {
    @Query("SELECT d FROM Decision d WHERE d.vacancy.id = ?1 AND d.cv.id = ?2")
    Optional<Decision> findByVacancyAndCv(int vacancyId, int cvId);

    @Query("SELECT d from Decision as d where d.cv.id IN (?1)")
    List<Decision> findAllByCvs(List<Integer> cvs);

    @Query("SELECT d from Decision as d where d.vacancy.id IN (?1)")
    List<Decision> findAllByVacancies(List<Integer> vacancies);
}
