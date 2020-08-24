package home.sabapathy.jms.model.io;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class OccAdrPctLocalDateDeserializer extends StdDeserializer<LocalDate>
{
    protected OccAdrPctLocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException
    {
        return LocalDate.parse(parser.readValueAs(String.class));
    }
}
