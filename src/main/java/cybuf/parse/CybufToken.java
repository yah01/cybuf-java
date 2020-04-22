package cybuf.parse;

public enum CybufToken
{
    LPAREN,     // ("("),

    RPAREN,     // (")"),

    LBRACE,     // ("{"),

    RBRACE,     // ("}"),

    LBRACKET,   // ("["),

    RBRACKET,   // ("]"),

    COMMA,      // (","),

    COLON,      // (":"),

    LITERAL_INT,

    LITERAL_FLOAT,

    LITERAL_STRING,

    LITERAL_CHAR,

    IDENTIFIER,

    ERROR,

    TRUE,

    FALSE,

    NIL,
}
