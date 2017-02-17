/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import static pl.ByteInstructions.*;

public class MainP {
    
    
    static ArrayList<Integer> clist = new ArrayList<Integer>();
    static String code;
    
    static void codetext(String s){
        code=s;
        System.out.println(code);
        int[] syn=new int[100];
        String[] codearr=new String[100];
        //Input code
        
        //Checker if code contains spaces
        if(code.contains(" ")){
            System.out.println("WITH SPACE!");
        //Store code in an array of strings
            codearr=code.split(" ");
            for(int i=0;i<codearr.length;i++){
                syn[i]=BYTECODES.get(codearr[i]);
            }
            //System.out.println(syntax);
        }
        else{
            System.out.println("NO SPACE!");

            codearr[0]=code;
            syn[0]=BYTECODES.get(codearr[0]);
            //System.out.println(syn);
        }
        //syntax=syn.clone();
        
        for (int i=0;i<syn.length;i++)
            clist.add(syn[i]);
}
    
    
    public static void main(String[] args){
        
  
        String s=JOptionPane.showInputDialog("CODE:");
//        while(i<syntax.length){
//        codetext();
//        i++;
//        }
        codetext(s);
        
        int[] syntax = new int[clist.size()];
        for (int i=0; i < syntax.length; i++)
        {
            syntax[i] = clist.get(i).intValue();
        }

        VM vm=new VM(syntax,0,0);
        vm.cpu();
    }
}
