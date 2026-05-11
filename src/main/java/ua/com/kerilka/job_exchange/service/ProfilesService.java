package ua.com.kerilka.job_exchange.service;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ua.com.kerilka.job_exchange.entity.Profiles;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.repository.ProfilesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfilesService {
    private final ProfilesRepository profilesRepository;

    @Cacheable(cacheNames = "profiles")
    public List<Profiles> findAllProfiles() {
        return profilesRepository.findAll();
    }

    @Cacheable(cacheNames = "profileName", key = "#firstName")
    public Profiles findByNameProfile(String firstName) {return profilesRepository.findByFirstName(firstName);}

    @Cacheable(cacheNames = "profileId", key = "#id")
    public Profiles findByIdProfile(Long id) {return profilesRepository.findById(id).get();}

    @CacheEvict(cacheNames = {"profileName", "profileId", "profiles"}, allEntries = true)
    public void save(Profiles profile) {profilesRepository.save(profile);}

    public Profiles findByUser(Users user){
        return profilesRepository.findByUser(user);
    }
}
