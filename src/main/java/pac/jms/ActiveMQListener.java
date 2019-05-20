package pac.jms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import org.springframework.messaging.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ActiveMQListener {

    private final SimpMessagingTemplate template;

    @Autowired
    ActiveMQListener(SimpMessagingTemplate template){
        this.template = template;
    }

    @JmsListener(destination = "${activemq.topic.listen-from-devices}")
    public void logAction(final Message jsonMessage) {
        System.out.println("Mesaj primit:");
        System.out.println(jsonMessage.getPayload().toString());
        this.template.convertAndSend("/chat",  "update");
    }
}
