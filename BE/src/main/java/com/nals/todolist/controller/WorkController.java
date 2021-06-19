package com.nals.todolist.controller;

import com.nals.todolist.entity.Work;
import com.nals.todolist.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    /**
     * author : HoangTQ
     * function : getAllWork()
     * @param pageNo : No of page, default = 0
     * @param pageSize : Size of page, default = 5
     * @param sortBy : property that we want to sort by, default = workName
     * @param orderBy : the order of Sorting, default = DESC (DESCENDING)
     * @return a Page of Work
     */
    @GetMapping("")
    public ResponseEntity<Page<Work>> getAllWork(@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
                                                 @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                                                 @RequestParam(name = "sortBy", defaultValue = "workName") String sortBy,
                                                 @RequestParam(name = "orderBy", defaultValue = "DESC") String orderBy) {
        try {
            Sort sort = (orderBy.equals("DESC")) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

            Page<Work> listWork = workService.findAll(pageable);
            if (listWork.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listWork, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * author : HoangTQ
     * function : addWork()
     * @param work : a object of Work that we are about to create
     * @param bindingResult : a set of Errors (if any)
     * @return Void
     */
    @PostMapping("/add")
    public ResponseEntity<Void> addWork(@Valid @RequestBody Work work,
                                        BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                workService.save(work);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * author : HoangTQ
     * function : editWork()
     * @param workId : if of a Work Object
     * @param work : a Work object we need to update
     * @param bindingResult : a set of Errors (if any)
     * @return a Work object
     */
    @PutMapping("/edit/{workId}")
    public ResponseEntity<Work> editWork(@PathVariable(name = "workId") Integer workId,
                                         @Valid @RequestBody Work work,
                                         BindingResult bindingResult) {
        try {
            Work currentWork = workService.findById(workId);
            if (currentWork == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            currentWork.setWorkName(work.getWorkName());
            currentWork.setStartingDate(work.getStartingDate());
            currentWork.setEndingDate(work.getEndingDate());
            currentWork.setStatus(work.getStatus());

            this.workService.save(work);
            return new ResponseEntity<>(work,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * author : HoangTQ
     * function : deleteWork()
     * @param workId : if of a Work object which is the work object we are want to delete
     * @return Void
     */
    @DeleteMapping("/delete/{workId}")
    public ResponseEntity<Void> deleteWork(@PathVariable(name = "workId") Integer workId) {
        try {
            Work work = this.workService.findById(workId);
            if (work == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            this.workService.deleteById(workId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
