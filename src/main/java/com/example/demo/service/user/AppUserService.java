package com.example.demo.service.user;

import com.example.demo.model.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService implements IAppUserService{
    @Override
    public List<AppUser> findAll() {
        return null;
    }

    @Override
    public AppUser add(AppUser appUser) {
        return null;
    }

    @Override
    public boolean remove(AppUser appUser) {
        return false;
    }

    @Override
    public AppUser update(AppUser appUser) {
        return null;
    }

    @Override
    public AppUser findById(Long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public AppUser getCurrentUser() {
        return null;
    }
}
