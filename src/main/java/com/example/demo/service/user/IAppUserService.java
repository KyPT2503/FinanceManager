package com.example.demo.service.user;

import com.example.demo.model.AppUser;
import com.example.demo.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAppUserService extends IGeneralService<AppUser>, UserDetailsService {
    AppUser getCurrentUser();

    AppUser getUserByUserName(String username);
    List<AppUser> findByEmail(String email);
}
