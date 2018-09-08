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
        System.out.println(file);
        //file = file + args[1];
        Scanner sc = new Scanner(System.in);
        //new FileReader(file)
        System.out.println(file);
        
        Function func;
        Number num3;
        while(sc.hasNext()){
            String a = sc.nextLine();
            if(a.startsWith("[radix]")){ 
                
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
                
                System.out.println(num3.getChars().length);
                System.out.print("[answer] ");
                for(char d: num3.getChars()){
                    System.out.print(d);
                }
            } 
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Assignment1 a = new Assignment1();
        a.run(args);
    }
    
}
