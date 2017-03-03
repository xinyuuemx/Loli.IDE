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
    String output;
    
    static void codetext(String s){
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
            
            for(int i=0;i<codearr.length;i++){
                
                if(codearr[i].contains("0")||codearr[i].contains("1")||codearr[i].contains("2")||codearr[i].contains("3")||codearr[i].contains("4")||codearr[i].contains("5")||codearr[i].contains("6")||codearr[i].contains("7")||codearr[i].contains("8")||codearr[i].contains("9")){
                    try{
                        syn[i]=Integer.parseInt(codearr[i]);
                    }
                    catch(NumberFormatException nfe){
                        System.out.println("Syntax Error: WRONG NUMBER(CONSTANT VALUE) FORMAT");
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
    
    
    public void exe(String s){
        
        s=s.replaceAll("\n"," ");                   //Replace all linebreaks in code with whitespace
        //s=s.trim();                                 //Remove leading and trailing whitespaces
        s=errorChecker(s);                          //Check for some errors in syntax
        codetext(s);                                //Process code input
        
        //PRINT CODE'S NO. OF STRINGS
        System.out.println(clist.size());
        int[] syntax = new int[clist.size()];
        for (int i=0; i < syntax.length; i++)
        {
            syntax[i] = clist.get(i).intValue();
            //System.out.print(syntax[i]+" ");
        }
        System.out.println();
        VM vm=new VM(syntax,0,200);
        vm.trace = false;
        output=vm.cpu();
    
    }

    public String errorChecker(String s){
        //EXCESS WHITESPACE REMOVER
        s = s.trim().replaceAll(" +", " ");
        
        return s;
    }
    
}
        


