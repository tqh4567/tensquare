package tensquare.rabbitMQ.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "kudingyu")
public class Custome3 {
    @RabbitHandler
    public void getmsg(String msg){
        System.out.println("kudingyu"+msg);
    }
}
