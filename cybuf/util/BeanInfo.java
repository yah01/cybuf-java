package cybuf.util;

public class BeanInfo
{
    public final FieldInfo[] fieldInfos;
    public BeanInfo(FieldInfo[] fieldInfos)
    {
        this.fieldInfos = fieldInfos;
    }
    public int length()
    {
        return fieldInfos.length;
    }
    public FieldInfo[] getFieldInfos()
    {
        return fieldInfos;
    }
}
