package com.rohit.ecommerce_backend.repositories;

import com.rohit.ecommerce_backend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("""
            SELECT O
            FROM Order O
            where O.user.id = :userId
        """)
    List<Order> findAllOrdersByUserId(
            @Param("userId") Long userId
    );

}
