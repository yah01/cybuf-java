package cybuf.parse;

public interface CybufLexer
{
    char EOF = (char)-1;

    CybufToken token();

    char next();

    void nextKey();

    void nextValue();

    char getCurrent();

    void scanNumber();

    void scanString();

    void scanTrue();

    void scanFalse();

    void skipWhitespace();

    boolean isInvalidKeyChar();

    String stringValue();

    Integer integerValue();

    Double doubleValue();

    Boolean booleanValue();

}
