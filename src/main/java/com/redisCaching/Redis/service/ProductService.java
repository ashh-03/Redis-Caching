package com.redisCaching.Redis.service;

import com.redisCaching.Redis.modal.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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


    //--------example when we have db and repo

//    @Cacheable("products")
//    public Product getProduct(
//            Long id
//    ){
//
//        System.out.println(
//                "Fetching from DB..."
//        );
//
//        return repository
//                .findById(id)
//                .orElseThrow();
//    }

}