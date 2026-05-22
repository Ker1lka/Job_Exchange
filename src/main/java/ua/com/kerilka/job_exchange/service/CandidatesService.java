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
        return candidatesRepository.findAllRealCandidates();
    }
    public List<Candidates> searchCandidatesByProfession(String keyword) {
        return candidatesRepository.findByProfessionContainingIgnoreCase(keyword);
    }

    // Пошук профілю кандидата за об'єктом користувача (із автоматичним створенням порожнього у разі відсутності)
    public Candidates findByUser(Users user) {
        return candidatesRepository.findByUser(user)
                .orElseGet(() -> {
                    Candidates newCandidate = new Candidates();
                    newCandidate.setUser(user);
                    newCandidate.setId(user.getId()); // тому що @MapsId
                    return candidatesRepository.save(newCandidate);
                });
    }
    public Candidates findById(Long id) {
        return candidatesRepository.findById(id).get();
    }

    public void save(Candidates candidate) {
        candidatesRepository.save(candidate);
    }

    public void deleteCandidate(Candidates candidates){candidatesRepository.delete(candidates);}
}

