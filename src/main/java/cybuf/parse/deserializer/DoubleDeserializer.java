package cybuf.parse.deserializer;

import cybuf.CybufException;

import java.lang.reflect.Type;

public class DoubleDeserializer implements ObjectDeserializer
{
    public final static DoubleDeserializer instance = new DoubleDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer, Type type)
    {
        try
        {
            return Double.valueOf(object.toString());
        }
        catch(Exception e)
        {
            throw new CybufException("DoubleDeserializer error, " + e.getMessage());
        }
    }
}
