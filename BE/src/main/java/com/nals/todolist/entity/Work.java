package com.nals.todolist.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "`work`")
public class Work implements Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_id")
    private Integer workId;

    @Column(name = "work_name", columnDefinition = "VARCHAR(50)")
    @NotNull(message = "Work Name can not be NULL")
    private String workName;

    @Column(name = "starting_date", columnDefinition = "DATE")
    private String startingDate;

    @Column(name = "ending_date", columnDefinition = "DATE")
    private String endingDate;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;

    @Override
    public boolean supports(Class<?> clazz) {
        return Work.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Work work = (Work) object;

        try {
            Date startingDate = new SimpleDateFormat("yyyy-MM-dd").parse(work.getStartingDate());
            Date endingDate = new SimpleDateFormat("yyyy-MM-dd").parse(work.getEndingDate());
            Date currentDate = new Date();

            if (startingDate.after(endingDate)){
                errors.rejectValue("startingDate", "work.startingDate.afterEndingDate");
            }
            if (startingDate.before(currentDate)) {
                errors.rejectValue("startingDate", "work.startingDate.beforeCurrentDate");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
