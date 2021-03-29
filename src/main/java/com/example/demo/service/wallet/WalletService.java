package com.example.demo.service.wallet;

import com.example.demo.model.AppUser;
import com.example.demo.model.Event;
import com.example.demo.model.Wallet;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.IWalletRepository;
import com.example.demo.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService implements IWalletService {
    @Autowired
    private IWalletRepository iWalletRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @Override
    public List<Wallet> findAll() {
        return (List<Wallet>) iWalletRepository.findAll();
    }

    @Override
    public Wallet add(Wallet wallet) {
        return iWalletRepository.save(wallet);
    }

    @Override
    public boolean remove(Wallet wallet) {
        iWalletRepository.delete(wallet);
        return true;
    }


    @Override
    public Wallet update(Wallet wallet) {
        return iWalletRepository.save(wallet);
    }

    @Override
    public Wallet findById(Long id) {
        return iWalletRepository.findById(id).get();
    }

    @Override
    public List<Wallet> findByWalletName(String name) {
        return iWalletRepository.findProductName(name);
    }

    @Override
    public void deleteWallet(Long id) {
        List<Event> list = eventService.getEventByWallet(String.valueOf(id));
        for (Event e:list) {
            eventRepository.delete(e);
        }
        iWalletRepository.deleteById(id);
    }

    @Override
    public List<Wallet> findByUser(AppUser appUser) {
        return (List<Wallet>) iWalletRepository.findWalletByUser(appUser.getId());
    }


}
