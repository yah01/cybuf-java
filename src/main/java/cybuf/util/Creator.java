package cybuf.util;

import cybuf.parse.deserializer.JavaBeanDeserializer;
import cybuf.serializer.JavaBeanSerializer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Creator
{
    public static JavaBeanSerializer createJavaBeanSerializer(Class<?> clazz)
    {
        Map<String, Field> fieldMap = new HashMap<>();
        parseAllFieldsToMap(clazz,fieldMap);
        FieldInfo[] fieldInfos = computeGetters(clazz,fieldMap);
        BeanInfo beanInfo = new BeanInfo(fieldInfos);
        return new JavaBeanSerializer(beanInfo);
    }
    public static JavaBeanDeserializer createJavaBeanDeserializer(Class<?> clazz)
    {
        Map<String, Field> fieldMap = new HashMap<>();
        parseAllFieldsToMap(clazz,fieldMap);
        FieldInfo[] fieldInfos = computeSetters(clazz,fieldMap);
        BeanInfo beanInfo = new BeanInfo(fieldInfos);
        return new JavaBeanDeserializer(beanInfo);
    }
    private static void parseAllFieldsToMap(Class<?> clazz,Map<String,Field> fieldMap)
    {
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields)
        {
            String fieldName = field.getName();
            if(!fieldMap.containsKey(fieldName))
            {
                fieldMap.put(fieldName,field);
            }
        }
    }
    private static FieldInfo[] computeGetters(Class<?> clazz,Map<String,Field> fieldMap)
    {
        Map<String,FieldInfo> fieldInfoMap = new HashMap<>();
        Method[] methods = clazz.getMethods();
        for(Method method : methods)
        {
            String methodName = method.getName();
            if(method.getParameterCount() > 0)
            {
                continue;
            }
            if(methodName.startsWith("get"))
            {
                if(methodName.length() < 4 || method.getReturnType() == void.class)
                {
                    continue;
                }
                char c3 = methodName.charAt(3);
                if(Character.isUpperCase(c3))
                {
                    String fieldName = Character.toLowerCase(c3) + methodName.substring(4);
                    if(fieldMap.containsKey(fieldName))
                    {
                        Field field = fieldMap.get(fieldName);
                        FieldInfo fieldInfo = new FieldInfo(fieldName,field,method,clazz);
                        fieldInfoMap.put(fieldName,fieldInfo);
                    }
                }
            }
            if(methodName.startsWith("is"))
            {
                if(methodName.length() < 3 || (method.getReturnType() != Boolean.TYPE && method.getReturnType() != Boolean.class))
                {
                    continue;
                }
                char c2 = methodName.charAt(2);
                if(Character.isUpperCase(c2))
                {
                    String fieldName = Character.toLowerCase(c2) + methodName.substring(3);
                    if(fieldMap.containsKey(fieldName))
                    {
                        Field field = fieldMap.get(fieldName);
                        FieldInfo fieldInfo = new FieldInfo(fieldName,field,method,clazz);
                        fieldInfoMap.put(fieldName,fieldInfo);
                    }
                }
            }
        }

        List<FieldInfo> fieldInfoList = new ArrayList<>(fieldInfoMap.values());
        FieldInfo[] fieldInfos = new FieldInfo[fieldInfoList.size()];
        fieldInfoList.toArray(fieldInfos);

        return fieldInfos;
    }

    private static FieldInfo[] computeSetters(Class<?> clazz,Map<String,Field> fieldMap)
    {
        Map<String,FieldInfo> fieldInfoMap = new HashMap<>();
        Method[] methods = clazz.getMethods();
        for(Method method : methods)
        {
            String methodName = method.getName();
            if(method.getParameterCount() != 1)
            {
                continue;
            }
            if(methodName.startsWith("set"))
            {
                if(methodName.length() < 4)
                {
                    continue;
                }
                char c3 = methodName.charAt(3);
                if(Character.isUpperCase(c3))
                {
                    String fieldName = Character.toLowerCase(c3) + methodName.substring(4);
                    if(fieldMap.containsKey(fieldName))
                    {
                        Field field = fieldMap.get(fieldName);
                        FieldInfo fieldInfo = new FieldInfo(fieldName,field,method,clazz);
                        fieldInfoMap.put(fieldName,fieldInfo);
                    }
                }
            }
        }

        List<FieldInfo> fieldInfoList = new ArrayList<>(fieldInfoMap.values());
        FieldInfo[] fieldInfos = new FieldInfo[fieldInfoList.size()];
        fieldInfoList.toArray(fieldInfos);
        return fieldInfos;
    }
}
