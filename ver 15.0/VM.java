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
        String boolResult="";
        boolean trace = false,isExpression=false;
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
            output="";
            String opcode = "";
            int a,b,addr,regnum,offset = 0,jumpTo=0,loopStart=0,loopEnd=0;
            while(ip<code.length-1){
                ip++;
                opcode=code[ip]; //fetch
                System.out.println("IP: "+ip);
                switch(opcode){
                    case "INTEGER":
                        ip++;
                        try{
                            num[varpointer]=Integer.parseInt(engine.eval(code[ip]).toString());
                            System.out.println("VARIABLE: "+num[varpointer]);
                            varpointer++;
                        }
                        catch(Exception e){
                            
                        }
                        break;
                    case "PRINT" :
                        ip++;
                        if(isExpression){
                            for(int j=0;j<variable.length;j++){
                                if(code[ip].equals(variable[j])){
                                    output+=num[j]+"\n";
                                }
                            }
                            isExpression=false;
                        }
                        else{
                            try{
                                String temp=engine.eval(code[ip]).toString();
                                output+=temp+"\n";
                            }
                            catch(Exception e){
                                System.out.println(e.getMessage());

                            }
                        }
			break;
                    case "IF" :
                        ip++;
                        for(int elifAt=0;elifAt<code.length;elifAt++){
                            if(code[elifAt].equals("ELSEIF")){
                                jumpTo=elifAt;
                            }
                        }
                        if(jumpTo==0){
                            for(int endifAt=0;endifAt<code.length;endifAt++){
                                if(code[endifAt].equals("ENDIF")){
                                    jumpTo=endifAt;
                                }
                            }
                        }
                        try{
                            String boolResult=engine.eval(code[ip]).toString();
                            if(boolResult=="false"){
                                ip=jumpTo;break;
                            }
                            else{
                                ip++;
                            }
                            
                        }
                        catch(Exception e){
                            
                        }
                        break;
                        
                    case "ELSEIF" :
                        ip++;
                        for(int elifAt=0;elifAt<code.length;elifAt++){
                            if(code[elifAt].equals("ELSEIF")){
                                jumpTo=elifAt;
                            }
                        }
                        if(jumpTo==0){
                            for(int endifAt=0;endifAt<code.length;endifAt++){
                                if(code[endifAt].equals("ENDIF")){
                                    jumpTo=endifAt;
                                }
                            }
                        }
                        try{
                            String boolResult=engine.eval(code[ip]).toString();
                            if(boolResult=="false"){
                                ip=jumpTo;break;
                            }
                            else{
                                ip++;
                            }
                            
                        }
                        catch(Exception e){
                            
                        }
                        break;
                    
                    case "ENDIF":
                        break;
                    
                    case "END":
                        break;
                    
                    case "WHILE":
                        loopStart=ip;
                        for(int endwAt=0;endwAt<code.length;endwAt++){
                            if(code[endwAt].equals("ENDLOOP")){
                                loopEnd=endwAt;
                            }
                        }
                        ip++;
                        System.out.println(code[ip]);
                        try{
                            boolResult=engine.eval(code[ip]).toString();
                            if(boolResult=="false"){
                                ip=loopEnd;break;
                            }
                            else{
                                break;
                            }
                        }
                        catch(Exception e){
                            
                        }
                        break;
                    
                    case "ENDLOOP":
                        System.out.println(boolResult);
                        if(boolResult=="false"){
                            break;
                        }
                        else{
                            ip=loopStart-1;
                        }
                        break;
                    default :
                        try{
                            //Overlap new integer value to eliminate decimal
                            for(int i=0;i<code[ip].length();i++){
                                if(code[ip].charAt(i)=='='){
                                    for(int j=0;j<variable.length;j++){
                                        if(code[ip].substring(0, i).equals(variable[j])){
                                            String temp=engine.eval(code[ip]).toString();
                                            for(int k=0;k<temp.length();k++){
                                                if(temp.charAt(k)==46){
                                                    num[j]=Integer.parseInt(temp.substring(0,k));
                                                    System.out.println("NUMJ: "+j+")"+num[j]);
                                                    isExpression=true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            
                        }
                        catch(Exception e){
                            
                        }
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