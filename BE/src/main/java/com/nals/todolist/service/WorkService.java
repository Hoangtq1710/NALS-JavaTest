package com.nals.todolist.service;

import com.nals.todolist.entity.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkService {

    Page<Work> findAll(Pageable pageable);

    Work findById(Integer id);

    void save(Work work);

    void deleteById(Integer id);

}
