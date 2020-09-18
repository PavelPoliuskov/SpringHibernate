package org.example.SpringHibernate.repos;

import org.example.SpringHibernate.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//Message repository. Allows to find all objects or by ID (Spring.io)
// This will be AUTO IMPLEMENTED by Spring into a Bean Repository
// CRUD refers Create, Read, Update, Delete
public interface MessageRepo extends CrudRepository<Message, Long> {

    //Spring JPA
    //https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
    List<Message> findByTag(String tag);
}
