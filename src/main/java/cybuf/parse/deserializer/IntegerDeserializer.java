package cybuf.parse.deserializer;

import cybuf.CybufException;

public class IntegerDeserializer implements ObjectDeserializer
{
    public final static IntegerDeserializer instance = new IntegerDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer)
    {
        if(object.getClass() != Integer.class && object.getClass() != Integer.TYPE)
        {
            throw new CybufException("class type error");
        }
        assert object instanceof Integer;
        return (Integer) object;
    }
}
