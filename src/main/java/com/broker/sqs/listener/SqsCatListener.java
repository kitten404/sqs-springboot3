package com.broker.sqs.listener;
import com.broker.sqs.listener.dto.Cat;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsCatListener {
    @SqsListener("test")
    public void listenToMessage(Cat cat) {
        System.out.println("Cat name : " + cat.getName());
        System.out.println("Cat color: " + cat.getColor());
    }
}
