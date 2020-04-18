import java.util.List;
import java.util.Map;

public class CybufParse
{
    private final String          text;
    private final CybufScanner    scanner;

    public CybufParse(String text)
    {
        this.text    = text;
        this.scanner = new CybufScanner(text);
    }

    public Object parse()
    {
        switch(scanner.token())
        {
            case LBRACKET:
                CybufArray array = new CybufArray();
                return parseArray(array);
            case LBRACE:
                CybufObject object = new CybufObject();
                return parseObject(object);
        }
        return null;
    }

    public Object parseObject(Map<String,Object> map)
    {
        for(;;)
        {
            scanner.skipWhitespace();
            if(scanner.getCurrent() == '}')
            {
                return map;
            }

            String key = "";
            scanner.nextKey();
            if(scanner.token() == CybufToken.IDENTIFIER)
            {
                key = scanner.stringValue();
                key = key.trim();
                scanner.nextValue();
            }
            else if(scanner.token() == CybufToken.ERROR)
            {
                throw new CybufException("Except ':'");
            }

            Object value;
            scanner.nextValue();
            if(scanner.token() == CybufToken.LBRACE || scanner.token() == CybufToken.LBRACKET)
            {
                value = parse();
            }
            else if(scanner.token() == CybufToken.LITERAL_INT)
            {
                value = scanner.integerValue();
            }
            else if(scanner.token() == CybufToken.LITERAL_FLOAT)
            {
                value = scanner.doubleValue();
            }
            else if(scanner.token() == CybufToken.LITERAL_STRING)
            {
                value = scanner.stringValue();
            }
            else
            {
                throw new CybufException("Undefined value type " + scanner.token());
            }

            map.put(key,value);
        }
    }

    public Object parseArray(List<Object> list)
    {

        return null;
    }
}
