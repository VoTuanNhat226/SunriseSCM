package com.vtn.repository;

import com.vtn.pojo.CartDetails;

public interface CartDetailsRepository {

    void save(CartDetails cartDetails);

    void update(CartDetails cartDetails);

    void delete(Long id);
}
