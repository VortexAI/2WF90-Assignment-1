/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2wf90.assignment.pkg1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author s151810
 */
public class Assignment1 {

    private void run(String[] args) throws FileNotFoundException{
        String file = System.getProperty("user.dir");
        
        file = file + "\\example.txt";
        Scanner sc = new Scanner(new FileReader(file));
        Function func;
        
        while(sc.hasNext()){
            String a = sc.nextLine();
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
            } 
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Assignment1 a = new Assignment1();
        a.run(args);
    }
    
}
