package org.example.SpringHibernate.repos;

import org.example.SpringHibernate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


//looking for a user in DB
public interface UserRepo extends JpaRepository<User, Long> {

    //Spring JPA
    //https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
    User findByUsername(String username);
}
