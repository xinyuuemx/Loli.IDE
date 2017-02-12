package pl;

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
}
