
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
                        //Variable identifier error checker for symbols
                        for(int i=0;i<symbolss.length;i++){
                            if(codearr[x+1].contains(symbolss[i])){
                                errline=x+1;
                                lineNumberErr(tempo);
                                errorDetected("VARIABLE IDENTIFIER ERROR: Variable name/identifier must not contain symbols");
                                run=false;
                                break;
                            }
                        }
                        for(l=0; l<codearr[x+1].length(); l++){
                            if(codearr[x+1].charAt(l)=='='){
                                System.out.println("DECLARATION: "+codearr[x+1]);
                                vOfIntvar = codearr[x+1].substring(l+1);
                                nOfIntvar = codearr[x+1].substring(0,l);
                                System.out.println(nOfIntvar);
                                System.out.println("VARIABLE: value -- "+vOfIntvar+", name -- "+nOfIntvar);
                                if(vOfIntvar==null){
                                    errline=x+1;
                                    lineNumberErr(tempo); 
                                    errorDetected("VARIABLE VALUE ERROR: 'INTEGER' variable requires specified integer value.");
                                    errctr=1;y=1;
                                    run=false; 
                                    check2=0;
                                }
                                else if(!isNumeric(vOfIntvar)){
                                    errline=x+1;
                                    lineNumberErr(tempo);
                                    errorDetected("VARIABLE VALUE ERROR: 'INTEGER' variable requires specified integer value.");
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
                            if(isVar(nOfIntvar)){
                                h=true;
                                errline=x+1;
                                lineNumberErr(tempo);
                                errorDetected("VARIABLE VALUE ERROR: "+nOfIntvar+" has already been declared.");
                                errctr=1;y=1;run=false;
                            }
                            //If variable does not exist
                            else{
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
                        char[] symbolsss=symbolss.toString().toCharArray();
                        for(int i=0;i<symbolss.length;i++){
                            if(codearr[x+1].charAt(0)==symbolsss[i]){
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
                    j=i; k++;
                }
            }
            else{
                temp[k]=s;
            }
        }
        for(i=0;i<k;i++){
            System.out.println("A "+temp[i]);
        }
        j=0;
        int l=0;
        if(k%2!=0){
            for(i=0;i<s.length();i++){if(s.charAt(i)=='\n'){errctr++;}else if(s.charAt(i)=='\"'){j++;if(j==k){break;} }}errorDetected("INSTRUCTION SYNTAX ERROR: 'PRINT' string literal requires enclosure by double quotation marks.");errctr=1;run=false;}
        else{
            for(i=0;i<=k;i++){
                if(temp[i].charAt(0)!='\"'){   
                    temp[i] = temp[i].trim().replaceAll("\n"," ").replaceAll(" +", " ");          //Replace all linebreaks in code with whitespace and excess whitespace remover        
                    temp[i]+=" ";
                    System.out.println("B "+temp[i]);
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
        


