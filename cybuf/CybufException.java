package cybuf;
public class CybufException extends RuntimeException
{
    public CybufException()
    {
        super();
    }

    public CybufException(String message)
    {
        super(message);
    }

    public CybufException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
