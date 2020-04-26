package cybuf.parse;

import cybuf.Cybuf;
import cybuf.CybufArray;
import cybuf.CybufException;
import cybuf.CybufObject;
import cybuf.parse.deserializer.CybufDeserializer;
import cybuf.parse.deserializer.ObjectDeserializer;

import java.util.List;
import java.util.Map;

public class CybufParse
{
    private final String                text;
    private final CybufScanner          scanner;

    public CybufParse(String text)
    {
        this.text               = text;
        this.scanner            = new CybufScanner(text);
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
                    if(key.length() <= 0)
                    {
                        throw new CybufException("Invalid key length");
                    }
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
                case LITERAL_CHAR:
                    value = scanner.charValue();
                    break;
                case TRUE:
                case FALSE:
                    value = scanner.booleanValue();
                    break;
                case NIL:
                    value = scanner.nullValue();
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
                case LITERAL_CHAR:
                    value = scanner.charValue();
                    break;
                case TRUE:
                case FALSE:
                    value = scanner.booleanValue();
                    break;
                case NIL:
                    value = scanner.nullValue();
                    break;
                case RBRACE:
                    throw new CybufException("Unclosing CybufArray");
                default:
                    throw new CybufException("Undefined value type " + scanner.token());
            }
            list.add(value);
        }
    }

    public <T> T parseObject(Class<T> clazz)
    {
        CybufDeserializer deserializer = new CybufDeserializer();
        Object obj = parse();
        return (T) deserializer.deserialize(obj,clazz);
    }

}
