package com.tensquare.test.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tensquare.rabbitMQ.RabbitMQApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMQApplication.class)
public class ProductTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void sendmsg1(){
        rabbitTemplate.convertAndSend("itcast","直接模式测试");
    }
    @Test
    public void sendmsg2(){
        rabbitTemplate.convertAndSend("chuanzhi","","分裂模式的测试");
    }
    @Test
    public void sendmsg3(){
        rabbitTemplate.convertAndSend("topic84","good.abc","主题模式的测试");
    }
}
