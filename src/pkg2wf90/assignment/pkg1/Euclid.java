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
public class Euclid extends Function{

    @Override
    Number run(Number a, Number b, Number m) {
        Number result = euclid(a, b);
        return result;
    }
    
    /**
     * Performs extended euclidian algorithm on two numbers
     * @param num1 first number
     * @param num2 second number
     * @return the result of the euclidian algorithm
     */
    Number euclid(Number num1, Number num2) {
        // check which number is larger and swap appropiately
        if (num2.getChars().length > num1.getChars().length) {
            Number temp = num2;
            num2 = num1;
            num1 = temp;
        }
        // convert string to ints without getting leading zeros
        num1.stringToIntArr(num1.getChars().length);
        num2.stringToIntArr(num2.getChars().length);
        // initialize variables
        AddSub subtract = new AddSub(false);
        EzMult mult = new EzMult();
        int[] zeroArray = new int[num1.getIntArr().length];
        int[] oneArray = new int[num1.getIntArr().length];
        oneArray[0] = 1;
        Number x1 = new Number(oneArray, num1.getRadix(), true);
        Number x2 = new Number(zeroArray, num1.getRadix(), true);
        Number y1 = new Number(zeroArray, num1.getRadix(), true);
        Number y2 = new Number(oneArray, num1.getRadix(), true);
        Number x3;
        Number y3;
        Number zero = new Number(zeroArray, num1.getRadix(), true);        
        Number q;
        Number r;
        Number d;
        Number x;
        Number y;
        // extended euclidian algorithm loop
        while (num2.thisBiggerThan(zero)) {
            // execute division
            q = division(num1, num2);
            // compute remainder
            r = subtract.sub(num1, mult.mult(q, num2, true));
            // remove leading zeros of remainder
            r = removeZeros(r);
            num1 = num2;
            num2 = r;
            x3 = subtract.sub(x1, mult.run(q, x2, null));
            y3 = subtract.sub(y1, mult.run(q, y2, null));
            x1 = x2;
            y1 = y2;
            x2 = x3;
            y2 = y3;          
        }
        d = num1;
        if (num1.isPositive()) {
            x = x1;
        } else {
            x1.flip();
            x = x1;
        }
        if (num2.isPositive()) {
            y = y1;
        } else {
            y1.flip();
            y = y1;
        }
        d.setD(d);
        d.setA(x);
        d.setB(y);
        return d;
    }
    
    /**
     * Divides two numbers
     * @param num1 first number
     * @param num2 second number
     * @return quotient of division
     */
    Number division(Number num1, Number num2) {
        int[] a = num1.getIntArr();
        int[] b = num2.getIntArr();
        int k = a.length;
        int l = b.length;
        // if a is shorter than b you know the answer will be 0
        if (k < l) {
            int[] zero = new int[] {0};
            return new Number(zero, num1.getRadix(), true);
        }
        
        int[] r = new int[k+1];
        for (int i=k; i >= 1; i--) {
            r[i] = a[i-1];
        }
        r[0] = 0;
        int[] q = new int[k-l + 1];
        int base = num1.getRadix();
        int carry;
        double carryDouble;
        int tmp;
        // use array.length-1-value to start from the back of the array
        for(int i = k - l; i >= 0; i--) {
            q[q.length-1 - i] = (r[r.length-1 - (i+l)] * base + 
                    r[r.length-1 - (i+l-1)]) / b[b.length-1 - (l-1)];
            if (q[q.length-1 - i] >= base) {
                q[q.length-1 - i] = base - 1;
            } 
            carry = 0;
            for(int j=0; j <= l-1; j++) {
                tmp = r[r.length-1 - (i+j)] - q[q.length-1 - i]
                        * b[b.length-1 - j] + carry;
                /* use doubles for the carry because java will round up on
                   negative values
                */
                carryDouble = Math.floor((double) tmp/base);
                carry = (int) carryDouble;
                r[r.length-1 - (i+j)] = tmp%base; 
                /* java gives negative modulo answer while we need the positive
                   one so correct that if that is the case.
                */
                if (r[r.length-1 - (i+j)] < 0) {
                    r[r.length-1 - (i+j)] += base;
                }
            }
            
            r[r.length-1 - (i+l)] = r[r.length-1 - (i+l)] + carry;
            while (r[r.length-1 - (i+l)] < 0) {
                carry = 0;
                for (int j = 0; j <= l-1; j++) {
                    tmp = r[r.length-1 - (i+j)] + b[b.length-1 - j] + carry;
                    carry = tmp/base;
                    r[r.length-1 - (i+j)] = tmp%base; 
                    /* java gives negative modulo answer while we need the positive
                    one so correct that if that is the case.
                    */
                    if (r[r.length-1 - (i+j)] < 0) {
                        r[r.length-1 - (i+j)] += base;
                    }
                }
                r[r.length-1 - (i+l)] = r[r.length-1 - (i+l)] + carry;
                q[q.length-1 - i] = q[q.length-1 - i] - 1;
            }
        }
        Number result = new Number(q, num1.getRadix(), true);
        return  result;
    }
    
    /**
     * Removes the leading zeros of a number
     * @param num number
     * @return number without leading zeros
     */
    Number removeZeros(Number num) {
        int i = 0;
        int[] newArr;
        // count amount of zeros
        while((i < num.getIntArr().length) && (num.getIntArr()[i] == 0)) {
            i++;
        }
        /* create new array and copy values of array of num. If there are only
          zeros, just return array of 0.
        */
        if (i == num.getIntArr().length) {
            newArr = new int [1];
            newArr[0] = 0;
        } else {
            newArr = new int [num.getIntArr().length - i];
            for(int j = newArr.length - 1; j >= 0; j--) {
                newArr[j] = num.getIntArr() [j+i];
            }
        }
        return new Number(newArr, num.getRadix(), num.isPositive());
    }
    
    
}



