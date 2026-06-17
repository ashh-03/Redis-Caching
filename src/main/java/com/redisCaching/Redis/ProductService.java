package com.redisCaching.Redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final RedisTemplate<String, Object>
            redisTemplate;

//------------------save product--------------------
    public Product save(
            Product product
    ){

        redisTemplate
                .opsForValue()
                .set(
                        "product:" + product.getId(),
                        product
                );

        return product;
    }

    //----------------------get product----------------


    public Product getProduct(
            Long id
    ){

        return (Product)
                redisTemplate
                        .opsForValue()
                        .get(
                                "product:" + id
                        );
    }

    //-----------------delete product----------------

    public void deleteProduct(
            Long id
    ){

        redisTemplate.delete(
                "product:" + id
        );
    }

}