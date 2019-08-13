public class Token {
    public int tokenNo;
    public String value="";
    public int lineNo;




    public Token(int tokenNo,int lineNo){
        this.tokenNo = tokenNo;
        this.lineNo = lineNo;
    }
    public Token(int tokenNo,String value,int lineNo){
        this.tokenNo = tokenNo;
        this.value = value;
        this.lineNo = lineNo;
    }
    /** if */
    public static final int IF = 1;
    /** else */
    public static final int ELSE = 2;
    /** while */
    public static final int WHILE = 3;
    /** read */
    public static final int READ = 4;
    /** write */
    public static final int WRITE = 5;
    /** int */
    public static final int INT = 6;
    /** real */
    public static final int REAL = 7;
    /** + */
    public static final int PLUS = 8;
    /** - */
    public static final int MINUS = 9;
    /** * */
    public static final int MUL = 10;
    /** / */
    public static final int DIV = 11;
    /** = */
    public static final int ASSIGN = 12;
    /** < */
    public static final int LT = 13;
    /** == */
    public static final int EQ = 14;
    /** <> */
    public static final int NEQ = 15;
    /** ( */
    public static final int LPARENT = 16;
    /** ) */
    public static final int RPARENT = 17;
    /** ; */
    public static final int SEMI = 18;
    /** { */
    public static final int LBRACE = 19;
    /** } */
    public static final int RBRACE = 20;
//    /** /* */
    public static final int LCOM = 21;
//    /** *\/ */
    public static final int RCOM = 22;
//    /** // */
    public static final int SCOM = 23;
    /** [ */
    public static final int LBRACKET = 24;
    /** ] */
    public static final int RBRACKET = 25;
    /** <= */
    public static final int LET = 26;
    /** > */
    public static final int GT = 27;
    /** >= */
    public static final int GET = 28;
    /** 标识符,由数字,字母或下划线组成,第一个字符不能是数字 */
    public static final int ID = 29;
    /** int型字面值 */
    public static final int LITERAL_INT = 30;
    /** real型字面值 */
    public static final int LITERAL_REAL = 31;

}
