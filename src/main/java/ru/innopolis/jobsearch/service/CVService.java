package ru.innopolis.jobsearch.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.jobsearch.entity.CV;
import ru.innopolis.jobsearch.entity.Company;
import ru.innopolis.jobsearch.repository.CVRepository;
import ru.innopolis.jobsearch.repository.CompanyRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class CVService {
    final private CVRepository cvRepository;

    public CV getCVById(Integer id){
        return cvRepository.getOne(id);
    }

    public CV save(CV cv){
        return cvRepository.save(cv);
    }

    public CV update(CV cv) {
        return cvRepository.save(cv);
    }

    public void delete(int id) {
        cvRepository.deleteById(id);
    }

    public List<CV> findAllByUserId(Long userId){
        return cvRepository.findAllByUserId(userId);
    }

}
