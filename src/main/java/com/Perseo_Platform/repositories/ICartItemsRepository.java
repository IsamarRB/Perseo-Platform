package com.Perseo_Platform.repositories;

import com.Perseo_Platform.models.CartItems;
import org.springframework.data.repository.CrudRepository;

public interface ICartItemsRepository extends CrudRepository<CartItems,Integer> {
}
