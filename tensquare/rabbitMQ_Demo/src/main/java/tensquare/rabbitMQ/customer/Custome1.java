package tensquare.rabbitMQ.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "itcast")
public class Custome1 {
    @RabbitHandler
    public void getmsg(String msg){
        System.out.println("itcast"+msg);
    }
}
