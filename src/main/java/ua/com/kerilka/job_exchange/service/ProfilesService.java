package ua.com.kerilka.job_exchange.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kerilka.job_exchange.entity.Profiles;
import ua.com.kerilka.job_exchange.repository.ProfilesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfilesService {
   private final ProfilesRepository profilesRepository;

   public List<Profiles> findAllProfiles() {
       return profilesRepository.findAll();
   }
   public Profiles findByNameProfile(String firstName) {
       return profilesRepository.findByFirstName(firstName);
   }
   public Profiles findByIdProfile(Long id) {
       return profilesRepository.findById(id).get();
   }
   public void save(Profiles profile) {
      profilesRepository.save(profile);
   }
   public void updateProfile(Profiles profile) {
       profilesRepository.save(profile);
   }
   public void deleteProfileById(long id) {
       profilesRepository.deleteById(id);
   }
   public void deleteProfile(Profiles profile) {
       profilesRepository.delete(profile);
   }
   public void deleteAllProfiles() {profilesRepository.deleteAll();}

}
