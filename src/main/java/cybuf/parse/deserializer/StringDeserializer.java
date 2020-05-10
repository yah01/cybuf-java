package cybuf.parse.deserializer;

import cybuf.CybufException;

import java.lang.reflect.Type;

public class StringDeserializer implements ObjectDeserializer
{
    public final static StringDeserializer instance = new StringDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer, Type type)
    {
        if(object.getClass() != String.class)
        {
            throw new CybufException("StringDeserializer error");
        }
        return (String) object;
    }
}
