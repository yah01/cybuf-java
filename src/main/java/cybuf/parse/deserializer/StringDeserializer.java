package cybuf.parse.deserializer;

import cybuf.CybufException;

public class StringDeserializer implements ObjectDeserializer
{
    public final static StringDeserializer instance = new StringDeserializer();
    @Override
    public <T> Object deserialize(Object object, CybufDeserializer deserializer,Class<T> clazz)
    {
        if(object.getClass() != clazz)
        {
            throw new CybufException("StringDeserializer error");
        }
        return (String) object;
    }
}
