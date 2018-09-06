/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2wf90.assignment.pkg1;

/**
 *
 * @author s151810
 */
abstract public class Function {
    
    public void convert(Number a, Number b){
        if(a.getChars().length > b.getChars().length){
            int length = a.getChars().length;
            a.stringToIntArr(length);
            b.stringToIntArr(length);
        } else {
            int length = a.getChars().length;
            a.stringToIntArr(length);
            b.stringToIntArr(length);
        }
    }
    
    abstract void run(int[] i, int[] j);
}
