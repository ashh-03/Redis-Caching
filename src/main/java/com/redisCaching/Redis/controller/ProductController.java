package com.redisCaching.Redis.controller;

import com.redisCaching.Redis.modal.Product;
import com.redisCaching.Redis.service.OtpService;
import com.redisCaching.Redis.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final OtpService otpService;



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


    //-----------generate otp------

    @GetMapping("/generate/{phone}")
    public String generate(
            @PathVariable String phone
            //-----or we create request dto inside phone number field
    ){

        return otpService
                .generateOtp(phone);
    }



    //----------verify otp--------
    @GetMapping("/verify")
    public String verify(
            @RequestParam String phone,
            @RequestParam String otp

            //----or can create response dto
    ){

        return otpService
                .verifyOtp(phone, otp);
    }

}