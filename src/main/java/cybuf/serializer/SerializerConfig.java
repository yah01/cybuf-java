package cybuf.serializer;

public class SerializerConfig
{
    private Boolean     compressedFormat;
    private Boolean     hasStartBrace;
    private Character   separator;
    public SerializerConfig(Boolean compressedFormat,Boolean hasStartBrace,Character separator)
    {
        this.compressedFormat = compressedFormat;
        this.hasStartBrace = hasStartBrace;
        this.separator = separator;
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
