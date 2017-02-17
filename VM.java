package pl;
import static pl.ByteInstructions.*;
public class VM {
	int [] data;
	int [] code;
	int [] stack;
	
	int ip; //instruction pointer
        int sp=-1; //stack pointer
        int fp; //function pointer
        
        public VM(int[] code, int main, int datasize){
        this.code=code;
        this.ip=main;
        data=new int[datasize];
        stack=new int[100];
        }
        
        public void cpu(){
            int opcode=code[ip]; //fetch
            switch(opcode){
                case END:
                    System.out.println("Process finished with instruction code "+opcode);
                    return;
                case HI:
                    System.out.println("Hi Em Pogi!");return;
            }
        }
	
}
