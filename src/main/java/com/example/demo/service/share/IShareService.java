package com.example.demo.service.share;

import com.example.demo.model.AppUser;
import com.example.demo.model.Share;
import com.example.demo.service.IGeneralService;

import java.util.List;

public interface IShareService extends IGeneralService<Share> {
    List<Share> findByOwner(AppUser owner);

    List<Share> findAllByCustom(AppUser custom);

    List<Share> findAllByOwnerAndCustom(AppUser owner, AppUser custom);
}
