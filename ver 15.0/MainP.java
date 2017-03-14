
package pl;

import java.util.ArrayList;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import static pl.ByteInstructions.*;

public class MainP {
    
 
    static ArrayList<Integer> clist = new ArrayList();
    String[] variable = new String[1000];
    String[] varcopy = new String[1000];
    String[] cleancode;
    int vp = -1, vp1=0, runn=0;
    boolean run=true;
    int vp2 = 1000;
    static String code="";
    String output="", o;
    String[] errors={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    int[] line=new int[50];
    int numberOferr=0,errorCount=0,errline, errctr=1,y=1;
    boolean hasErrors=false;
    
    
    void codetext(String s, String wholecode){

        code=s;
        int x=0,counter=0;
//        while(x<code.length()){
//            if(code.charAt(x)==' '){
//                counter++;
//            }
//            x++;
//        }
        
        String[] codearr=new String[100];             //Declare an array of string with size according to number of spaces
        while(x<100){
            codearr[x]="";
            x++;
        }x=0;
        char[] cd=code.toCharArray();
        for(x=0;x<cd.length;x++){
            if(cd[x]=='\"'){
                int y=x+1;
                while(cd[y]!='\"'){
                    if(cd[y]==' '){
                        cd[y]='~';
                    }
                    y++;
                }
                x=y+1;
            }
        }
        code=""; 
        for(x=0;x<cd.length;x++){
            code+=cd[x];
        }
        
        System.out.println("ASO"+code);
        codearr=code.split(" ");                            //Store code into an array of strings
        for(int m=0;m<codearr.length;m++){
            char[] t=codearr[m].toCharArray();
            for(int n=0;n<codearr[m].length();n++){
                if(codearr[m].charAt(n)=='~'){
                    t[n]=' ';
                }
            }
            codearr[m]="";
            for(int n=0;n<t.length;n++){
            codearr[m]+=t[n];
            }
        }
            
        System.out.println("ASDFG");
        for(int i=0; i<codearr.length;i++){
            System.out.print(i+")"+codearr[i]+" ");          
        }
        System.out.println();
        
        String tempo=wholecode;
        x=0;
        int check=0, check2=0, l=0;
        //String symbols="! @ # $ % ^ & * ( ) | { } [ ] : ' , . / ? _ \" \\";
        String symbols="! @ # $ % ^ & * ( ) - + { } [ ] | \\ : ; \" ' < > , . _ ? / ~ `";
        String symbols2="! @ # $ % ^ & * ( ) - + { } [ ] | \\ : ; ' < > , . _ ? / ~ `";
        String[] symbolss=symbols.split(" ");
        String[] symbolss2=symbols2.split(" ");
        String vOfIntvar="", nOfIntvar="";
        // ERROR CHECKING
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        while(x<codearr.length){
            switch(codearr[x]){
                
                case "INTEGER":
                           
                    try{
                        check=BYTECODES.get(codearr[x+1]);
                        run=false;
                        errline=x+1;
                        lineNumberErr(tempo);
                        errorDetected("ERROR: Please declare variable identifier and value.");
                        errctr=1;y=1;
                    }
                    catch(NullPointerException npe){
                        boolean h=false;
                        
                        //Check if variable declaration does not include initialization
                        if(!codearr[x+1].contains("=")){
                            errline=x+1;
                            lineNumberErr(tempo);
                            errorDetected("VARIABLE DECLARATION ERROR: 'INTEGER' variable must have initial integer value.");
                            run=false;
                        }
                        
                        //Variable identifier error checker for symbols
                        for(l=0; l<codearr[x+1].length(); l++){
                            if(codearr[x+1].charAt(l)=='='){
                                System.out.println("DECLARATION: "+codearr[x+1]);
                                vOfIntvar = codearr[x+1].substring(l+1);
                                nOfIntvar = codearr[x+1].substring(0,l);
                                for(int i=0;i<symbolss.length;i++){
                                    if(nOfIntvar.contains(symbolss[i])){
                                        errline=x+1;
                                        lineNumberErr(tempo);
                                        errorDetected("VARIABLE IDENTIFIER ERROR: Variable name/identifier must not contain symbols");
                                        run=false;
                                        break;
                                    }
                                }
                                System.out.println(nOfIntvar);
                                System.out.println("VARIABLE: value -- "+vOfIntvar+", name -- "+nOfIntvar);
                        
                        //Variable value error checker
                                if(vOfIntvar==null){
                                    errline=x+1;
                                    lineNumberErr(tempo); 
                                    errorDetected("VARIABLE DECLARATION ERROR: 'INTEGER' variable declaration requires specified integer value.");
                                    errctr=1;y=1;
                                    run=false; 
                                    check2=0;
                                }
                                else if(!isNumeric(vOfIntvar)){
                                    errline=x+1;
                                    lineNumberErr(tempo);
                                    errorDetected("VARIABLE DECLARATION ERROR: 'INTEGER' variable requires specified integer value.");
                                    errctr=1;y=1;run=false; 
                                    check2=0;
                                }
                                else{
                                     check2=1;
                                    }
                            }  
                        }
                        if(check2==1){
                            //Check if variable already exists then store new value
                            if(isVar(nOfIntvar)){
                                h=true;
                                errline=x+1;
                                lineNumberErr(tempo);
                                errorDetected("VARIABLE VALUE ERROR: "+nOfIntvar+" has already been declared.");
                                errctr=1;y=1;run=false;
                            }
                            //If variable does not exist
                            else{
                                try{
                                engine.eval(codearr[x+1]);
                                }
                                catch(Exception e){
                                    
                                }
                                vp++;
                                variable[vp]=nOfIntvar;
                            }
                        } 
                    }
                    break;
                    
                case "PRINT":
                    
                    try{
                        check=BYTECODES.get(codearr[x+1]);
                        run=false;
                        errline=x+1;
                        lineNumberErr(tempo);
                        errorDetected("INSTRUCTION SYNTAX ERROR: 'PRINT' requires string literal or variable value after instruction declaration.");
                        errctr=1;y=1;
                    }
                    catch(NullPointerException npe){
                        //'PRINT' value error checker for symbols
                        boolean noErrors=true;
                        for(int i=0;i<symbolss2.length;i++){
                            if(codearr[x+1].contains(symbolss2[i])){
                                errline=x+1;
                                lineNumberErr(tempo);
                                errorDetected("INSTRUCTION SYNTAX ERROR: 'PRINT <\"string\">' or 'PRINT <variable>'");
                                run=noErrors=false;
                                break;
                            }
                        }
                       
                        //checks if string literal or variable
                        if(!isVar(codearr[x+1])){
                            //if not variable then check if enclosed by double quotation marks
                            if(codearr[x+1].charAt(0) != '\"' || codearr[x+1].charAt(codearr[x+1].length()-1) != '\"' ){
                                lineNumberErr(tempo);
                                errorDetected("INSTRUCTION SYNTAX ERROR: 'PRINT' string literal requires enclosure by double quotation marks.");
                                run=noErrors=false;
                                break;
                            }    
                        }
                       
                    }
                    break;
                
                case "IF":
                    int ifcounter=0,endifcounter=0;
                    try{
                        check=BYTECODES.get(codearr[x+1]);
                        run=false;
                        errline=x+1;
                        lineNumberErr(tempo);
                        errorDetected("INSTRUCTION SYNTAX ERROR: 'IF' requires conditional statement after instruction declaration.\n"
                                    + "                  Syntax: IF <condition> THEN <executable statements> ENDIF");
                        errctr=1;y=1;
                    }
                    catch(NullPointerException npe){
                        //counter for nested if
                        for(int i=x;i<codearr.length;i++){
                            if(codearr[i]=="IF"){
                                ifcounter++;
                            }
                            if(codearr[i]=="ENDIF"){
                                endifcounter++;
                            }
                        }
                        //check if all IF instruction are enclosed by ENDIF
                        if(ifcounter!=endifcounter){
                            run=false;
                            errline=x+1;
                            lineNumberErr(tempo);
                            errorDetected("INSTRUCTION SYNTAX ERROR: 'IF' instruction requires 'ENDIF' enclosure.");
                            errctr=1;y=1;
                        }
                        else{
                            //check if the next statement after IF is a conditional that only returns true or false
                            try{
                                String b=engine.eval(codearr[x+1]).toString();
                                System.out.println("alam"+b);
                                //check if condtional has dual < or > symbols
                                if(!(b.equals("true") || b.equals("false"))){
                                    run=false;
                                    errline=x+1;
                                    lineNumberErr(tempo);
                                    errorDetected("INSTRUCTION SYNTAX ERROR: Invalid conditional statement after 'IF' instruction.");
                                    errctr=1;y=1;
                                }
                                else{
                                    if(!codearr[x+2].equals("THEN")){
                                        errorDetected("INSTRUCTION SYNTAX ERROR: Missing instruction - 'THEN'\n"
                                                     +"                  Syntax: IF <condition> THEN <executable statements> ENDIF");
                                    }
                                }
                            }
                            catch(Exception e){
                                System.out.println(e.getMessage());
                                run=false;
                                errline=x+1;
                                lineNumberErr(tempo);
                                errorDetected("INSTRUCTION SYNTAX ERROR: Invalid conditional statement after 'IF' instruction.");
                                errctr=1;y=1;
                            }
                            
                        }
                    }
                    
                case "ELSEIF":
                    //check if 'IF' instruction has been called
                    boolean validElif=false;
                    for(int i=x;i>=0;i--){
                        if(codearr[i]=="IF"){
                            validElif=true;break;
                        }
                    }
                    if(!validElif){
                        errline=x+1;
                        lineNumberErr(tempo);
                        errorDetected("INSTRUCTION SYNTAX ERROR: 'ELSEIF' instruction must come after 'IF' instruction.\n"+
                                      "Syntax: IF <condition> THEN <executable statements> ELSEIF <executable statements> ... ENDIF");
                        errctr=1;y=1;
                    }
                    break;
                
                case "ENDIF":
                    break;
                    
                case "WHILE":
                    break;
                    
                case "ENDLOOP":
                    break;
                    
                case "END":
                    break;
                    
                case "THEN":
                    break;
                    
                default:
//                    if(codearr[x].charAt(0)=='\"'){
//                        break;
//                    }
                    try{
                        engine.eval(codearr[x]);
                    }
                    catch(Exception e){
                        run=false;
                        errline=x+1;
                        lineNumberErr(tempo);
                        errorDetected("EXPRESSION SYNTAX ERROR: Invalid expression. Cannot process.");
                        System.out.println("EXPERROR :"+e.getMessage());
                        errctr=1;y=1;
                    }
            }
            x++;  
        }
        System.out.println("mali: "+errorCount);
        int pp=0;
        System.out.print("CLEANED STRING ARRAY: ");
        while(pp<codearr.length){
           System.out.print(codearr[pp]+" ");
            pp++;
        }
        System.out.println();
////////////////////////// PARSING /////////////////////////////////////////////////////////////
        if(run==true){
            cleancode=codearr;
        }
        
        else{            
            for(int i=0;i<errorCount;i++){
                output+="Line "+line[i]+" -- "+errors[i];
                System.out.println(errors[i]);
            }
        }
    
}
    
    public void exe(String s,String wholecode){
        String[] temp= new String[10];
        runn++;
        int i=0,j=0, k=0;
        String obj=""; 
        int chk=0;
        while(i<10){
            temp[i]=""; i++;
        }
        for(i=0;i<s.length();i++){
            if(s.charAt(i)=='\"'){
                if(k%2==0){
                    temp[k]=s.substring(j, i);
                    j=i; k++;
                }
                else{
                    temp[k]=s.substring(j, i+1);
                    j=i+1; k++;
                }
                chk=1;
            }
        }
        temp[k]=s.substring(j, s.length());
        if(chk==0){
                temp[k]=s;
        }
        j=0;
        int l=0;
        if(k%2!=0){
            for(i=0;i<s.length();i++){if(s.charAt(i)=='\n'){errctr++;}else if(s.charAt(i)=='\"'){j++;if(j==k){break;} }}errorDetected("INSTRUCTION SYNTAX ERROR: Misplaced double quotation mark.");errctr=1;run=false;}
        else{
            for(i=0;i<=k;i++){
                if(temp[i].charAt(0)!='\"'){   
                    temp[i] = temp[i].trim().replaceAll("\n"," ").replaceAll(" +", " ");          //Replace all linebreaks in code with whitespace and excess whitespace remover        
                    temp[i]+=" ";
                }
                else{
                    temp[i]+=" ";
                }
            }
        }
        s="";
        for(i=0;i<temp.length;i++){
            s+=temp[i];
        }
        chk=0;
///////////////////////////////////////// 
        char sArr[]=s.toCharArray();
        //Trim spaces on both ends of assignment operator
        for(i=0;i<s.length();i++){
            if(sArr[i]=='='){
                if(sArr[i-1]==' '){
                    sArr[i-1]='^';
                }
                if(sArr[i+1]==' '){
                    sArr[i+1]='^';
                }
            }
        }
        //Convert clean char array to string
        s="";
        for(i=0;i<sArr.length;i++){
            if(sArr[i]!='^'){
                s+=sArr[i]+"";
            }
        }
        s=s.replaceAll("=+", "=");
//////////////////////////////////////////////        
        codetext(s,wholecode);                                                      //Process code input
        
        System.out.println("FINAL: "+output);
        if(errorCount==0){
            System.out.println("CLEANCODE:");
            System.out.println();
            for (i=0; i < cleancode.length; i++)
            {
                System.out.println(cleancode[i]);
            }
            System.out.println();
            VM vm=new VM(cleancode,0,200,variable);
            vm.trace = false;
            output=vm.cpu();
            o=obj;
        }
    }

    void errorDetected(String err){
        hasErrors=true;
        System.out.println("ERROR AT "+errorCount);
        line[errorCount]=errctr;
        errors[errorCount]=err+"\n";
        errorCount++;
    }
    
    public static boolean isNumeric(String str)  
    {  
      try  
      {  
        int i = Integer.parseInt(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
    
    public void lineNumberErr(String temp){
        int  z=0, k=0, j=0;
        for(k=0;k<temp.length();k++){
            if(temp.charAt(k)==' '||temp.charAt(k)=='\n'){
                y++;
                if(y==errline){
                    z=k;
                    k=temp.length();
                }
            }
            else if(Character.isDigit(temp.charAt(k))){
                y--;
            }
        }
        for(j=0;j<=z;j++){
            if(temp.charAt(j)=='\n'){
                errctr++;
            }
        }
    }
    
    public boolean isVar(String var){
        int i=0;
        boolean check=false;
        while(i<variable.length){
            if(var.equals(variable[i])){
                check=true;break;
            }
            i++;
        }
        System.out.println("var: "+check);
        return check;
    }
}
        


