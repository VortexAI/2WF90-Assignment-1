/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2wf90.assignment.pkg1;

/**
 *
 * @author s166967
 */
public class Karatsuba extends Function{    
    //Set up objects of other operators, will be used later.
    EzMult mult = new EzMult();
    AddSub add = new AddSub(true);
    AddSub sub = new AddSub(false);
    
    @Override
    Number run(Number a, Number b, Number m) {
        //Check if the answer will be positive
        boolean positive = (a.isPositive() == b.isPositive());

        //Set both numbers to positive for simplicity.
        if(!a.isPositive()){
            a.flip();
        }
        if(!b.isPositive()){
            b.flip();
        }
        
        //Calculate a times b.
        Number ab = Karat(a,b);
        
        //Make sure the sign is correct based on the earlier set variable.
        if(ab.isPositive() != positive){
            ab.flip();
            
            //Prever counters from being reset by storing...
            int countMult = ab.getCountMult();
            int countAdd  = ab.getCountAdd();
            
            ab = new Number(ab.getIntArr(), ab.getRadix(), ab.isPositive());
            
            //.. and setting them back again
            ab.setCountMult(countMult);
            ab.setCountAdd(countAdd);
        }
        
        return ab;
    }
    
    Number Karat(Number a, Number b) {
        
        //Get the sizes of the numbers.
        int aSize = a.getChars().length;
        int bSize = b.getChars().length;
        
        //Make sure the sign doesnt add to it. 
        if(!a.isPositive()){
            aSize--;
        }
        if(!b.isPositive()){
            bSize--;
        }
        
        //If either of the nubmers has a size less than 2, start recursing back
        if(aSize < 2 || bSize < 2){  
            return mult.run(a,b, null);
        }
        
        //Make sure both numbers are of equal and even size
        if(aSize % 2 != 0 || bSize % 2 != 0){
            if(aSize > bSize) {
                if(aSize % 2 != 0){
                    a.stringToIntArr(aSize+1);
                    b.stringToIntArr(aSize+1);    
                    aSize++;
                    bSize=aSize;
                } else{
                    convert(a,b);
                }
            } else {
                if(bSize % 2 != 0){
                    a.stringToIntArr(bSize+1);
                    b.stringToIntArr(bSize+1);
                    bSize++;
                    aSize = bSize;
                } else {
                    convert(a,b);
                }            
            }
        } else {
            convert(a,b);
        }
        
        //Get the radix representation arrays
        int[] x = a.getIntArr();
        int[] y = b.getIntArr();
        
        //Get the size of both of the now equally sized arrays
        int size = x.length;
        
        //Get the radix
        int radix = a.getRadix();
        
        //Get the lower and upper halves of the arrays
        Number x_hi = new Number(getHigh(x), radix, true);
        Number x_lo = new Number(getLow(x), radix, true);        
        Number y_hi = new Number(getHigh(y), radix, true);  
        Number y_lo = new Number(getLow(y), radix, true);       

        //Calculate the needed multiplicates via recursion     
        Number xy_hi = Karat(x_hi,y_hi);          
        Number xy_lo = Karat(x_lo,y_lo);
        
        //Calculate second term: 
        //x_hi*y_lo +x_lo*y_hi = (x_hi +x_lo)(y_hi +y_lo)-x_hi*y_hi *x_lo* y_lo
        Number SecondTerm = sub.run(sub.run(Karat(add.run(x_hi, x_lo, null), add.run(y_hi, y_lo, null)),xy_hi, null),xy_lo,null);
        //Calculate x times y where the SecondTerm = (x_hi*y_lo + x_lo*y_hi)
        //x*y = x_hi*y_hi*b^n + (x_hi*y_lo + x_lo*y_hi)*b^n/2 + x_lo*y_lo
        Number xy = add.run(add.run(shift(xy_hi, size), shift(SecondTerm, size/2), null),xy_lo,null);
        
        return xy;
    }

    int[] getLow(int[] c){
        //Get the lower half of an array
        //It's just copying half the array
        int halfSize = c.length/2;
        int[] low = new int[halfSize];
        for(int i=0; i<halfSize; i++){
            low[i]=c[i+halfSize];
        }
        return low;
    }
    
    int[] getHigh(int[] c){
        //Get the upper half of an array
        //It's just copying half the array
        int halfSize = c.length/2;
        int[] high = new int[halfSize];
        for(int i=0; i<halfSize; i++){
            high[i]=c[i];
        }
        return high;
    }
    
    //The following function is used for multplying a matrix in a radix
    //representation by a power of that radix. Also called a shift.  
    Number shift(Number c, int power){
        //Get the size of the array to be shift
        int size = c.getChars().length;
        c.stringToIntArr(size);
        //Get the array
        int[] cArray = c.getIntArr();
        //Create the shifted array. It has 'power' extra indices, since all
        //will be shifted 'power' times to the left. 
        int[] shiftedArray = new int[size+power]; 
        //Copy the original that was to be shifted into the initial positions of
        //the new one. 
        for(int i=0; i < size; i++) {
            shiftedArray[i] = cArray[i];
        }
        //Fill in the last slots with 0's
        for(int i=size; i < size + power; i++){
            shiftedArray[i] = 0;
        }
        Number shifted = new Number(shiftedArray, c.getRadix(), c.isPositive());
        shifted.setCountMult(c.getCountMult());
        shifted.setCountAdd(c.getCountAdd());
        
        return shifted;
    }
}