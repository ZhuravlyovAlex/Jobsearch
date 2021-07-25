package ru.innopolis.jobsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.jobsearch.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
