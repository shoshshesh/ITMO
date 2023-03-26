package main;

public enum Token {
    TYPE_OR_NAME, LBRACKET, RBRACKET, SEMICOLON, COMA, END, POINTER, L_SQUARE, R_SQUARE;

    @Override
    public String toString() {
        String result = "";
        switch (this) {
            case TYPE_OR_NAME -> result = "type_or_name";
            case LBRACKET     -> result = "(";
            case RBRACKET     -> result = ")";
            case SEMICOLON    -> result = ";";
            case COMA         -> result = ",";
            case END          -> result = "''";
            case POINTER      -> result = "*";
            case L_SQUARE     -> result = "[";
            case R_SQUARE     -> result = "]";
        }
        return result;
    }
}

//package main;
//
//public enum Token {
//    TYPE_AND_NAME, LBRACKET, RBRACKET, SEMICOLON, COMA, END;
//
//    @Override
//    public String toString() {
//        String result = "";
//        switch (this) {
//            case TYPE_AND_NAME -> result = "type_name";
//            case LBRACKET      -> result = "(";
//            case RBRACKET      -> result = ")";
//            case SEMICOLON     -> result = ";";
//            case COMA          -> result = ",";
//            case END           -> result = "''";
//        }
//        return result;
//    }
//}
