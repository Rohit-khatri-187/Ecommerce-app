package com.rohit.ecommerce_backend.repositories;


import com.rohit.ecommerce_backend.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {


    @Query("""
    SELECT oi
    FROM OrderItem oi
    JOIN oi.order o
    WHERE oi.id = :orderItemId
    AND o.user.id = :userId
    """)
    Optional<OrderItem> findOrderItemForUser(
            @Param("orderItemId") Long orderItemId,
            @Param("userId") Long userId
    );

}
