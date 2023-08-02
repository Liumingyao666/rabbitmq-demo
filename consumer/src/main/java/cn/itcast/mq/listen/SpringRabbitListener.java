package cn.itcast.mq.listen;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

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

    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) {
        System.out.println("消费者1接收到Fanout消息：【" + msg + "】");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) {
        System.out.println("消费者2接收到Fanout消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "direct.queue1"), exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT), key = {"red", "blue"}))
    public void listenDirectQueue1(String msg) {
        System.out.println("消费者接收到direct.queue1消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "direct.queue2"), exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT), key = {"red", "yellow"}))
    public void listenDirectQueue2(String msg) {
        System.out.println("消费者接收到direct.queue2消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenTopicQueue1(String msg){
        System.out.println("消费者接收到topic.queue1的消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueue2(String msg){
        System.out.println("消费者接收到topic.queue2的消息：【" + msg + "】");
    }


    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(Map<String, String> msg){
        System.out.println("消费者接收到Fanout消息：【" + msg + "】");
    }
}
