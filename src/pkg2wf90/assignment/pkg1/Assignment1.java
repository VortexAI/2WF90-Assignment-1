/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2wf90.assignment.pkg1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author s151810
 */
public class Assignment1 {

    private void run(String[] args) throws FileNotFoundException{
        String file = args[1];
        BufferedReader br = new BufferedReader(new FileReader(file));
        
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Assignment1 a = new Assignment1();
        a.run(args);
    }
    
}
