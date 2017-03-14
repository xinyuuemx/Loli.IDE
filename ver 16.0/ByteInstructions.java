package pl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ByteInstructions {
    public static class Instruction{
        String name;
        int n =0;
        public Instruction(String name){ this(name,0);}
        public Instruction(String name, int nargs){
            this.name = name;
            this.n = nargs;
        }
    }
	//INTEGER
        public static final short INTEGER=1;
        //BASIC SYSTEM FUNC.
        public static final short PRINT=2;
        public static final short IF=3; 
        public static final short ELSEIF=4; 
        public static final short ENDIF=5; 
        public static final short WHILE=6; 
        public static final short ENDLOOP=7; 
        public static final short CALL=8;
        public static final short RET=9;
        public static final short END=10; 
        //    static Instruction[] instruct = new Instruction[] {
//        new Instruction("ACREATE"),
//        new Instruction("ADELETE"),
//        new Instruction("INTEGER"),
//        new Instruction("IADD"),
//        new Instruction("ISUB"),
//        new Instruction("IMUL"),
//        new Instruction("ILT"),
//        new Instruction("IEQ"),
//        new Instruction("BCONST"),
//        new Instruction("BTRUE"),
//        new Instruction("BFALSE"),
//        new Instruction("CCONST"),
//        new Instruction("CFUNC"),
//        new Instruction("LOAD"),
//        new Instruction("STORE"),
//        new Instruction("GLOAD"),
//        new Instruction("GSTORE"),
//        new Instruction("PRINT"),
//        new Instruction("POP"),
//        new Instruction("END"),
//        new Instruction("CALL"),
//        new Instruction("RET"),
//        new Instruction("ARIT"),
//    };
//////////////////////// HASHMAPPING FOR CODE INPUT ////////////////////////////////                
                
//    static final Map<String, Short> BYTECODES;
    static final Map<String, Integer> BYTECODES;
    static {
//    final Map<String, Short> bcode = new HashMap<>();
    final Map<String, Integer> bcode = new HashMap<>();
    
    bcode.put("INTEGER", 1);
    bcode.put("PRINT", 2);
    bcode.put("IF",3);
    bcode.put("ELSEIF",4);
    bcode.put("ENDIF", 5);
    bcode.put("WHILE", 6);
    bcode.put("ENDLOOP",7);
    bcode.put("CALL",8);
    bcode.put("RET",9);
    bcode.put("END",10);
    BYTECODES = Collections.unmodifiableMap(bcode);
    }
}
