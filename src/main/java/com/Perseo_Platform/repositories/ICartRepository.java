package com.Perseo_Platform.repositories;

import com.Perseo_Platform.models.Cart;
import org.springframework.data.repository.CrudRepository;

public interface ICartRepository extends CrudRepository<Cart, Integer> {
}
