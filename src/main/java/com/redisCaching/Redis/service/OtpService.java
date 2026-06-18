package com.redisCaching.Redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final RedisTemplate<String, Object>
            redisTemplate;


    //---------generate otp
    public String generateOtp(
            String phone
    ){

        SecureRandom random = new SecureRandom();

        int ottp = 100000 +
                random.nextInt(900000);

        String otp= String.valueOf(ottp);

        redisTemplate
                .opsForValue()
                .set(
                        "otp:" + phone,
                        otp,
                        Duration.ofMinutes(1)
                );

        return otp;

    }

//-----------verify otp
    public String verifyOtp(
            String phone,
            String otp
    ){

        String savedOtp =
                (String) redisTemplate
                        .opsForValue()
                        .get(
                                "otp:" + phone
                        );


        if(savedOtp == null) {

            return "OTP Expired";
        }

        if(!savedOtp.equals(
                otp
        )) {

            return "Invalid OTP";
        }

        redisTemplate.delete(
                "otp:" +
                        phone
        );

        return "OTP Verified Successfully";

//        return otp.equals(savedOtp);
    }

}