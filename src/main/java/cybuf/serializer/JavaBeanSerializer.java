package cybuf.serializer;

import cybuf.util.BeanInfo;
import cybuf.util.FieldInfo;

import java.lang.reflect.InvocationTargetException;

public class JavaBeanSerializer implements ObjectSerializer
{
    private final BeanInfo      beanInfo;
    private final FieldInfo[]   getters;

    public JavaBeanSerializer(BeanInfo beanInfo)
    {
        this.beanInfo = beanInfo;
        getters = beanInfo.getFieldInfos();
    }

    @Override
    public void write(Object obj, CybufSerializer serializer) throws InvocationTargetException, IllegalAccessException
    {
        serializer.writeStartCharWithFormat('{');

        for(int i=0;i<getters.length;++i)
        {
            String key = getters[i].getFieldName();
            serializer.writeFieldName(key);

            Object value = getters[i].getObjectFieldValue(obj);
            serializer.write(value);

            if(i != getters.length - 1)
            {
                serializer.writeSeparator();
            }
        }
        serializer.writeEndCharWithFormat('}');
    }
}
