package ua.com.kerilka.job_exchange.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kerilka.job_exchange.entity.Candidates;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.repository.CandidatesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidatesService {
    private final CandidatesRepository candidatesRepository;

    public List<Candidates> findAllCandidates() {
        return candidatesRepository.findAll();
    }

    public Candidates findByNameCandidate(String firstName) {
        return candidatesRepository.findByFirstName(firstName);
    }

    public Candidates findByIdCandidate(Long id) {
        return candidatesRepository.findById(id).get();
    }

    public void save(Candidates candidate) {
        candidatesRepository.save(candidate);
    }

    public Candidates findByUser(Users user) {
        return candidatesRepository.findByUser(user);
    }

    public void updateCandidate(Candidates candidate) {
        candidatesRepository.save(candidate);
    }

    public void deleteCandidateById(Long id) {
        candidatesRepository.deleteById(id);
    }
}

