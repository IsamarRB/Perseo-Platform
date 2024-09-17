package com.Perseo_Platform.repositories;

import com.Perseo_Platform.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface ICourseRepository extends CrudRepository<Course,Integer> {
}
