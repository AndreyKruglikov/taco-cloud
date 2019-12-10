package com.kroogle.tacocloud.data.jpa.user;

import com.kroogle.tacocloud.model.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
