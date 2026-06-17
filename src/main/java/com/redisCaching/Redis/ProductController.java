package com.redisCaching.Redis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;



    //-------save api------
    @PostMapping
    public Product save(
            @RequestBody Product product
    ){

        return service.save(product);
    }

    //-------get api-----------

    @GetMapping("/{id}")
    public Product get(
            @PathVariable Long id
    ){

        return service.getProduct(id);
    }

    //-------delete api--------

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ){

        service.deleteProduct(id);
    }

}