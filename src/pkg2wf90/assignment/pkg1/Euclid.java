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
    void run(Number a, Number b) {
        
    }
    
    int[] euclid(Number num1, Number num2) {
        int[] result = new int[num1.value.length];
               AddSub.addition(result, result, 1); 
        int x1 = 1;
        int x2 = 0;
        int y1 = 0;
        int y2 = 1;
        int q;
        int r;
        Number zero = null;
        zero.value = new int[num2.value.length];
        while (num2.thisBiggerThan(zero)) {
            q = division(num1, num2);
            r = num1
            
        }
        return result;
    }
    
    int[] division(Number num1, Number num2) {
        int[] a = new int[] {3,2}; 
        int[] b = new int[] {1,9}; 
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
            }
            
            r[r.length-1 - (i+l)] = r[r.length-1 - (i+l)] + carry;
            while (r[r.length-1 - (i+l)] < 0) {
                carry = 0;
                for (int j = 0; j <= l-1; j++) {
                    tmp = r[r.length-1 - (i+j)] + b[b.length-1 - i] + carry;
                    carry = tmp/base;
                    r[r.length-1 - (i+j)] = tmp%base; 
                }
                r[r.length-1 - (i+l)] = r[r.length-1 - (i+l)] + carry;
                q[q.length-1 - i] = q[q.length-1 - i] - 1;
            }
        }
        for(int i = q.length-1; i>=0; i--) {
        System.out.println(q[i]);
	}
        for(int i = r.length-1; i >= 0; i--) {
            System.out.println(r[i]);
        }
        return  q;
    }
    
    
    public static void main(String[] args) {
        test();
    }
}



