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
public class Invert extends Function{

    @Override
    Number run(Number a, Number b, Number m) {
        Euclid euclid = new Euclid();
        AddSub addSub = new AddSub(true);
        EzMult mult = new EzMult();
        
        convert(a, m);
        Number aPrime = new Number(a.getIntArr(), a.getRadix(), a.isPositive());
        Number mPrime = new Number(m.getIntArr(), m.getRadix(), m.isPositive());
        
        int[] x1Array = new int[Math.max(aPrime.getIntArr().length,mPrime.getIntArr().length)];
        x1Array[x1Array.length - 1] = 1;
        Number x1 = new Number(x1Array, a.getRadix(), true);
        Number x2 = new Number(new int[Math.max(aPrime.getIntArr().length,mPrime.getIntArr().length)], a.getRadix(), true);
        Number x3;
        Number quotient;
        Number remainder;
        
        while (isNotZero(mPrime.getIntArr())) {
            quotient = euclid.division(aPrime, mPrime);
            remainder = addSub.sub(aPrime, mult.run(quotient,mPrime, null));
            aPrime = mPrime;
            mPrime = remainder;
            x3 = addSub.sub(x1, mult.run(quotient, x2, null));
            x1 = x2;
            x2 = x3;          
        }
        if (isOne(aPrime.getIntArr())) {
            return x1;
        } else {
            return new Number("ERROR", -1, true);
        }
    }
    
    boolean isNotZero (int[] array){
        for (int i = 0; i < array.length - 1; i++){
            if (array[i] > 0){
                return true;
            }
        }
        return false;
    }
    
    boolean isOne (int[] array) {
        for (int i = 0; i < array.length - 2; i++){
            if (array[i] > 0) {
                return false;
            }
        } 
        if (array[array.length - 1] == 1) {
            return true;
        }
        return false;
    }
    
}
