public class CybufScanner implements CybufLexer
{
    private int               nowPos;
    private int               startPos;
    private int               tokenLength;
    private int               textLength;
    private char              nowChar;
    private final String      text;
    private CybufToken        token;

    public CybufScanner(String text)
    {
        this.text = text;
        this.textLength = text.length();

        nowPos = -1;
        next();
        skipWhitespace();

        if(nowChar == '{')
        {
            next();
            this.token = CybufToken.LBRACE;
        }
        else if(nowChar == '[')
        {
            next();
            this.token = CybufToken.LBRACKET;
        }
        else
        {
            throw new CybufException("syntax error,should start with '{' or '['");
        }
    }

    @Override
    public CybufToken token()
    {
        return this.token;
    }

    @Override
    public char next()
    {
        int index = ++nowPos;
        return nowChar = (index >= textLength ? EOF : text.charAt(index));
    }

    @Override
    public void nextKey()
    {
        startPos = nowPos;
        tokenLength = 0;

        while(nowChar != EOF && nowChar != ':')
        {
            next();
            tokenLength++;
        }
        if(nowChar == ':')
        {
            token = CybufToken.IDENTIFIER;
        }
        if(nowChar == EOF)
        {
            token = CybufToken.ERROR;
        }
    }

    @Override
    public void nextValue()
    {
        for(;;)
        {
            if(nowChar == '"')
            {
                scanString();
                return ;
            }
            if(nowChar >= '0' && nowChar <= '9')
            {
                scanNumber();
                return ;
            }
            if(nowChar == '-')
            {
                scanNumber();
                return ;
            }
            switch(nowChar)
            {
                case '(':
                    next();
                    token = CybufToken.LPAREN;
                    return ;
                case ')':
                    next();
                    token = CybufToken.RPAREN;
                    return ;
                case '{':
                    next();
                    token = CybufToken.LBRACE;
                    return ;
                case '}':
                    next();
                    token = CybufToken.RBRACE;
                    return ;
                case '[':
                    next();
                    token = CybufToken.LBRACKET;
                    return ;
                case ']':
                    next();
                    token = CybufToken.RBRACKET;
                    return ;
                case ':':
                    next();
                    token = CybufToken.COLON;
                    return ;
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                case '\f':
                case '\b':
                    next();
                    break ;
                default:
                    throw new CybufException("Illegal char " + nowChar);
            }
        }

    }

    @Override
    public char getCurrent()
    {
        return nowChar;
    }

    @Override
    public void scanNumber()
    {
        startPos = nowPos;
        tokenLength = 0;
        if(nowChar == '-')
        {
            tokenLength++;
            next();
        }
        for(;;)
        {
            if(nowChar <= '9' && nowChar >= '0')
            {
                next();
                tokenLength++;
            }
            else
            {
                break;
            }
        }
        boolean isDouble = false;
        if(nowChar == '.')
        {
            tokenLength++;
            next();
            isDouble = true;
            for(;;)
            {
                if(nowChar <= '9' && nowChar >= '0')
                {
                    next();
                    tokenLength++;
                }
                else
                {
                    break;
                }
            }
        }
        if(isDouble)
        {
            token = CybufToken.LITERAL_FLOAT;
        }
        else
        {
            token = CybufToken.LITERAL_INT;
        }
    }

    @Override
    public void scanString()
    {
        startPos = nowPos + 1;
        tokenLength = 0;
        for(;;)
        {
            next();
            if(nowChar == '"')
            {
                break ;
            }
            else if(nowChar == EOF || nowChar == '\n')
            {
                throw new CybufException("Unclosing string");
            }
            tokenLength++;
        }
        next();
        token = CybufToken.LITERAL_STRING;
    }

    @Override
    public String stringValue()
    {
        return text.substring(startPos,startPos + tokenLength);
    }

    @Override
    public Integer integerValue()
    {
        return Integer.parseInt(stringValue());
    }

    @Override
    public Double doubleValue()
    {
        return Double.parseDouble(stringValue());
    }

    @Override
    public void skipWhitespace()
    {
        for(;;)
        {
            if (nowChar == ' ' || nowChar == '\r' || nowChar == '\n' || nowChar == '\t' || nowChar == '\f' || nowChar == '\b')
            {
                next();
            }
            else
            {
                break;
            }
        }
    }
}
