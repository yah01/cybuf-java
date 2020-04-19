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
                scanner.next();
                return map;
            }
            else if(scanner.getCurrent() == (char)-1)
            {
                throw new CybufException("Unclosing CybufObject");
            }

            String key = "";
            scanner.nextKey();
            switch(scanner.token())
            {
                case IDENTIFIER:
                    key = scanner.stringValue();
                    key = key.trim();
                    scanner.nextValue();
                    break;
                case ERROR:
                    throw new CybufException("Except ':'");
            }


            Object value;
            scanner.nextValue();
            switch(scanner.token())
            {
                case LBRACE:
                case LBRACKET:
                    value = parse();
                    break;
                case LITERAL_INT:
                    value = scanner.integerValue();
                    break;
                case LITERAL_FLOAT:
                    value = scanner.doubleValue();
                    break;
                case LITERAL_STRING:
                    value = scanner.stringValue();
                    break;
                case TRUE:
                case FALSE:
                    value = scanner.booleanValue();
                    break;
                default:
                    throw new CybufException("Undefined value type " + scanner.token());
            }


            map.put(key,value);
        }
    }

    public Object parseArray(List<Object> list)
    {
        for(;;)
        {
            scanner.skipWhitespace();
            if(scanner.getCurrent() == ']')
            {
                scanner.next();
                return list;
            }
            else if(scanner.getCurrent() == (char)-1)
            {
                throw new CybufException(("Unclosing CybufArray"));
            }

            Object value;
            scanner.nextValue();
            switch(scanner.token())
            {
                case LBRACE:
                case LBRACKET:
                    value = parse();
                    break;
                case LITERAL_FLOAT:
                    value = scanner.doubleValue();
                    break;
                case LITERAL_INT:
                    value = scanner.integerValue();
                    break;
                case LITERAL_STRING:
                    value = scanner.stringValue();
                    break;
                case RBRACE:
                    throw new CybufException("Unclosing CybufArray");
                default:
                    throw new CybufException("Undefined value type " + scanner.token());
            }
            list.add(value);
        }
    }
}
