package ua.com.kerilka.job_exchange.service;


import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.kerilka.job_exchange.entity.Users;
import ua.com.kerilka.job_exchange.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username);

        if (user == null) throw new UsernameNotFoundException(username);
        return user;}

    @Cacheable(cacheNames = "user", key = "#username")
    public Users findUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public boolean getUserFromDB(String username) {
        return (usersRepository.findByUsername(username) != null);
    }

    public Users saveNewUser(Users user) {
        return usersRepository.save(user);
    }
    @Cacheable(cacheNames = "users")
    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }
    @Cacheable(cacheNames = "roles")
    public List<Users> findAllRoles() {return usersRepository.findAll();}
}
