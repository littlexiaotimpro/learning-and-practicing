package com.practice.test.repositories;

import com.practice.test.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link @EnableJpaRepositories(basePackages = {"com.practice.test.repositories"})}
 * 1.配置了扫描路径，继承了jpa的相关接口，@Repository 可以省略
 *
 * 2.@RepositoryDefinition(domainClass = Log.class, idClass = String.class)
 * 没有继承相关类型时，必须加上如上注解，标识当前接口为持久层定义，并指定其绑定的实体映射及主键类型
 */
@Repository
public interface DemoQueryRepository extends JpaRepository<Log, String> {

}
