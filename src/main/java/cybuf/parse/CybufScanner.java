package cybuf.parse;

import cybuf.CybufException;

public class CybufScanner implements CybufLexer
{
    private int               nowPos;
    private int               startPos;
    private int               tokenLength;
    private int               textLength;
    private char              nowChar;
    private final String      text;
    private CybufToken        token;

    private final static String TRUE  = "true";
    private final static String FALSE = "false";

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
            if(nowChar == 't')
            {
                scanTrue();
                return ;
            }
            if(nowChar == 'f')
            {
                scanFalse();
                return;
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
    public void scanTrue()
    {
        for(int i=0;i<4;++i)
        {
            if(nowChar == TRUE.charAt(i))
            {
                next();
            }
            else
            {
                throw new CybufException("error parse true");
            }
        }
        if (nowChar == ' ' || nowChar == '}' || nowChar == ']' || nowChar == '\n' || nowChar == '\r'
                || nowChar == '\t' || nowChar == '\f' || nowChar == '\b' )
        {
            token = CybufToken.TRUE;
        }
        else
        {
            throw new CybufException("scan true error");
        }
    }

    @Override
    public void scanFalse()
    {
        for(int i=0;i<5;++i)
        {
            if(nowChar == FALSE.charAt(i))
            {
                next();
            }
            else
            {
                throw new CybufException("error parse false");
            }
        }
        if (nowChar == ' ' || nowChar == '}' || nowChar == ']' || nowChar == '\n' || nowChar == '\r'
                || nowChar == '\t' || nowChar == '\f' || nowChar == '\b' )
        {
            token = CybufToken.FALSE;
        }
        else
        {
            throw new CybufException("scan false error");
        }
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
        if(token != CybufToken.LITERAL_INT)
        {
            throw new CybufException("Expect : " + CybufToken.LITERAL_INT);
        }
        return Integer.parseInt(stringValue());
    }

    @Override
    public Double doubleValue()
    {
        if(token != CybufToken.LITERAL_FLOAT)
        {
            throw new CybufException("Expect : " + CybufToken.LITERAL_FLOAT);
        }
        return Double.parseDouble(stringValue());
    }

    @Override
    public Boolean booleanValue()
    {
        if(token == CybufToken.FALSE)
        {
            return Boolean.FALSE;
        }
        else if(token == CybufToken.TRUE)
        {
            return Boolean.TRUE;
        }
        else
        {
            throw new CybufException("Expect : " + CybufToken.FALSE + " or " + CybufToken.TRUE);
        }
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
