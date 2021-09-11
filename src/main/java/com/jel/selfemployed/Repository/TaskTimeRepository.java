package com.jel.selfemployed.Repository;

import com.jel.selfemployed.Model.IAccountingDay;
import com.jel.selfemployed.Model.TaskTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TaskTimeRepository extends CrudRepository<TaskTime, Integer> {

    Optional<TaskTime> findByTaskIdAndSessionDate(int id, Date sessionDate);

    Iterable<TaskTime> findAllBySessionDateBetween(Date fromDate, Date toDate);

    @Query(value = "SELECT SESSION_DATE as dt, sum(SESSION_HOURS) as hours " +
            "FROM  TASK_TIME " +
            "WHERE SESSION_DATE >= :from and SESSION_DATE <= :to " +
            "GROUP BY SESSION_DATE", nativeQuery = true)
    List<IAccountingDay> findHoursSumBetwwenDates(@Param("from") Date fromDate, @Param("to") Date toDate);
}