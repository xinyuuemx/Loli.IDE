/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

import java.util.ArrayList;
import static pl.ByteInstructions.*;

public class MainP {
    
 
    static ArrayList<Integer> clist = new ArrayList();
    static String code;
    String output="", o, error;
    String[] errors={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    int numberOferr=0,errorCount=0;
    void codetext(String s){
       
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

        
        x=0;
        int check=0;

        while(x<codearr.length){
            switch(codearr[x]){
           
                case "INTEGER":
                    try{
                        check=BYTECODES.get(codearr[x+1]);
                        errorDetected("ERROR: PLEASE DECLARE VARIABLE IDENTIFIER.");
                        run=false;
                    }
                    catch(NullPointerException npe){
                        
                    }     
            }
            x++;
        }
        System.out.println("mali: "+errorCount);
        if(run==true){
            for(int i=0;i<codearr.length;i++){
                
                if(codearr[i].contains("0")||codearr[i].contains("1")||codearr[i].contains("2")||codearr[i].contains("3")||codearr[i].contains("4")||codearr[i].contains("5")||codearr[i].contains("6")||codearr[i].contains("7")||codearr[i].contains("8")||codearr[i].contains("9")){
                    try{
                        syn[i]=Integer.parseInt(codearr[i]);
                    }
                    catch(NumberFormatException nfe){
                        errorDetected("Syntax Error: WRONG NUMBER(CONSTANT VALUE) FORMAT");
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
            System.out.println("pasok");
            
            for(int i=0;i<errorCount;i++){
                output+=errors[i];
                System.out.println(errors[i]);
            }
        }
    
}
    
    public void exe(String s){
        MainP mp=new MainP();
        String obj="";
        s = s.trim().replaceAll("\n"," ").replaceAll(" +", " ");          //Replace all linebreaks in code with whitespace and excess whitespace remover        
        codetext(s);                                                      //Process code input
        
        System.out.println("FINAL: "+output);
        if(errorCount==0){
            //PRINT CODE'S NO. OF STRINGS
             System.out.println(clist.size());
            int[] syntax = new int[clist.size()];
            for (int i=0; i < syntax.length; i++)
            {
                syntax[i] = clist.get(i).intValue();
                obj=obj+syntax[i]+" ";
            }
            System.out.println();
            VM vm=new VM(syntax,0,200);
            vm.trace = false;
            output=vm.cpu();
            o=obj;
        }
    }

    void errorDetected(String err){
        errors[errorCount]=err+"\n";
        errorCount++;
    }
}
        


