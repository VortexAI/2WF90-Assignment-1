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

    Number run(Number a, Number b) {
        Number result = euclid(a, b);
        return result;
    }
    
    Number euclid(Number num1, Number num2) {
        num1.stringToIntArr(num1.getChars().length);
        num2.stringToIntArr(num2.getChars().length);
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
        Number neg = new Number(oneArray, num1.getRadix(), false);
        Number q;
        Number r;
        while (num2.thisBiggerThan(zero)) {
            q = division(num1, num2);
            r = subtract.sub(num1, mult.mult(q, num2, true));
            num1 = num2;
            num2 = r;
            x3 = subtract.sub(x1, mult.mult(q, x2, true));
            y3 = subtract.sub(y1, mult.mult(q, y2, true));
            x1 = x2;
            y1 = y2;
            x2 = x3;
            y2 = y3;           
            
        }
        Number d;
        d = num1;
        Number x;
        Number y;
        if (num1.thisBiggerThan(neg)) {
            x = x1;
        } else {
            x1.flip();
            x = x1;
        }
        if (num2.thisBiggerThan(neg)) {
            y = y1;
        } else {
            y1.flip();
            y = y1;
        }
        d.intToStringArr(d.getIntArr());
        return d;
    }
    
    Number division(Number num1, Number num2) {
        int[] a = num1.getIntArr();
        int[] b = num2.getIntArr();
        int k = a.length;
        int l = b.length;
        int[] r = new int[k+1];
        for (int i=k; i >= 1; i--) {
            r[i] = a[i-1];
        }
        r[0] = 0;
        int[] q = new int[k-l + 1];
        int base = 16;
        int carry;
        int tmp;
        
        for(int i = k - l; i >= 0; i--) {
            q[q.length-1 - i] = (r[r.length-1 - (i+l)] * base + r[r.length-1 - (i+l-1)]) / b[b.length-1 - (l-1)];
            if (q[q.length-1 - i] >= base) {
                q[q.length-1 - i] = base - 1;
            } 
            carry = 0;
            for(int j=0; j <= l-1; j++) {
                tmp = r[r.length-1 - (i+j)] - q[q.length-1 - i]*b[b.length-1 - j] + carry;
                carry = tmp/base;
                r[r.length-1 - (i+j)] = tmp%base; 
                if (r[r.length-1 - (i+j)] < 0) {
                    r[r.length-1 - (i+j)] += base;
                }
            }
            
            r[r.length-1 - (i+l)] = r[r.length-1 - (i+l)] + carry;
            while (r[r.length-1 - (i+l)] < 0) {
                carry = 0;
                for (int j = 0; j <= l-1; j++) {
                    tmp = r[r.length-1 - (i+j)] + b[b.length-1 - i] + carry;
                    carry = tmp/base;
                    r[r.length-1 - (i+j)] = tmp%base; 
                    if (r[r.length-1 - (i+j)] < 0) {
                        r[r.length-1 - (i+j)] += base;
                    }
                }
                r[r.length-1 - (i+l)] = r[r.length-1 - (i+l)] + carry;
                q[q.length-1 - i] = q[q.length-1 - i] - 1;
            }
        }
        for(int i = q.length-1; i>=0; i--) {
            System.out.println("q: " + q[i]);
	}
        for(int i = r.length-1; i >= 0; i--) {
            System.out.println(r[i]);
        }
        Number result = new Number(q, num1.getRadix(), true);
        return  result;
    }
    
    
}



