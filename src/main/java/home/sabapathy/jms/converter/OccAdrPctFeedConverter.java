package home.sabapathy.jms.converter;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OccAdrPctFeedConverter implements MessageConverter
{
    private ObjectMapper objectMapper;
    private JavaType javaType;

    public OccAdrPctFeedConverter(ObjectMapper objectMapper, JavaType javaType)
    {
        this.objectMapper = objectMapper;
        this.javaType = javaType;
    }

    @Override
    public Message toMessage(Object object, Session session)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object fromMessage(Message message) throws JMSException
    {
        try
        {
            String body = ((TextMessage) message).getText();
            return objectMapper.readValue(body, javaType);
        }
        catch (IOException | ClassCastException ex)
        {
            throw new MessageConversionException("OCC ADR PCT Exception while converting message", ex);
        }
    }
}
