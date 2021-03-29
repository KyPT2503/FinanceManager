package com.example.demo.service.groupaction;

import com.example.demo.model.GroupAction;
import com.example.demo.repository.GroupActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupActionService implements IGroupAction {
    @Autowired
    private GroupActionRepository groupActionRepository;

    @Override
    public List<GroupAction> findAll() {
        return groupActionRepository.findAll();
    }

    @Override
    public GroupAction add(GroupAction groupAction) {
        return null;
    }

    @Override
    public boolean remove(GroupAction groupAction) {
        return false;
    }

    @Override
    public GroupAction update(GroupAction groupAction) {
        return null;
    }

    @Override
    public GroupAction findById(Long id) {
        return null;
    }
}
