package com.example.demo.service.user;

import com.example.demo.model.AppUser;
import com.example.demo.model.AppUserPrinciple;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService implements IAppUserService, UserDetailsService {

    @Autowired
    private AppUserRepository repository;

    @Override
    public List<AppUser> findAll() {
        return repository.findAll();
    }

    @Override
    public AppUser add(AppUser appUser) {
        return repository.save(appUser);
    }

    @Override
    public boolean remove(AppUser appUser) {
        if (appUser == null){
            return false;
        }
        repository.delete(appUser);
        return true;
    }

    @Override
    public AppUser update(AppUser appUser) {
        return repository.save(appUser);
    }

    @Override
    public AppUser findById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = repository.findByEmail(email);
        if (appUser == null ) {
            throw new UsernameNotFoundException(email);
        }
        System.out.println("đã nhận UserDetail từ "+email);
        return AppUserPrinciple.build(appUser);
    }

    @Override
    public AppUser getUserByUserName(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public AppUser getCurrentUser() {
        AppUser appUser;
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof  UserDetails) {
            email = ((UserDetails)principal).getUsername();
        }else {
            email = principal.toString();
        }
        appUser = this.getUserByUserName(email);
        return appUser;
    }


}
