package com.kroogle.tacocloud.data.jdbc;

import com.kroogle.tacocloud.model.Order;

public interface OrderRepository {

    Order save(Order order);

}
