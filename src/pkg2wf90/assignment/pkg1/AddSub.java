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
public class AddSub extends Function{

    boolean add;
    
    AddSub(boolean add){
        this.add = add;
    }

    @Override
    void run(Number a, Number b) {
        if(add) {
            add(a,b);
        } else {
            sub(a,b);
        }
        
    }
    
    Number add(Number num1, Number num2) {
	if (num1.isPositive() && num2.isPositive() || !num1.isPositive() && !num2.isPositive()) {
		return new Number(addition(num1, num2), num1.getRadix(), num1.isPositive());
	} else if (num1.isPositive() && !num2.isPositive()) {
		return sub(num1, num2);
	} else {
		return sub(num2, num1);
	}
    }
    
    int[] addition(Number num1, Number num2) {
	convert(num1, num2);
	int[] result = new int[num1.value.length + 1];
	int base = num1.radix;
	int carry = 0;
	for (int i = num1.value.length-1; i >= 0; i++) {
            
        }
        return result;
    }
    
    Number sub(Number num1, Number num2) {
        return num1;
    }
    
    
}
