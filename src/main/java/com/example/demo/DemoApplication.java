package com.example.demo;

import com.example.demo.service.balance.BalanceServiceImpl;
import com.example.demo.service.balance.IBalanceService;
import com.example.demo.service.user.AppUserService;
import com.example.demo.service.user.IAppUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public IBalanceService balanceService() {
        return new BalanceServiceImpl();
    }
    @Bean
    public IAppUserService appUserService() {
        return new AppUserService();
    }
}
