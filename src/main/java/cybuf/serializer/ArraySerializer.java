package cybuf.serializer;

import java.lang.reflect.InvocationTargetException;

public class ArraySerializer implements ObjectSerializer
{
    public final static ArraySerializer instance = new ArraySerializer();
    @Override
    public void write(Object obj, CybufSerializer serializer) throws InvocationTargetException, IllegalAccessException
    {
        serializer.writeChar('[');
        serializer.increaseTab();
        serializer.writeln();

        Object[] array = (Object[]) obj;
        for(int i=0;i<array.length;++i)
        {
            serializer.write(array[i]);
            if(i != array.length - 1)
            {
                serializer.writeln();
            }
        }

        serializer.decreaseTab();
        serializer.writeln();
        serializer.writeChar(']');
    }
}
