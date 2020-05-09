package cybuf.parse.deserializer;

import cybuf.CybufException;
import cybuf.CybufObject;
import cybuf.util.BeanInfo;
import cybuf.util.FieldInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JavaBeanDeserializer implements ObjectDeserializer
{
    private final BeanInfo beanInfo;
    public JavaBeanDeserializer(BeanInfo beanInfo)
    {
        this.beanInfo = beanInfo;
    }
    @Override
    public <T> Object deserialize(Object object, CybufDeserializer deserializer,Class<T> clazz)
    {
        if(object.getClass() != CybufObject.class)
        {
            throw new CybufException("JavaBeanDeserializer error, object should be CybufObject");
        }
        CybufObject cybufObject = (CybufObject) object;
        Map<String,FieldInfo> setters = new HashMap<>();
        FieldInfo[] fieldInfos = beanInfo.getFieldInfos();
        for(FieldInfo fieldInfo : fieldInfos)
        {
            setters.put(fieldInfo.getFieldName(),fieldInfo);
        }
        try
        {
            Object newInstance = clazz.getConstructor().newInstance();
            for(Map.Entry<String,Object> entry : cybufObject.entrySet())
            {
                String fieldName = entry.getKey();
                if(setters.containsKey(fieldName))
                {
                    FieldInfo fieldInfo = setters.get(fieldName);
                    Object value = deserializer.deserialize(entry.getValue(),fieldInfo.getFieldClass());
                    fieldInfo.setObjectFieldValue(newInstance,value);
                    setters.remove(fieldName);
                }
                else
                {
                    throw new CybufException("JavaBeanDeserializer error, JavaBean does not have " + fieldName);
                }
            }
            return newInstance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
        {
            throw new CybufException("JavaBeanDeserializer error, create instance error");
        }
    }

}
