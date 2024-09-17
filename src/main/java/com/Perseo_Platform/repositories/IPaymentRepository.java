package com.Perseo_Platform.repositories;

import com.Perseo_Platform.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface IPaymentRepository extends CrudRepository<Payment,Integer> {
}
