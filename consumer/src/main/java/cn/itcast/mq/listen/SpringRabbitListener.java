package cn.itcast.mq.listen;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueueMessage(String message){
//        System.out.println("spring 消费者接收到消息：【" + message + "】");
//    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue1Message(String message) throws InterruptedException {
        System.out.println("spring 消费者1接收到消息：【" + message + "】" + LocalTime.now());
        Thread.sleep(20);
    }


    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue2Message(String message) throws InterruptedException {
        System.out.println("spring 消费者2接收到消息：【" + message + "】" + LocalTime.now());
        Thread.sleep(200);
    }

}
