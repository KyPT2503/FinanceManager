package com.example.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppUserPrinciple implements UserDetails {
    private Long id;
    private String userName;
    private String password;
    private Collection<? extends  GrantedAuthority> roles;

    public AppUserPrinciple(Long id, String userName, String password, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public static AppUserPrinciple build(AppUser appUser) {
        List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(appUser.getRole().getName()));
        System.out.println("đã có được authorize " + appUser.getRole().getName());
        return new AppUserPrinciple(appUser.getId(), appUser.getEmail(), appUser.getPassword(),authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
