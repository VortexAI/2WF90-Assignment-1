/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2wf90.assignment.pkg1;

import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author s151810
 */
public class Number {
    private final char[] libInt = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final Map<Character, Integer> libChar = new HashMap<Character, Integer>();
    char[] number;
    int[] value;
    int radix;
    boolean positive;
    
    static{
        libChar.put('0', 0);
        libChar.put('1', 1);
        libChar.put('2', 2);
        libChar.put('3', 3);
        libChar.put('4', 4);
        libChar.put('5', 5);
        libChar.put('6', 6);
        libChar.put('7', 7);
        libChar.put('8', 8);
        libChar.put('9', 9);
        libChar.put('a', 10);
        libChar.put('b', 11);
        libChar.put('c', 12);
        libChar.put('d', 13);
        libChar.put('e', 14);
        libChar.put('f', 15);
    }
    
    Number(String number, int radix, boolean positive){
        this.number = number.toCharArray();
        this.radix = radix;
        this.positive = positive;
    }
    
    Number(int[] value, int radix, boolean positive){
        this.value = value;
        this.radix = radix;
        this.positive = positive;
        this.number = intToStringArr(value);
    }
    
    public void stringToIntArr(int l){
        int[] result = new int[l];
        for(int i : result){
            i = 0;
        }
        for(int i = l-1; i>= l- number.length; i--){
            result[i] = libChar.get(number[i-(l-number.length)]);
        }
        this.value = result;
    }
    
    public char[] intToStringArr(int[] a){
        
        char[] result = new char[0];
        boolean NonZero = true;
        
        for(int i = 0; i < a.length; i++){
            if(i == 0 && NonZero){
                
            } else if(i != 0 && NonZero){
                result[i] = libInt[i];
                NonZero = false;
            } else {
                result[i] = libInt[i];
            }
        }
        
        return result;
    }
    
    public void flip(){
        positive = !positive;
    }
    
    /**
     * 
     * @param other String you want to check to be smaller
     * @return 
     */
    public boolean thisBiggerThan(Number other){
        return true;
    }
    
    public char[] getChars(){
        return number;
    }
    
    public int getRadix(){
        return radix;
    }
    
    public boolean isPositive(){
        return positive;
    }
    
    public int[] getIntArr(){
        return value;
    }
}
