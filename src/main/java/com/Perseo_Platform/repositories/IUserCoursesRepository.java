package com.Perseo_Platform.repositories;

import com.Perseo_Platform.models.UserCourses;
import org.springframework.data.repository.CrudRepository;

public interface IUserCoursesRepository extends CrudRepository<UserCourses,Integer> {
}
