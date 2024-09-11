package com.vtn.repository;

import com.vtn.pojo.Cart;

public interface CartRepository {

    void save(Cart cart);

    void update(Cart cart);

    void delete(Long id);
}
