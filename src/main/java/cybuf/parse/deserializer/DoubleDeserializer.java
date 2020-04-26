package cybuf.parse.deserializer;

import cybuf.CybufException;

public class DoubleDeserializer implements ObjectDeserializer
{
    public final static DoubleDeserializer instance = new DoubleDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer)
    {
        if(object.getClass() != Double.class && object.getClass() != Double.TYPE)
        {
            throw new CybufException("class type error");
        }
        assert object instanceof Double;
        return (Double) object;
    }
}
