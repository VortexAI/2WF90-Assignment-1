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
public class Reduce extends Function {
    
    /** Will calculate x mod m for Numbers
     * 
     * @param x The given number to reduce
     * @param y Dummy input from input reader (always null)
     * @param m The given modulo
     * @return the result of x mod m
     */
    @Override
    Number run(Number x, Number y, Number m) {
        
        // We need to use big integer multiplication and subtraction
        EzMult mult = new EzMult();
        AddSub addSub = new AddSub(true);
        
        boolean positive = x.isPositive();
        if (!positive) {
            // We want to work with the absolute value
            x.flip(); 
        }
        
        // Create the int arrays
        x.stringToIntArr(x.getChars().length);
        m.stringToIntArr(m.getChars().length);
        
        // base is needed for mb^i
        int base = x.getRadix();
        
        for (int i = x.getLength() - m.getLength(); i >= 0; i--) {
            // calculate mb^i
            Number mBase = mult.run(m, power(base, i), null);
            
            // Keep subtracting when possible
            while (isBiggerEqualThan(x, mBase)) {
                x = addSub.sub(x, mBase);
            }
        }
        
        // the reduction cannot be negative, hence we need to subtract x from m
        if (!positive) {
            x = addSub.sub(m, x);
        }
        
        return x;
        
    }
    
    /** Compares two Numbers and returns true when a >= b
     * 
     * @param a the first Number
     * @param b the second Number
     * @return True if a >= b, false otherwise
     */
    public boolean isBiggerEqualThan(Number a, Number b){
        
        // make their int arrays of even size
        convert(a, b);
        
        int[] arrayA = a.getIntArr();
        int[] arrayB = b.getIntArr();
        
        for(int i = 0; i < arrayA.length; i++){
            if(arrayA[i] == arrayB[i]) {

            } else if (arrayA[i] > arrayB[i]){
                return true;
            } else{
                return false;
            }
        }
        return true;
        
    }
    
    /** creates a Number for a given radix to the power (power)
     * 
     * @param base the base/radix we are working in
     * @param power exponent
     * @return Number for a base to the power (power)
     */
    Number power(int base, int power) {
        int[] array = new int[power + 1];
        array[0] = 1;
        return new Number(array, base, true);
    }
    
}
