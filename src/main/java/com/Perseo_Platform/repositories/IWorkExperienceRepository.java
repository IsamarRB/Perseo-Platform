package com.Perseo_Platform.repositories;


import com.Perseo_Platform.models.WorkExperience;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IWorkExperienceRepository extends CrudRepository<WorkExperience, Integer> {
    List<WorkExperience> findByUserUserId(int userId);
}
