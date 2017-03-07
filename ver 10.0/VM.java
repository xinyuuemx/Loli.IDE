package pl;
import java.util.ArrayList;
import java.util.List;
import static pl.ByteInstructions.*;
public class VM {
    
	public static final int DEFAULT_STACK_SIZE = 1000;
	public static final int DEFAULT_CALL_STACK_SIZE = 1000;
	public static final int FALSE = 0;
	public static final int TRUE = 1;
        
        String[][] variables=new String[3][];
	int [] globals;
	int [] code;
	int [] stack;
        String [] variable;
        int[] num = new int[1000];
	int last;
	int ip; //instruction pointer
        int sp=-1; //stack pointer
        int fp; //function pointer
         
        String output="";
        boolean trace = false;
        public VM(int[] code, int nglobals,int stacksize, String[] r){
        this.code=code;
        globals=new int[nglobals];
        stack=new int[stacksize];
        variable = r;
        }
        public void exec(int startip){
            ip = startip;
            cpu();
        }
        public String cpu(){
            output=" ";System.out.println("dtfyg"+output);
            int opcode = 0;
            int a,b,addr,regnum,offset = 0;
            while(ip<code.length){
                opcode=code[ip]; //fetch
                ip++;
                switch(opcode){
                    case ACREATE:
                        break;
                    case ADELETE:
                        break;
                    case INTEGER:
                        int v = code[ip];
                        ip++;
                        num[v] = code[ip];
                        ip++;
                        break;
                    case IADD:
                        b = stack[sp--];   			// 2nd opnd at top of stack
                        a = stack[sp--]; 			// 1st opnd 1 below top
                        stack[++sp] = a + b;      	// push result
                        break;
                    case ISUB:
			b = stack[sp--];
			a = stack[sp--];
                        stack[++sp] = a - b;
			break;
                    case IMUL:
			b = stack[sp--];
			a = stack[sp--];
			stack[++sp] = a * b;
                        break;
                    case ILT :
                        b = stack[sp--];
			a = stack[sp--];
			stack[++sp] = (a < b) ? TRUE : FALSE;
			break;
                    case IEQ :
			b = stack[sp--];
			a = stack[sp--];
			stack[++sp] = (a == b) ? TRUE : FALSE;
			break;
                    case BR :
			ip = code[ip++];
			break;
                    case BRT :
			addr = code[ip++];
			if ( stack[sp--]==TRUE ) ip = addr;
			break;
                    case BRF :
			addr = code[ip++];
			if ( stack[sp--]==FALSE ) ip = addr;
			break;
                    case LOAD : 
                        offset = code[ip++];
                        stack[++sp] = stack[fp+offset];
			break;
                    case GLOAD :// load from global memory
			addr = code[ip++];
			stack[++sp] = globals[addr];
			break;
                    case STORE :
			regnum = code[ip++];
			stack[fp+offset]= stack[sp--];
			break;
                    case GSTORE :
			addr = code[ip++];
			globals[addr] = stack[sp--];
			break;
                    case PRINT :
                        MainP m=new MainP();
                        char c;
                        if(code[ip]>500){
                           output=output+variable[code[ip]].replaceAll("\"", "")+"\n";
                        }
                        else{
			output += num[code[ip]];
                        ip++;
                        }
			break;
                    case POP:
			--sp;
			break;
                    case CALL :
                        addr = code[ip++];
                        int nargs = code[ip++];
                        stack[++sp] = nargs;
                        stack[++sp] = fp;
                        stack[++sp] = ip;
                        nargs = stack[sp--];
                        fp=sp;
                        ip=addr;
			// expects all args on stack
			break;
                    case RET:
                        int rvalue = stack[sp--];
                        sp=fp;
                        ip = stack[sp--];
                        fp = stack[sp--];
                        nargs = stack[sp--];
                        sp -= nargs;
                        stack [++sp] = rvalue;
                        break;
                    case END:
                        break;
//                    default :
//			throw new Error("invalid opcode: "+opcode+" at ip="+(ip-1));
                }
                if(trace){
                    System.err.printf("%-35S",disassemble(opcode));
                    System.err.println(stackString());
                }
            }
                    dumpDataMemory();
                    last = opcode;
                    System.out.println(output);
                    return output;
        }
    protected String stackString(){
        StringBuilder buf = new StringBuilder();
        buf.append("stack=[");
        for(int i =0;i<=sp;i++){
            int o = stack[i];
            buf.append("");
            buf.append(o);
        }
        buf.append("]");
        return buf.toString();
    }
    protected String disassemble(int opcode){
        String opName = ByteInstructions.instruct[opcode-1].name;
        StringBuilder buf = new StringBuilder();
        buf.append(String.format("%04d:\t%-11s", ip, opName));
        int nargs = ByteInstructions.instruct[opcode].n;
        if(nargs>0){
            List<String> operands = new ArrayList<>();
            for(int i = ip; i<=ip+nargs;i++){
               operands.add(String.valueOf(code[i]));
            }
            for(int i = 0; i<operands.size();i++){
                String s = operands.get(i);
                if(i>0) buf.append(", ");
                buf.append(s);
            }
        }
        return buf.toString();
    }
    protected void dumpDataMemory(){
        System.err.println("Data Memory: ");
        int addr = 0;
        for(int o : globals){
            System.err.printf("0%4d: %d\n", addr, o);
        }
        System.out.println();
    }
    protected void dumpCodeMemory(){
        System.err.println("Code Memory: ");
        int addr = 0;
        for(int o : code){
            System.err.printf("0%4d: %d\n", addr, o);
        }
        System.out.println();
    }
}