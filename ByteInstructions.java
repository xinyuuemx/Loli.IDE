package pl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ByteInstructions {
		public static final short ACREATE=1;
		public static final short ADELETE=2;
		//INTEGER
                public static final short ICONST=3;
                public static final short IADD=4;
		public static final short ISUB=5;
                public static final short IMUL=6;
                public static final short IDIV=7;
                //FLOAT
                public static final short FADD=8;
		public static final short FSUB=9;
                public static final short FMUL=10;
                public static final short FDIV=11;
                //BOOLEAN
                public static final short BCONST=12;
                public static final short BTRUE=13;
		public static final short BFALSE=14;
                //CHAR
                public static final short CCONST=15;
		public static final short CFUNC=16;
                //LOCAL VAR.
                public static final short LOAD=17;	
		public static final short STORE=18;
                //GLOBAL VAR.
                public static final short GLOAD=19;	
		public static final short GSTORE=20;
                //BASIC SYSTEM FUNC.
                public static final short PRINT=21;
                public static final short POP=22;
                public static final short END=23;     
                public static final short HI=24;     

//////////////////////// HASHMAPPING FOR CODE INPUT ////////////////////////////////                
                
//    static final Map<String, Short> BYTECODES;
    static final Map<String, Integer> BYTECODES;
    static {
//    final Map<String, Short> bcode = new HashMap<>();
    final Map<String, Integer> bcode = new HashMap<>();
    
/*    bcode.put("ICONST", (short)1);
    bcode.put("IADD", (short)2);
    bcode.put("ISUB", (short)3);
    bcode.put("IMUL", (short)4);
    bcode.put("IDIV", (short)5);
    
    bcode.put("FCONST", (short)6);
    bcode.put("FADD", (short)7);
    bcode.put("FSUB", (short)8);
    bcode.put("FMUL", (short)9);
    bcode.put("FDIV", (short)10);

    bcode.put("BCONST", (short)11);
    bcode.put("BVALUE", (short)12);

    bcode.put("LOAD", (short)13);
    bcode.put("STORE", (short)14);

    bcode.put("GLOAD", (short)13);
    bcode.put("GSTORE", (short)14);

    bcode.put("PRINT", (short)15);
    bcode.put("POP", (short)16);
    bcode.put("END", (short)17);*/
    
    bcode.put("END", 23);
    bcode.put("HI", 24);
    BYTECODES = Collections.unmodifiableMap(bcode);
    }
}
