/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author s151810
 */
public class Test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String file = System.getProperty("user.dir");
        System.out.println(file);
        BufferedReader sc = new BufferedReader(new FileReader(file + "\\test.txt"));
        while(sc.ready()){
            System.out.println(sc.readLine());
        }
        
    }
}
