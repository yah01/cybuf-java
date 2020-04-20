package cybuf.serializer;

import cybuf.util.BeanInfo;

public class JavaBeanSerializer implements ObjectSerializer
{
    private final BeanInfo beanInfo;

    public JavaBeanSerializer(BeanInfo beanInfo)
    {
        this.beanInfo = beanInfo;
    }

    @Override
    public void write(Object obj, CybufSerializer serializer)
    {

    }
}
