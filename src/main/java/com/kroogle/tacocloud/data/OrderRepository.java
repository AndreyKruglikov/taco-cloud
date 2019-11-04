package com.kroogle.tacocloud.data;

import com.kroogle.tacocloud.model.Order;

public interface OrderRepository {

    Order save(Order order);

}
