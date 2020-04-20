package cybuf.serializer;

public class SerializerWriter
{
    private char[]      buf;
    private int         count;
    private final int   DEFAULT_BUF_LENGTH = 2048;

    public SerializerWriter()
    {
        buf = new char[DEFAULT_BUF_LENGTH];
        count = 0;
    }

    private void expandCapacity(int capacity)
    {
        int newCapacity = buf.length + (buf.length >> 1);
        if(newCapacity < capacity)
        {
             newCapacity = capacity;
        }
        char[] newBuf = new char[newCapacity];
        System.arraycopy(buf,0,newBuf,0,count);

        buf = newBuf;
    }

    public String toString()
    {
        return new String(buf,0,count);
    }

}
