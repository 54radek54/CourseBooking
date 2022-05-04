package com.example.coursebooking.service.impl;

import com.example.coursebooking.exception.AppBasicException;
import com.example.coursebooking.model.AppUser;
import com.example.coursebooking.repository.AppUserRepository;
import com.example.coursebooking.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class AppUserServiceImplementation implements AppUserService {

    private final AppUserRepository appUserRepository;

    private final PasswordEncoder passwordEncoder;

    public static final String ROLE_USER = "ROLE_USER";

    public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";

    @Override
    public void addUser(AppUser appUser)throws AppBasicException{
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        try{
            if(appUserRepository.findByEmail(appUser.getEmail()) == null){
                appUser.setRole(ROLE_USER);
                appUserRepository.saveAndFlush(appUser);
            }else {throw new AppBasicException("User already exist!");}
        }catch (PersistenceException e){
            throw new AppBasicException("User can tbe saved right now");
        }
    }

    @Override
    public AppUser getAppUserByEmail(String email) throws AppBasicException {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public List<AppUser> getAllUserAccounts(){
        return appUserRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id)throws AppBasicException{
        appUserRepository.deleteById(id);
    }

    @Override
    public void editUser(AppUser appUser, Principal principal)throws AppBasicException{
        AppUser updatedUser = appUserRepository.findByEmail(principal.getName());
        updatedUser.setFirstName(appUser.getFirstName());
        updatedUser.setLastName(appUser.getLastName());
        appUserRepository.save(updatedUser);
    }

    @Override
    public void makeEmployeeFromUserById(Long id)throws AppBasicException{
        AppUser currentUser = appUserRepository.findUserById(id);
        if(currentUser.getRole().equals(ROLE_USER)){
            currentUser.setRole(ROLE_EMPLOYEE);
        }else {
            currentUser.setRole(ROLE_USER);
        }
        appUserRepository.save(currentUser);

    }

    @Override
    public List<AppUser> getByKeyword(String keyword) {
        return appUserRepository.findUserByKeyword(keyword);
    }
}
