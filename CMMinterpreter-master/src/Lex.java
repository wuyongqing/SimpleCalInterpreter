import java.io.*;
import java.util.ArrayList;

public class Lex {
    static int count = 0;
    static int lineNo = 1;
    static char[] inputChar;
    static char currentChar;
    static ArrayList<Token> tokens = new ArrayList<>();//创建的token序列
    static StringBuilder sb = new StringBuilder();

    public static ArrayList<Token> lexAnalysis(String pathName) {

        String input = readTxtFile(pathName);//文件内容
        inputChar = input.toCharArray();//文件内容的char[]表示
        //readChar();//等下看要不要加这句话
        makeTokenList();
        display();
        return tokens;
    }

    public static String readTxtFile(String filePath) {
        StringBuffer str = new StringBuffer("");
        File file = new File(filePath);
        try {
            FileReader fr = new FileReader(file);
            int ch = 0;
            while ((ch = fr.read()) != -1) {
                System.out.print((char) ch + "");
                str.append((char) ch + "");
            }
            System.out.println();
            str.append('#');
            fr.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("File reader出错");
        }
        return str.toString();

    }//读入文件，返回所有string

    public static void readChar() {
//        if(inputChar[count]!='#') {
            currentChar = inputChar[count++];
            if (currentChar == '\n') lineNo++;
//        }else{
//            System.exit(-1);
//        }
    }//currentChar指向下一位

    public static void makeTokenList() {
        readChar();
        while(true) {
            //readChar();//从char[]中读取一个字符保存到currentChar中
            if (currentChar == ';') {
                tokens.add(new Token(Token.SEMI,";", lineNo));readChar();continue;
            }
            if (currentChar == '+') {
                tokens.add(new Token(Token.PLUS,"+", lineNo));readChar();continue;
            }
            if (currentChar == '-') {
                tokens.add(new Token(Token.MINUS,"-", lineNo));readChar();continue;
            }
            if (currentChar == '*') {
                tokens.add(new Token(Token.MUL,"*", lineNo));readChar();continue;
            }
            if (currentChar == '(') {
                tokens.add(new Token(Token.LPARENT, "(",lineNo));readChar();continue;
            }
            if (currentChar == ')') {
                tokens.add(new Token(Token.RPARENT,")", lineNo));readChar();continue;
            }
            if (currentChar == '[') {
                tokens.add(new Token(Token.LBRACKET, "[",lineNo));readChar();continue;
            }
            if (currentChar == ']') {
                tokens.add(new Token(Token.RBRACKET, "]",lineNo));readChar();continue;
            }
            if (currentChar == '{') {
                tokens.add(new Token(Token.LBRACE, "{",lineNo));readChar();continue;
            }
            if (currentChar == '}') {
                tokens.add(new Token(Token.RBRACE,"}", lineNo));readChar();continue;
            }//如果currentChar是+-*()[]{}
            if (currentChar == '/') {
                readChar();
                if (currentChar == '*') {
                    tokens.add(new Token(Token.LCOM,"/*", lineNo));
                    readChar();
                    while (true) {
                        if (currentChar == '*') {
                            readChar();
                            if (currentChar == '/') {
                                tokens.add(new Token(Token.RCOM,"*/", lineNo));
                                readChar();
                                break;
                            }
                        } else {
                            readChar();
                        }
                    }
                    continue;
                } else if (currentChar == '/') {
                    tokens.add(new Token(Token.SCOM,"//", lineNo));
                    while (currentChar != '\n') {
                        readChar();
                    }
                    readChar();
                    continue;
                } else {//除号
                    tokens.add(new Token(Token.DIV,"/", lineNo));
                    readChar();
                    continue;
                }
            }//如果currentChar是/
            if (currentChar == '=') {
                readChar();
                if (currentChar == '=') {
                    tokens.add(new Token(Token.EQ, "==",lineNo));
                    readChar();
                    continue;
                } else {
                    tokens.add(new Token(Token.ASSIGN,"=", lineNo));
                    readChar();
                    continue;
                }
            }
            if (currentChar == '>') {
                readChar();
                if (currentChar == '=') {
                    tokens.add(new Token(Token.GET,">=", lineNo));
                    readChar();
                    continue;
                } else {
                    tokens.add(new Token(Token.GT,">", lineNo));
                    readChar();
                    continue;
                }
            }
            if (currentChar == '<') {
                readChar();
                if (currentChar == '=') {
                    tokens.add(new Token(Token.LET, "<=",lineNo));
                    readChar();
                    continue;
                } else if (currentChar == '>') {
                    tokens.add(new Token(Token.NEQ, "<>",lineNo));
                    readChar();
                    continue;
                } else {
                    tokens.add(new Token(Token.LT,"<", lineNo));
                    readChar();
                    continue;
                }
            }//如果currentChar是<>=
            if (currentChar >= '0' && currentChar <= '9') {
                boolean isReal = false;//是否小数
                while ((currentChar >= '0' && currentChar <= '9') || currentChar == '.') {
                    if (currentChar == '.') {
                        if (isReal) {
                            break;
                        } else {
                            isReal = true;
                        }
                    }
                    sb.append(currentChar);
                    readChar();
                }
                if (isReal) {
                    tokens.add(new Token(Token.LITERAL_REAL, sb.toString(), lineNo));readChar();
                } else {
                    tokens.add(new Token(Token.LITERAL_INT, sb.toString(), lineNo));readChar();
                }
                sb.delete(0, sb.length());
                continue;
            }//如果currentChar是int or real
            if(currentChar>='A'&&currentChar<='Z'||currentChar>='a'&&currentChar<='z'||currentChar=='_'){
                if(currentChar=='i'&&inputChar[count]=='n'&&inputChar[count+1]=='t'){
                    readChar();readChar();readChar();
                    tokens.add(new Token(Token.INT,"INT", lineNo));
                    continue;
                }else{
                    while (true) {
                        if (currentChar>='A'&&currentChar<='Z'||currentChar>='a'&&currentChar<='z'||currentChar=='_'||currentChar>='0'&&currentChar<='9') {
                            sb.append(currentChar);
                            readChar();
                        }else{
                            tokens.add(new Token(Token.ID,sb.toString(),lineNo));
                            readChar();
                            sb.delete(0,sb.length());
                            break;
                        }
                    }
                }
            }//如果currentChar是数字
            if(currentChar=='#') break;
            if(currentChar==' '||currentChar=='\r'||currentChar=='\n'||currentChar=='\f')   readChar();
        }
    }

    public static void display(){
        for(Token t : tokens){
            System.out.println("["+t.tokenNo+"\t"+t.value+"\t"+t.lineNo+"]");
        }
    }
}