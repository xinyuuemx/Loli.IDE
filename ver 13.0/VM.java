package pl;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import static pl.ByteInstructions.*;
public class VM {
    
	public static final int DEFAULT_STACK_SIZE = 1000;
	public static final int DEFAULT_CALL_STACK_SIZE = 1000;
	public static final int FALSE = 0;
	public static final int TRUE = 1;
              
	int[] globals;
	int[] stack;
        String[] code;
	String[] variable;
        int[] num = new int[1000];
	int last;
	int ip=-1; //instruction pointer
        int sp=-1; //stack pointer
        int fp; //function pointer
        int varpointer=0;
        String output="";
        boolean trace = false;
        public VM(String[] code, int nglobals,int stacksize, String[] r){
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
            System.out.println("CPU HERE!");
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            output=" ";
            String opcode = "";
            int a,b,addr,regnum,offset = 0;
            while(ip<code.length-1){
                ip++;
                opcode=code[ip]; //fetch
                System.out.println("IP: "+ip);
                switch(opcode){
                    case "INTEGER":
                        ip++;
                        try{
                            System.out.println("IP: "+ip);
                            num[varpointer]=Integer.parseInt(engine.eval(code[ip]).toString());
                            System.out.println("VARIABLE: "+num[varpointer]);
                            varpointer++;
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "PRINT" :
                        ip++;
                        try{
                            System.out.println("IP: "+ip);
                            output+=engine.eval(code[ip]);
                        }
                        catch(Exception e){
                            
                        }
			break;
                    
                    case "END":
                        break;
//                    default :
//			throw new Error("invalid opcode: "+opcode+" at ip="+(ip-1));
                }
                if(trace){
                    //System.err.printf("%-35S",disassemble(opcode));
                    //System.err.println(stackString());
                }
            }
                    //dumpDataMemory();
                    //last = opcode;
                    System.out.println(output);
                    return output;
        }
//    protected String stackString(){
//        StringBuilder buf = new StringBuilder();
//        buf.append("stack=[");
//        for(int i =0;i<=sp;i++){
//            int o = stack[i];
//            buf.append("");
//            buf.append(o);
//        }
//        buf.append("]");
//        return buf.toString();
//    }
//    protected String disassemble(int opcode){
//        String opName = ByteInstructions.instruct[opcode-1].name;
//        StringBuilder buf = new StringBuilder();
//        buf.append(String.format("%04d:\t%-11s", ip, opName));
//        int nargs = ByteInstructions.instruct[opcode].n;
//        if(nargs>0){
//            List<String> operands = new ArrayList<>();
//            for(int i = ip; i<=ip+nargs;i++){
//               operands.add(String.valueOf(code[i]));
//            }
//            for(int i = 0; i<operands.size();i++){
//                String s = operands.get(i);
//                if(i>0) buf.append(", ");
//                buf.append(s);
//            }
//        }
//        return buf.toString();
//    }
//    protected void dumpDataMemory(){
//        System.err.println("Data Memory: ");
//        int addr = 0;
//        for(int o : globals){
//            System.err.printf("0%4d: %d\n", addr, o);
//        }
//        System.out.println();
//    }
//    protected void dumpCodeMemory(){
//        System.err.println("Code Memory: ");
//        int addr = 0;
//        for(int o : code){
//            System.err.printf("0%4d: %d\n", addr, o);
//        }
//        System.out.println();
//    }
}