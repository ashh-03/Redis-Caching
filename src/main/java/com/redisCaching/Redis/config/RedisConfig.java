package com.redisCaching.Redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object>
    redisTemplate(
            RedisConnectionFactory factory
    ){
        RedisTemplate<String, Object>
                template =
                new RedisTemplate<>();

        template.setConnectionFactory(factory);

        template.setKeySerializer(
                new StringRedisSerializer()
        );

        template.setDefaultSerializer(
                new GenericJackson2JsonRedisSerializer()
        );


//        template.setValueSerializer(
//                new GenericJackson2JsonRedisSerializer()
//        );




        //  for newer version of java genericjackson2jsonredisserialiser() dont work(written just above)

//        JacksonJsonRedisSerializer<Object> serializer =
//                new JacksonJsonRedisSerializer<>(
//                        Object.class
//                );
//
//        template.setValueSerializer(
//                serializer
//        );



        //---------------------------------
//
//        template.setHashKeySerializer(
//                new StringRedisSerializer()
//        );
//
//        template.setHashValueSerializer(
//                serializer
//        );
//-------------------or
//        template.setDefaultSerializer(
//                serializer
//        );



        //-------------temporarily because above Serializer is not supporting thats why storing in string
//        template.setDefaultSerializer(
//                new StringRedisSerializer()
//        );

        return template;
    }


}