package cybuf.parse.deserializer;

import cybuf.CybufException;

public class StringDeserializer implements ObjectDeserializer
{
    public final static StringDeserializer instance = new StringDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer)
    {
        if(object.getClass() != String.class)
        {
            throw new CybufException("class type error");
        }
        return (String) object;
    }
}
