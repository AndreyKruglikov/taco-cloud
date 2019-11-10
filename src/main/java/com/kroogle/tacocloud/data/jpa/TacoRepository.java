package com.kroogle.tacocloud.data.jpa;

import com.kroogle.tacocloud.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
