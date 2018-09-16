/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2wf90.assignment.pkg1;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author s151810
 */
public class Assignment1 {

    private void run(String[] args) throws FileNotFoundException, IOException{
        String file = System.getProperty("user.dir");
        
        file = file + "\\" + args[0];
        Scanner sc = new Scanner(new FileReader(file));
        Function func = null;
        FileWriter fw = new FileWriter("output.txt");
        BufferedWriter br = new BufferedWriter(fw);
        
        
        while(sc.hasNext()){
            String a = sc.nextLine();
            br.write(a + System.getProperty("line.separator"));
            if(a.startsWith("[radix]")){ 
                int radix = 0;
                String x = null;
                String y = null;
                String m = null;
                Number num3;
                radix = Integer.parseInt(a.split(" ")[a.split(" ").length-1]);
                String Assignment = sc.nextLine();
                br.write(Assignment + System.getProperty("line.separator"));
                a = sc.nextLine();
                while(!sc.hasNext() || !a.trim().isEmpty()){
                    
                    if(a.startsWith("[x]")){
                        String[] xl = a.split(" ");
                        x = xl[xl.length-1];
                        br.write(a + System.getProperty("line.separator"));
                    }
                    if(a.startsWith("[y]")){
                        String[] yl = a.split(" ");
                        y = yl[yl.length-1];
                        br.write(a + System.getProperty("line.separator"));
                    }
                    if(a.startsWith("[m]")){
                        String[] m1 = a.split(" ");
                        m = m1[m1.length-1];
                        br.write(a + System.getProperty("line.separator"));
                    }
                    if(sc.hasNext()){
                        a = sc.nextLine();
                        
                    } else {
                        break;
                    }
                }
                
                
                
                if(Assignment.equals("[add]")){
                    func = new AddSub(true);    
                } else if(Assignment.equals("[subtract]")){
                    func = new AddSub(false);
                } else if(Assignment.equals("[multiply]")){
                    func = new EzMult();
                } else if(Assignment.equals("[karatsuba]")){
                    func = new Karatsuba();
                } else if(Assignment.equals("[reduce]")) {
                    func = new Reduce();
                } else if(Assignment.equals("[invert]")){
                    func = new Invert();
                } else {
                    func = new Euclid();
                }
                Number num1 = new Number(x, radix, !x.startsWith("-"));
                Number num2 = null;
                Number numM = null;
                if (y != null){
                    num2 = new Number(y, radix, !y.startsWith("-"));
                }
                
                
                if (m != null){
                    numM = new Number(m, radix, !m.startsWith("-"));
                }
                
                
                
                try {
                    num3 = func.run(num1, num2, numM);
                    br.write("[answer] ");
                    for(char d: num3.getChars()){
                        br.write(d);
                    }
                
                    br.write(System.getProperty("line.separator"));
                    System.out.print("\n");
                    if((func.getClass() == EzMult.class)||(func.getClass() == Karatsuba.class)){
                        br.write("[count-add] " + num3.getCountAdd() + System.getProperty("line.separator"));
                        br.write("[count-mul] " + num3.getCountMult() + System.getProperty("line.separator"));
                    }
                
                    if(func.getClass() == Euclid.class){
                        br.write("[answer-d] " + num3.getD());
                        br.write("[answer-a] " + num3.getA());
                        br.write("[answer-b] " + num3.getB());
                    }
                    
                } catch (Exception e) {
                    br.write("error " + e + "was caught");
                }
                br.write(System.getProperty("line.separator"));
            } 
        }
        
        br.flush();
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Assignment1 a = new Assignment1();
        a.run(args);
    }
    
}
