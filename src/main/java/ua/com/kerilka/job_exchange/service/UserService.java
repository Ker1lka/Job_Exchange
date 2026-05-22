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
    public Users findUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
    public boolean getUserFromDB(String username) {
        return (usersRepository.findByUsername(username) != null);
    }
    public Users saveNewUser(Users user) {
        return usersRepository.save(user);
    }
    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    public Users findById(Long id) {return usersRepository.findById(id).orElse(null);}
    public void save(Users user) {usersRepository.save(user);}

    public List<Users> findAllRoles() {return usersRepository.findAll();}
    public void addRoleToUser(Long userId, Long roleId){usersRepository.addRoleToUser(userId, roleId);}
    public void deteleRoleFromUser(Long userId, Long roleId){usersRepository.deleteRoleFromUser(userId, roleId);}
    public void updateUser(Users user) {usersRepository.save(user);}

    public void deleteUser(Users user){usersRepository.delete(user);}
    public void deleteUserById(Long id){usersRepository.deleteById(id);}
}
