package cybuf.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class FieldInfo
{
    private final String    fieldName;
    private final Field     field;
    private final Method    method;
    private final Class<?>  declaringClass;//所属的类

    public FieldInfo(String fieldName,Field field,Method method,Class<?> declaringClass)
    {
        this.fieldName           = fieldName;
        this.field               = field;
        this.method              = method;
        this.declaringClass      = declaringClass;
    }

    public Object getObjectFieldValue(Object obj) throws InvocationTargetException, IllegalAccessException
    {
        return method.invoke(obj);
    }

    public Object setObjectFieldValue(Object obj, Object...args) throws InvocationTargetException, IllegalAccessException
    {
        return method.invoke(obj, args);
    }
    public String getFieldName()
    {
        return fieldName;
    }
    public Class<?> getType()
    {
        return field.getType();
    }
    public Type getGenericType()
    {
        return field.getGenericType();
    }
}
