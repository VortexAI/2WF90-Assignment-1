/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2wf90.assignment.pkg1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author s151810
 */
public class Assignment1 {

    private void run(String[] args) throws FileNotFoundException, IOException{
        String file = System.getProperty("user.dir");
        
        file = file + "\\" + args[0];
        BufferedReader sc = new BufferedReader(new FileReader(file));
        Function func;
        FileWriter fw = new FileWriter("output");
        BufferedWriter br = new BufferedWriter(fw);
        
        while(sc.ready()){
            String a = sc.readLine();
            sc.
            if(a.startsWith("[radix]")){ 
                Number num3;
                int radix = Integer.parseInt(a.split(" ")[a.split(" ").length-1]);
                String Assignment = sc.nextLine();
                String[] xl = sc.nextLine().split(" ");
                String x = xl[xl.length-1];
                String[] yl = sc.nextLine().split(" ");
                String y = yl[yl.length-1];
                
                if(Assignment.equals("[add]")){
                    func = new AddSub(true);    
                } else if(Assignment.equals("[subtract]")){
                    func = new AddSub(false);
                } else if(Assignment.equals("[multiply]")){
                    func = new EzMult();
                } else if(Assignment.equals("[karatsuba]")){
                    func = new Karatsuba();
                } else {
                    func = new Euclid();
                }
                
                Number num1 = new Number(x, radix, !x.startsWith("-"));
                Number num2 = new Number(y, radix, !y.startsWith("-"));
                num3 = func.run(num1, num2);
                
                System.out.print("[answer] ");
                for(char d: num3.getChars()){
                    System.out.print(d);
                }
                System.out.print("\n");
                if((func.getClass() == EzMult.class)||(func.getClass() == Karatsuba.class)){
                    System.out.println("[count-add] " + num3.getCountAdd());
                    System.out.println("[count-mul] " + num3.getCountMult());
                }
                
                if(func.getClass() == Euclid.class){
                    System.out.println("[answer-d] " + num3.getD());
                    System.out.println("[answer-a] " + num3.getA());
                    System.out.println("[answer-b] " + num3.getB());
                }
            } 
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Assignment1 a = new Assignment1();
        a.run(args);
    }
    
}
