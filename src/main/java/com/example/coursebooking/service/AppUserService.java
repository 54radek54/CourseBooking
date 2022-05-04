package com.example.coursebooking.service;

import com.example.coursebooking.exception.AppBasicException;
import com.example.coursebooking.model.AppUser;

import java.security.Principal;
import java.util.List;

public interface AppUserService {

    void addUser(AppUser appUser)throws AppBasicException;

    List<AppUser> getAllUserAccounts();

    void editUser(AppUser appUser, Principal principal)throws AppBasicException;

    void deleteUserById(Long id)throws AppBasicException;

    void makeEmployeeFromUserById(Long id)throws AppBasicException;

    AppUser getAppUserByEmail(String email)throws AppBasicException;

    List<AppUser> getByKeyword(String keyword);

}
