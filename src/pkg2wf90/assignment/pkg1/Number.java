/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2wf90.assignment.pkg1;

import java.util.*;


/**
 *
 * @author s151810
 */
public class Number {
    char[] number;
    int[] value;
    int radix;
    boolean positive;
    
    Number(String number, int radix, boolean positive){
        this.number = number.toCharArray();
        
    }
    
    public void stringToIntArr(int l){
        
        
    }
    
    public void intToStringArr(int[] a){
        
        
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
