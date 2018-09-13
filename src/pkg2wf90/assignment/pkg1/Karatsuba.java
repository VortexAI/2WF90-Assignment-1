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
public class Karatsuba extends Function{
    int multCount = 0;
    int addSubCount = 0;
    
    EzMult mult = new EzMult();
    AddSub add = new AddSub(true);
    AddSub sub = new AddSub(false);
    
    @Override
    Number run(Number a, Number b) {
        return Karat(a, b);
    }
    
    Number Karat(Number a, Number b) {
        //x*y = x_hi*y_hi*b^n + (x_hi*y_lo + x_lo*y_hi)*b^n/2 + x_lo*y_lo
        //x_hi*y_lo +x_lo*y_hi = (x_hi +x_lo)(y_hi +y_lo)-x_hi*y_hi *x_lo* y_lo
        int aSize = a.getChars().length;
        int bSize = b.getChars().length;
        //If either of the nubmers has a size less than 2, start recursing back
        if(aSize < 2 || bSize < 2){
            multCount++;
            return mult.run(a,b);
        }
        
        //Make sure both numbers are of equal size
        if(aSize % 2 != 0 || bSize %2 != 0){
            if(aSize > bSize){
                a.stringToIntArr(aSize+1);
                b.stringToIntArr(aSize+1);
            } else {
                a.stringToIntArr(bSize+1);
                b.stringToIntArr(bSize+1);
            }               
        }

        int[] x = a.getIntArr();
        int[] y = b.getIntArr();
        int size = x.length;
        int radix = a.getRadix();
        
        Number x_hi = new Number(getHigh(x), a.getRadix(), a.isPositive());
        Number x_lo = new Number(getLow(x), a.getRadix(), a.isPositive());        
        Number y_hi = new Number(getHigh(y), b.getRadix(), b.isPositive());       
        Number y_lo = new Number(getLow(y), b.getRadix(), b.isPositive());       
        
        Number xy_hi = Karat(x_hi,y_hi);
        Number xy_lo = Karat(x_lo,y_lo);
        Number bN = new Number(makeShiftArray(radix, size/2), radix, true);
        Number bNhalf = new Number(makeShiftArray(radix, size/4), radix, true);
        
        Number SecondTerm = sub.run(sub.run(Karat(add.run(x_hi, x_lo), add.run(y_lo, y_lo)),xy_hi),xy_lo);
        Number xy = add.run(add.run(Karat(xy_hi, bN), Karat(SecondTerm, bNhalf)),xy_lo);
        
        addSubCount += 6;
        xy.setCountMult(multCount);
        xy.setCountAdd(addSubCount);
        return xy;
    }

    int[] getLow(int[] c){
        int halfSize = c.length/2;
        int[] low = new int[halfSize];
        for(int i=0; i<halfSize; i++){
            low[i]=c[i+halfSize];
        }
        return low;
    }
    
    int[] getHigh(int[] c){
        int halfSize = c.length/2;
        int[] high = new int[halfSize];
        for(int i=0; i<halfSize; i++){
            high[i]=c[i];
        }
        return high;
    }
    
    int[] makeShiftArray(int radix, int position){
        int[] shift = new int[position];
        
        for(int i = 0; i < position; i++){
            shift[i]=0;
        }
        
        shift[position] = 1;
        
        return shift;
    }
    /*int[] makeEven(int[] c){
        int size = c.length;
        int even[] = new int[size+1];
        even[0] = 0;
        for(int i = 1; i<=size+1; i++){
            even[i] = c[i];
        }
        return even;
    }*/
}