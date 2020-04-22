package cybuf.serializer;

import cybuf.CybufException;

public class SerializerWriter
{
    private char[]      buf;
    private int         count;
    private final int   DEFAULT_BUF_LENGTH = 2048;
    private int         tabCount;

    public SerializerWriter()
    {
        buf      = new char[DEFAULT_BUF_LENGTH];
        count    = 0;
        tabCount = 0;
    }

    public void writeChar(char c)
    {
        int newCount = count + 1;
        if(newCount > buf.length)
        {
            expandCapacity(newCount);
        }
        buf[count] = c;
        count = newCount;
    }

    public void writeFieldName(String fieldName)
    {
        int len = fieldName.length();
        int newCount = count + len + 1;
        if(newCount > buf.length)
        {
            expandCapacity(newCount);
        }
        fieldName.getChars(0,len,buf,count);
        buf[newCount - 1] = ':';
        count = newCount;
    }
    public void writeString(String value)
    {
        int len = value.length();
        int newCount = count + len + 2;
        if(newCount > buf.length)
        {
            expandCapacity(newCount);
        }
        int start = count + 1;
        buf[count] = '"';
        value.getChars(0,len,buf,start);
        buf[newCount - 1] = '"';
        count = newCount;
    }
    public void writeCharacter(Character value)
    {
        int newCount = count + 3;
        if(newCount > buf.length)
        {
            expandCapacity(newCount);
        }
        buf[newCount - 3] = '\'';
        buf[newCount - 2] = value;
        buf[newCount - 1] = '\'';
        count = newCount;
    }
    public void writeNull()
    {
        int newCount = count + 3;
        if(newCount > buf.length)
        {
            expandCapacity(newCount);
        }
        "nil".getChars(0,3,buf,count);
        count = newCount;
    }
    public void writeln()
    {
        writeChar('\n');
        for(int i=0;i<tabCount;++i)
        {
            writeChar('\t');
        }
    }
    public void writeBaseObject(Object object)
    {
        String objectString = object.toString();
        int len = objectString.length();
        int newCount = count + len;
        if(newCount > buf.length)
        {
            expandCapacity(newCount);
        }
        objectString.getChars(0,len,buf,count);
        count = newCount;
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
    public void increaseTab()
    {
        tabCount = tabCount + 1;
    }
    public void decreaseTab()
    {
        tabCount = tabCount - 1;
    }

    public String toString()
    {
        return new String(buf,0,count);
    }

    public char getLastChar()
    {
        if(count > 0)
        {
            return buf[count - 1];
        }
        else
        {
            throw new CybufException("empty buf");
        }
    }

}
