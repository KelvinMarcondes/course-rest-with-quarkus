package com.marcondes.quarkussocial.rest.domain.repository;

import com.marcondes.quarkussocial.rest.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
