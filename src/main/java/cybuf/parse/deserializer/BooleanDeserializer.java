package cybuf.parse.deserializer;

import cybuf.CybufException;

public class BooleanDeserializer implements ObjectDeserializer
{
    public final static BooleanDeserializer instance = new BooleanDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer)
    {
        if(object.getClass() != Boolean.class && object.getClass() != Boolean.TYPE)
        {
            throw new CybufException("class type error");
        }
        assert object instanceof Boolean;
        return (Boolean) object;
    }
}
