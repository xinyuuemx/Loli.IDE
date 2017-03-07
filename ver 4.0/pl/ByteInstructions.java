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
        public static final short ACREATE=1;
        public static final short ADELETE=2;
	//INTEGER
        public static final short ICONST=3;
        public static final short IADD=4;
	public static final short ISUB=5;
        public static final short IMUL=6;
        public static final short ILT=7;
        public static final short IEQ=12;
        //BOOLEAN
        public static final short BR=13;
        public static final short BRT=14;
	public static final short BRF=15;
        //CHAR
        public static final short CCONST=16;
	public static final short CFUNC=17;
        //LOCAL VAR.
        public static final short LOAD=18;	
	public static final short STORE=19;
        //GLOBAL VAR.
        public static final short GLOAD=20;	
	public static final short GSTORE=21;
        //BASIC SYSTEM FUNC.
        public static final short PRINT=22;
        public static final short POP=23;
        public static final short END=24; 
        public static final short CALL=25; 
        public static final short RET=26; 
        //SELECTION INSTRUCTIONS
        public static final short IF=27;
    static Instruction[] instruct = new Instruction[] {
        new Instruction("ACREATE"),
        new Instruction("ADELETE"),
        new Instruction("ICONST"),
        new Instruction("IADD"),
        new Instruction("ISUB"),
        new Instruction("IMUL"),
        new Instruction("ILT"),
        new Instruction("IEQ"),
        new Instruction("BCONST"),
        new Instruction("BTRUE"),
        new Instruction("BFALSE"),
        new Instruction("CCONST"),
        new Instruction("CFUNC"),
        new Instruction("LOAD"),
        new Instruction("STORE"),
        new Instruction("GLOAD"),
        new Instruction("GSTORE"),
        new Instruction("PRINT"),
        new Instruction("POP"),
        new Instruction("END"),
        new Instruction("CALL"),
        new Instruction("RET"),
    };
//////////////////////// HASHMAPPING FOR CODE INPUT ////////////////////////////////                
                
    static final Map<String, Integer> BYTECODES;
    static {
        final Map<String, Integer> bcode = new HashMap<>();


        bcode.put("ACREATE", 1);
        bcode.put("ADELETE", 2);

        bcode.put("ICONST", 3);
        bcode.put("IADD", 4);
        bcode.put("ISUB", 5);
        bcode.put("IMUL", 6);
        bcode.put("ILT", 7);
        bcode.put("IEQ", 12);

        bcode.put("BCONST", 13);
        bcode.put("BVALUE", 14);

        bcode.put("BTRUE", 15);
        bcode.put("CCONST", 16);
        bcode.put("CFUNC", 17);

        bcode.put("LOAD", 18);
        bcode.put("STORE", 19);

        bcode.put("GLOAD", 20);
        bcode.put("GSTORE", 21);

        bcode.put("PRINT", 22);
        bcode.put("POP", 23);
        bcode.put("END", 24);
        bcode.put("CALL", 25);
        bcode.put("RET", 26);
        BYTECODES = Collections.unmodifiableMap(bcode);
    }
}
