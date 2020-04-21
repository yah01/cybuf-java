package cybuf.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FieldInfo
{
    private final String    fieldName;
    private final Field     field;
    private final Method    method;
    private final Class<?>  declaringClass;
    private final Class<?>  fieldClass;

    public FieldInfo(String fieldName,Field field,Method method,Class<?> declaringClass)
    {
        this.fieldName           = fieldName;
        this.field               = field;
        this.method              = method;
        this.declaringClass      = declaringClass;
        this.fieldClass          = field.getClass();
    }

    public Object getObjectFieldValue(Object obj) throws InvocationTargetException, IllegalAccessException
    {
        return method.invoke(obj);
    }

    public String getFieldName()
    {
        return fieldName;
    }
}
