package com.example.demo.service.share;

import com.example.demo.model.AppUser;
import com.example.demo.model.Share;
import com.example.demo.repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareService implements IShareService {
    @Autowired
    private ShareRepository shareRepository;

    @Override
    public List<Share> findAll() {
        return null;
    }

    @Override
    public Share add(Share share) {
        List<Share> shares = shareRepository.findAllByOwnerAndCustom(share.getOwner(), share.getCustom());
        if (shares.size() > 0) return null;
        return shareRepository.save(share);
    }

    @Override
    public boolean remove(Share share) {
        shareRepository.delete(share);
        return true;
    }

    @Override
    public Share update(Share share) {
        return null;
    }

    @Override
    public Share findById(Long id) {
        return null;
    }

    @Override
    public List<Share> findByOwner(AppUser owner) {
        return shareRepository.findByOwner(owner);
    }

    @Override
    public List<Share> findAllByCustom(AppUser custom) {
        return shareRepository.findAllByCustom(custom);
    }

    @Override
    public List<Share> findAllByOwnerAndCustom(AppUser owner, AppUser custom) {
        return shareRepository.findAllByOwnerAndCustom(owner, custom);
    }
}
