package com.jel.selfemployed.Repository;

import com.jel.selfemployed.Model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {

}