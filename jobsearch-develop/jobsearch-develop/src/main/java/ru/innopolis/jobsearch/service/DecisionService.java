package ru.innopolis.jobsearch.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.jobsearch.dto.DecisionDto;
import ru.innopolis.jobsearch.entity.CV;
import ru.innopolis.jobsearch.entity.Decision;
import ru.innopolis.jobsearch.entity.Vacancy;
import ru.innopolis.jobsearch.repository.DecisionRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DecisionService {
    private final DecisionRepository decisionRepository;

    public Decision saveDecision(Decision decision) {
        return decisionRepository.save(decision);
    }

    public Decision getById(Decision decision) {
        return decisionRepository.getOne(decision.getId());
    }

    public List<DecisionDto> findAllDecisions() {
        return DecisionMapper.INSTANCE.decisionEntitiesToDecisionDtos(decisionRepository.findAll());

    }

    public Decision getDecisionByVacancyAndCv(int vacancyId, int cvId) {
        return decisionRepository.findByVacancyAndCv(vacancyId, cvId).orElse(null);
    }

    public List<Decision> getDecisionsByCvs(List<Integer> cvs){
        return decisionRepository.findAllByCvs(cvs);
    }

    public List<Decision> getDecisionsByVacancies(List<Integer> vacancies){
        return decisionRepository.findAllByVacancies(vacancies);
    }
}
