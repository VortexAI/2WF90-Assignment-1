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
        String file = args[1];
        Scanner sc = new Scanner(new FileReader(file));
        Function func;
        Number num3;
        while(sc.hasNext()){
            String a = sc.nextLine();
            if(a.startsWith("[radix]")){ 
                
                int radix = Integer.parseInt(a.split(" ")[1]);
                String Assignment = sc.nextLine();
                String x = sc.nextLine().split(" ")[1];
                String y = sc.nextLine().split(" ")[1];
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
                ArrayList<Character> result = new ArrayList<Character>();
                if(checkZero(num3)){
                    System.out.println("[answer] 0");
                } else {
                    System.out.println("[answer] " + num3.getChars());
                }
            } 
        }
    }
    
    boolean checkZero(Number a){
        for (int i : a.getIntArr()){
            if(i != 0){
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Assignment1 a = new Assignment1();
        a.run(args);
    }
    
}
