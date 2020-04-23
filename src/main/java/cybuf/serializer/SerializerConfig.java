package cybuf.serializer;

public class SerializerConfig
{
    public static final Integer NEWLINE = 1;
    public static final Integer TAB     = 2;
    public static final Integer SPACE   = 3;
    private Boolean     compressedFormat;
    private Boolean     hasStartBrace;
    private Character   separator;
    public SerializerConfig(Boolean compressedFormat,Boolean hasStartBrace,Integer separator)
    {
        this.compressedFormat = compressedFormat;
        this.hasStartBrace = hasStartBrace;
        switch(separator)
        {
            case 1:
                this.separator = '\n';
                break;
            case 2:
                this.separator = '\t';
                break;
            case 3:
            default:
                this.separator = ' ';
                break;
        }
    }

    public Boolean getCompressedFormat()
    {
        return compressedFormat;
    }

    public Boolean getHasStartBrace()
    {
        return hasStartBrace;
    }

    public Character getSeparator()
    {
        return separator;
    }
}
