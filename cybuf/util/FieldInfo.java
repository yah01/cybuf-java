package cybuf.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FieldInfo
{
    private final String    fieldName;
    private final Field     field;
    private final Method    method;
    private final Class<?>  clazz;

    public FieldInfo(String fieldName,Field field,Method method,Class<?> clazz)
    {
        this.fieldName  = fieldName;
        this.field      = field;
        this.method     = method;
        this.clazz      = clazz;
    }

    public Object getObjectFieldValue(Object obj) throws InvocationTargetException, IllegalAccessException
    {
        return method.invoke(obj);
    }

    public Object getObjectFieldName(Object obj)
    {
        return fieldName;
    }
}
