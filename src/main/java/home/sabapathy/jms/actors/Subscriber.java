package home.sabapathy.jms.actors;

import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

@Component
public class Subscriber implements MessageListener
{
    // TODO: This wouldn't be wired... Just a dummy receiver for now!
    
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