package cybuf.parse.deserializer;

import cybuf.util.BeanInfo;

public class JavaBeanDeserializer implements ObjectDeserializer
{
    private final BeanInfo beanInfo;
    public JavaBeanDeserializer(BeanInfo beanInfo)
    {
        this.beanInfo = beanInfo;
    }
    @Override
    public Object deserialize(Object object, CybufDeserializer deserializer)
    {
        return null;
    }

}
