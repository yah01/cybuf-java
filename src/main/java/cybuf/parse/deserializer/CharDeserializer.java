package cybuf.parse.deserializer;

import cybuf.CybufException;

public class CharDeserializer implements ObjectDeserializer
{
    public final static CharDeserializer instance = new CharDeserializer();
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer)
    {
        if(object.getClass() != Character.class && object.getClass() != Character.TYPE)
        {
            throw new CybufException("class type error");
        }
        assert object instanceof Character;
        return (Character) object;
    }
}
