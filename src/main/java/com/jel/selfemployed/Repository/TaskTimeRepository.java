package com.jel.selfemployed.Repository;

import com.jel.selfemployed.Model.TaskTime;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;

public interface TaskTimeRepository extends CrudRepository<TaskTime, Integer> {

    Optional<TaskTime> findByTaskIdAndSessionDate(int id, Date sessionDate);

    Iterable<TaskTime> findAllBySessionDateBetween(Date fromDate, Date toDate);
}