package com.practice.test.repositories;

import com.practice.test.entity.Log;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Log.class, idClass = String.class)
public interface DemoAnnotationRepository {

    Log findById(String logNo);

}
