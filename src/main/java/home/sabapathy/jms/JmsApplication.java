package home.sabapathy.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import home.sabapathy.jms.actors.Publisher;

@SpringBootApplication
public class JmsApplication
{
    public static void main(String[] args)
    {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(JmsApplication.class);

        applicationContext.getBean(Publisher.class).publishMessage();

        applicationContext.getBean(DefaultMessageListenerContainer.class).shutdown();
    }
}
