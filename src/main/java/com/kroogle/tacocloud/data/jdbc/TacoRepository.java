package com.kroogle.tacocloud.data.jdbc;

import com.kroogle.tacocloud.model.Taco;

public interface TacoRepository {

    Taco save(Taco taco);

}
