
package pl;

import java.util.ArrayList;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import static pl.ByteInstructions.*;

public class MainP {
    
 
    static ArrayList<Integer> clist = new ArrayList();
    String[] variable = new String[1000];
    String[] varcopy = new String[1000];
    int vp = -1, vp1=0, runn=0;
    int vp2 = 1000;
    static String code="";
    String output="", o;
    String[] errors={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    int[] line=new int[50];
    int numberOferr=0,errorCount=0,errline, errctr=1,y=1;
    boolean hasErrors=false;
    
    
    void codetext(String s, String wholecode){
       
        boolean run=true;
        code=s;
        int x=0,counter=0;
        while(x<code.length()){
            if(code.charAt(x)==' '){
                counter++;
            }
            x++;
        }
        
        String[] codearr=new String[counter+1];             //Declare an array of string with size according to number of spaces
        int[] syn=new int[codearr.length];                  //Declare an array of integer with size according to codearr[]'s size 
        codearr=code.split(" ");                            //Store code into an array of strings

        String tempo=wholecode;
        x=0;
        int check=0, check2=0, l=0;
        String symbols="! @ # $ % ^ & * ( ) | { } [ ] : ' , . / ? _ \" \\";
        String[] symbolss=symbols.split(" ");
        String vOfIntvar="", nOfIntvar="";
        // ERROR CHECKING
        while(x<codearr.length){
            switch(codearr[x]){
                
                case "INTEGER":
                    ScriptEngineManager mgr = new ScriptEngineManager();
                    ScriptEngine engine = mgr.getEngineByName("JavaScript");
                           
                    try{
                        check=BYTECODES.get(codearr[x+1]);
                        run=false;
                        errline=x+1;
                        lineNumberErr(tempo);
                        errorDetected("ERROR: Please declare variable identifier and value.",errctr);
                        errctr=1;y=1;
                    }
                    catch(NullPointerException npe){
                        boolean h=false;   
                        //Variable identifier error checker for symbols
                        for(int i=0;i<symbolss.length;i++){
                            if(codearr[x+1].contains(symbolss[i])){
                                errline=x+1;
                                lineNumberErr(tempo);
                                errorDetected("VARIABLE IDENTIFIER ERROR: Variable name/identifier must not contain symbols",errctr);
                                run=false;
                                break;
                            }
                        }
                        for(l=0; l<codearr[x+1].length(); l++){
                            if(codearr[x+1].charAt(l)=='='){
                                System.out.println("DECLARATION: "+codearr[x+1]);
                                vOfIntvar = codearr[x+1].substring(l+1);
                                nOfIntvar = codearr[x+1].substring(0,l);
                                System.out.println("VARIABLE: value -- "+vOfIntvar+", name -- "+nOfIntvar);
                                if(vOfIntvar==null){
                                    errline=x+1;
                                    lineNumberErr(tempo); 
                                    errorDetected("VARIABLE VALUE ERROR: 'INTEGER' variable requires specified integer value.",errctr);
                                    errctr=1;y=1;
                                    run=false; 
                                    check2=0;
                                }
                                else if(!isNumeric(vOfIntvar)){
                                    errline=x+1;
                                    lineNumberErr(tempo);
                                    errorDetected("VARIABLE VALUE ERROR: 'INTEGER' variable requires specified integer value.",errctr);
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
                            x++;
//                            for(int i=0;i<vp;i++){
//                                if(nOfIntvar.equals(variable[i])){ 
//                                    String t = i+"";
//                                    t+=codearr[x+1].substring(l);
//                                    System.out.println("STORED: "+t);
//                                    i=vp+1;
//                                    h=true;
//                                }
//                            }
                            if(isVar(nOfIntvar)){
                                h=true;
                                errline=x+1;
                                lineNumberErr(tempo);
                                errorDetected("VARIABLE VALUE ERROR: "+nOfIntvar+" has already been declared.",errctr);
                                errctr=1;y=1;run=false;
                            }
                            //If variable does not exist
                            else{
                                vp++;
                                variable[vp]=nOfIntvar;
                                String t = vp+"";
                                t+=codearr[x+1].substring(l);
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
                        errorDetected("INSTRUCTION SYNTAX ERROR: 'PRINT' requires string literal or variable value after instruction declaration.",errctr);
                        errctr=1;y=1;
                    }
                    catch(NullPointerException npe){
                        //'PRINT' value error checker for symbols
                        boolean noErrors=true;
                        char[] symbolsss=symbolss.toString().toCharArray();
                        for(int i=0;i<symbolss.length;i++){
                            if(codearr[x+1].charAt(0)==symbolsss[i]){
                                errline=x+1;
                                lineNumberErr(tempo);
                                errorDetected("INSTRUCTION SYNTAX ERROR: 'PRINT <\"string\">' or 'PRINT <variable>'",errctr);
                                run=noErrors=false;
                                break;
                            }
                        }
                       
                        //checks if string literal or variable
                        if(!isVar(codearr[x+1])){
                            //if not variable then check if enclosed by double quotation marks
                            if(codearr[x+1].charAt(0) != '\"' || codearr[x+1].charAt(codearr[x+1].length()-1) != '\"' ){
                                lineNumberErr(tempo);
                                errorDetected("INSTRUCTION SYNTAX ERROR: 'PRINT' string literal requires enclosure by double quotation marks.",errctr);
                                run=noErrors=false;
                                break;
                            }
//                            if(noErrors){
//                                x++;
//                                for(int i=0;i<=vp;i++){
//                                    System.out.println(codearr[x]);
//                                    if(codearr[x].equals(variable[i])){
//                                        codearr[x]=variable[i];
//                                        i=vp;
//                                        break;
//                                    }
//                                }
//                           
//                            }
                            else{
                                x++;
                                vp2--;
                                variable[vp2]=codearr[x];
                                codearr[x]=vp2+"";
                            }    
                        }
                        else{ //if variable just print
                            x++;
                                for(int i=0;i<=vp;i++){
                                    if(codearr[x].equals(variable[i])){
                                        codearr[x]=i+"";
                                        i=vp;
                                        break;
                                    }
                                }
                        }
                       
                    }
                    break;
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
            for(int i=0;i<codearr.length;i++){
                
                if(codearr[i].contains("0")||codearr[i].contains("1")||codearr[i].contains("2")||codearr[i].contains("3")||codearr[i].contains("4")||codearr[i].contains("5")||codearr[i].contains("6")||codearr[i].contains("7")||codearr[i].contains("8")||codearr[i].contains("9")){
                    try{
                        syn[i]=Integer.parseInt(codearr[i]);
                    }
                    catch(NumberFormatException nfe){
                        lineNumberErr(tempo);
                        errorDetected("Syntax Error: WRONG NUMBER(CONSTANT VALUE) FORMAT",errctr);
                        errctr=1;y=1;
                    }
                }
                else{
                    try{                                            //Check each string if it has an equal in the bytecode instructions
                        syn[i]=BYTECODES.get(codearr[i]);           //and store it into an array of integers.
                    }
                    catch(NullPointerException e){
                        System.out.println("Syntax error at string index "+i);
                    }
                }
            }
            
            for (int i=0;i<syn.length;i++){
                clist.add(syn[i]);
            }
        }
        
        else{            
            for(int i=0;i<errorCount;i++){
                output+="Line "+line[i]+" -- "+errors[i];
                System.out.println(errors[i]);
            }
        }
    
}
    
    public void exe(String s,String wholecode){
        runn++;
        String obj="";
        s = s.trim().replaceAll("\n"," ").replaceAll(" +", " ");          //Replace all linebreaks in code with whitespace and excess whitespace remover        
///////////////////////////////////////// 
        char sArr[]=s.toCharArray();
        //Trim spaces on both ends of assignment operator
        for(int i=0;i<s.length();i++){
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
        for(int i=0;i<sArr.length;i++){
            if(sArr[i]!='^'){
                s+=sArr[i]+"";
            }
        }
        s=s.replaceAll("=+", "=");
//////////////////////////////////////////////        
        codetext(s,wholecode);                                                      //Process code input
        
        System.out.println("FINAL: "+output);
        if(errorCount==0){
            int[] syntax = new int[clist.size()];
            for (int i=0; i < syntax.length; i++)
            {
                syntax[i] = clist.get(i).intValue();
                obj=obj+syntax[i]+" ";
            }
            System.out.println();
            VM vm=new VM(syntax,0,200,variable);
            vm.trace = false;
            output=vm.cpu();
            o=obj;
        }
    }

    void errorDetected(String err, int errline){
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
        


