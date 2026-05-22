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
    public List<Candidates> searchCandidatesByProfession(String keyword) {
        return candidatesRepository.findByProfessionContainingIgnoreCase(keyword);
    }

    public Candidates findByUser(Users user) {
        return candidatesRepository.findByUser(user)
                .orElseGet(() -> {
                    // Якщо об'єкта кандидата в базі ще немає, створюємо пустий з цим ID
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

    public void updateCandidate(Candidates candidate) {
        candidatesRepository.save(candidate);
    }

    public void deleteCandidateById(Long id) {
        candidatesRepository.deleteById(id);
    }
}

