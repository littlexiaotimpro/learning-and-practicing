package com.practice.test.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

// NoRepositoryBean：标注当前组件在容器加载过程中不会创建实例，一般用于选择性的提供方法
@NoRepositoryBean
public interface MethodRepository<T,ID> extends Repository<T, ID> {

    Optional<T> findById(ID id);

}
