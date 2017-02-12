/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;
import static pl.ByteInstructions.*;

public class MainP {
    static int[] test={
        END
    };
    public static void main(String[] args){
        VM vm=new VM(test,0,0);
        vm.cpu();
    }
}
