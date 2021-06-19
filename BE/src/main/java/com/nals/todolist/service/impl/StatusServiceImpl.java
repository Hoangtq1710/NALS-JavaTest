package com.nals.todolist.service.impl;

import com.nals.todolist.entity.Status;
import com.nals.todolist.repository.StatusRepository;
import com.nals.todolist.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository repository;

    @Override
    public List<Status> findAll() {
        return repository.findAll();
    }
}
