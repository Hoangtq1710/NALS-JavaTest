package com.nals.todolist.service.impl;

import com.nals.todolist.entity.Work;
import com.nals.todolist.repository.WorkRepository;
import com.nals.todolist.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkRepository repository;

    @Override
    public Page<Work> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Work findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void save(Work work) {
        repository.save(work);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
