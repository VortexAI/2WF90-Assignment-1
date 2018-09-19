/*
 * 
 */
package pkg2wf90.assignment.pkg1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author Stijn Beukes
 */
public class Number {
    private final char[] libInt = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
        '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final Map<Character, Integer> libChar = 
            new HashMap<Character, Integer>();
    private char[] number;
    private int[] value;
    private int radix;
    private boolean positive;
    private int countAdd;
    private int countMult;
    private Number d;
    private Number a;
    private Number b;
    
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
        if(positive){
            this.number = number.toCharArray();
        } else {
            this.number = number.split("-")[number.split("-").length-1].toCharArray();
        }
        this.radix = radix;
        this.positive = positive;
    }
    
    Number(int[] value, int radix, boolean positive){
        this.value = value;
        this.radix = radix;
        this.positive = positive;
        this.number = intToStringArr(value);
    }
    
    /**
     * Takes a length and initializes this Number's int array to be of equal v
     * value to this Number's provided String and of equal length to l
     * @param l the length of the desired int array
     */
    public void stringToIntArr(int l){
        int index = 0;
        if(number[0] == '-'){
            index = 1;
        }
        int[] result = new int[l];
        for(int i : result){
            i = 0;
        }
        
        for(int i = l-1; i >= l- number.length + index; i--){
            result[i] = libChar.get(number[i-(l-number.length)]);
        }
        this.value = result;
    }
    
    /**
     * takes a string and turns it into an int array of equal value
     * @param m String to be turned into a 
     * @return int array of equal value to String m
     */
    public int[] stringToIntArrLoc(String m){
        int index = 0;
        if(!positive){
            index = 1;
        }
        int[] result = new int[m.length()];
        
        for(int i = m.length()-1; i >= m.length()- number.length + index; i--){
            result[i] = libChar.get(m.toCharArray()[i-(m.length()-number.length)]);
        }
        return result;
    }
    
    /**
     * Takes an int array and turns it into a char array of equal value
     * @param a int array to be turned into a String array
     * @return String array of equal value to int array {@code a}
     */
    public char[] intToStringArr(int[] a){
        
        char[] result;
        ArrayList<Character> inter = new ArrayList<Character>();
        boolean nonZero = false;
        
        for(int i : a){
            if (i !=0 && !nonZero) {
                if(!positive){
                    inter.add('-');
                }
                inter.add(libInt[i]);
                nonZero=true;
            } else if (nonZero){
                inter.add(libInt[i]);
            }
        }
        if(inter.size() == 0){
            return new char[] {'0'};
        }
        result = new char[inter.size()];
        for(int i = 0; i <result.length; i++){
            result[i] = inter.get(i).charValue();
        }
        return result;
    }
    /**
     * swaps the value of positive with its inverse value
     */
    public void flip(){
        positive = !positive;
    }
    
    /**
     * Takes a number of equal length to be compared to.
     * @param other String you want to check to be smaller
     * @return True if this Number is bigger than {@code other}. Otherwise false
     */
    public boolean thisBiggerThan(Number other){
        int[] otherArray = other.getIntArr();
        for(int i = 0; i < value.length; i++){
            if(value[i] == otherArray[i]){
                  
            } else if (value[i] > otherArray[i]){
                return true;
            } else{
                return false;
            }
        }
        return false;
    }
    
    public int getLength(){
        return value.length;
    }
    
    public char[] getChars(){
        return number.clone();
    }
    
    
    public int getRadix(){
        int b = radix;
        return b;
    }
    
    /**
     * sets value of positive
     * @return value of whether this Number is positive
     */
    public boolean isPositive(){
        boolean b = positive;
        return b;
    }
    
    /**
     * gets value of the Numbers int array
     * @return returns a copy of the array holding the integer values of the
     * Number
     */
    public int[] getIntArr(){
        return value.clone();
    }
    
    /**
     * sets Number's countAdd variable
     * @param i is used to set {@code countAdd}
     */
    public void setCountAdd(int i){
        this.countAdd = i;
    }
    
    /**
     * returns Number's countAdd variable
     * @return returns the value of countAdd
     */
    public int getCountAdd(){
        return this.countAdd;
    }
    
    /**
     * sets Number's countMult variable
     * @param i is used to set the value of {@code countMult}
     */
    public void setCountMult(int i){
        this.countMult = i;
    }
    
    /**
     * returns Nubmer's countMult variable
     * @return returns the value of countMult
     */
    public int getCountMult(){
        return this.countMult;
    }
    
    /**
     * sets value of this Number's a
     * @param a is used to set this Number's {@code a} variable
     */
    
    public void setA(Number a){
        this.a = a;
    } 
    
    /**
     * sets value of this Number's b
     * @param b is used to set this Number's {@code b} variable
     */
    
    public void setB(Number b){
        this.b = b;
    }
    
    /**
     * sets value of this Number's d
     * @param d is used to set this Number's {@code d} variable
     */
    public void setD(Number d){
        this.d = d;
    }
    
    /**
     * returns value of this Number's a
     * @return value of {@code a}
     */
    public Number getA(){
        return a;
    }
    
    /**
     * returns value of this Number's b
     * @return value of {@code b}
     */
    public Number getB(){
        return b;
    }
    
    /**
     * returns value of this Number's d
     * @return value of {@code d}
     */
    public Number getD(){
        return d;
    }
}
