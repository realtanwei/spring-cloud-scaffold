package com.tanwei.spring.business.producer;

import com.tanwei.spring.business.producer.client.HelloClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author tanwei
 * @date 2023-05-06 14:59
 **/
@SpringBootTest
public class ProducerSpringBootApplicationTests {

    @Autowired
    HelloClient helloClient;

    @Test
    void webfluxTest() {


        String all = helloClient.saying();
        System.out.println(all);

    }
}
