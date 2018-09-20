/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2wf90.assignment.pkg1;

import java.util.Arrays;

/**
 *
 * @author s1233570
 */
public class Invert extends Function{
    
    
    /** Will calculate the modular inverse of number a mod m
     * 
     * @param a The given number of which we will try to find the inverse
     * @param b Dummy input from the input reader
     * @param m The given modulo
     * @return returns the inverse if it exists and otherwise the word ERROR
     */
    @Override
    Number run(Number a, Number b, Number m) {
        
        // We need to use big integer multiplication, division and subtraction
        Euclid euclid = new Euclid();
        AddSub addSub = new AddSub(true);
        EzMult mult = new EzMult();
        
        // Convert the input to a usable format and makes a copy of the original input
        convert(a, m);
        Number aPrime = new Number(a.getIntArr(), a.getRadix(), a.isPositive());
        Number mPrime = new Number(m.getIntArr(), m.getRadix(), m.isPositive());
        
        // Create some temp numbers to store values in and assings 1 to x1
        int[] x1Array = new int[Math.max(aPrime.getIntArr().length,mPrime.getIntArr().length)];
        x1Array[x1Array.length - 1] = 1;
        Number x1 = new Number(x1Array, a.getRadix(), true);
        Number x2 = new Number(new int[Math.max(aPrime.getIntArr().length,mPrime.getIntArr().length)], a.getRadix(), true);
        Number x3;
        Number quotient;
        Number remainder;
        
        // The while loop checks if mPrime is not already zero
        while (isBiggerThanZero(mPrime.getIntArr())) {
            // quotient is calculated by using big integer division
            quotient = euclid.division(aPrime, mPrime);
            // remainder is calculated by using big integer multiplication and subtraction
            remainder = addSub.sub(aPrime, mult.run(quotient,mPrime, null));
            // Moves the values around and calculates x3
            aPrime = mPrime;
            mPrime = remainder;
            x3 = addSub.sub(x1, mult.run(quotient, x2, null));
            x1 = x2;
            x2 = x3;          
        }
        // Make x1 on the positive side modulo m
        if (!x1.isPositive()) {
            x1 = addSub.add(x1, m);
        }
        // If aPrime is equal to 1 we have found an inverse otherwise not
        if (isOne(aPrime.getIntArr())) {
            return x1;
        } else {
            return new Number("ERROR", -1, true);
        }
    }
    
    /** Checks if an int array is equal to zero
     * 
     * @param array The int array to check
     * @return returns whether the int array is zero or not
     */
    boolean isBiggerThanZero (int[] array){
        // Loops over the entire array to check if any of the values are bigger than zero
        for (int i = 0; i < array.length; i++){
            if (array[i] > 0){
                return true;
            }
        }
        return false;
    }
    /** Checks if an int array is equal to one
     * 
     * @param array The int array to check
     * @return returns whether the int array is one or not
     */
    boolean isOne (int[] array) {
        // Checks if any of the other spots of the array is bigger than zero
        for (int i = 0; i < array.length - 1; i++){
            if (array[i] > 0) {
                return false;
            }
        } 
        // Checks if the smallest digit is equal to one
        if (array[array.length - 1] == 1) {
            return true;
        }
        return false;
    }
    
}
