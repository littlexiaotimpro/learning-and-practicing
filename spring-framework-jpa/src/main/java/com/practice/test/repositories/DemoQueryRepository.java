package com.practice.test.repositories;

import com.practice.test.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoQueryRepository extends JpaRepository<Log, String> {

}
