package com.rohit.ecommerce_backend.repositories;

import com.rohit.ecommerce_backend.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    @Query("""
        SELECT c
        FROM CartItem c
        WHERE c.user.id = :userId
    """)
    List<CartItem> findCartItemsByUserId(@Param("userId") Long userId);


    @Query("""
        SELECT c
        FROM CartItem c
        WHERE c.user.id = :userId
        AND c.product.id = :productId
    """)
    Optional<CartItem> findCartItemByUserIdAndProductId(
            @Param("userId") Long userId,
            @Param("productId") Long productId
    );

    @Transactional
    @Modifying
    @Query("""
        DELETE FROM CartItem c
        WHERE c.user.id = :userId
    """)
    void deleteAllCartItemsByUserId(@Param("userId") Long userId);



}
