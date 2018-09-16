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

    @Override
    Number run(Number x, Number y, Number m) {
        boolean positive = x.isPositive();
        if (!positive) {
            x.flip();
        }
        x.stringToIntArr(x.getChars().length);
        m.stringToIntArr(m.getChars().length);
        int base = x.getRadix();
        EzMult mult = new EzMult();
        AddSub addSub = new AddSub(true);
        for (int i = x.getLength() - m.getLength(); i >= 0; i--) {
            Number mBase = mult.run(m, power(base, i), null);
            while (isBiggerEqualThan(x, mBase)) {
                x = addSub.sub(x, mBase);
            }
        }
        if (!positive) {
            x = addSub.sub(m, x);
        }
        return x;
        
    }
    
    public boolean isBiggerEqualThan(Number a, Number b){
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
    
    Number power(int base, int power) {
        int[] array = new int[power + 1];
        array[0] = 1;
        return new Number(array, base, true);
    }
    
}
