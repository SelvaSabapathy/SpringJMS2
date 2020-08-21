package home.sabapathy.jms.actors;

import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

@Component
public class Subscriber implements MessageListener
{
    @Override
    public void onMessage(javax.jms.Message message)
    {
        receiveMessage(message);
    }

    public void receiveMessage(javax.jms.Message message)
    {
        System.out.println("Received: \"" + message + "\"");
    }
}