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
    Number run(Number a, Number b) {
        if(add) {
            add(a,b);
        } else {
            sub(a,b);
        }
        return new Number( 2, false);
    }
    
    Number add(Number num1, Number num2) {
	if (num1.isPositive() && num2.isPositive() || !num1.isPositive() && !num2.isPositive()) {
            return new Number(addition(num1, num2), num1.getRadix(), num1.isPositive());
	} else if (num1.isPositive() && !num2.isPositive()) {
            num2.flip();
            return sub(num1, num2);
	} else {
            num1.flip();
            return sub(num2, num1);
	}
    }
    
    int[] addition(Number num1, Number num2) {
	convert(num1, num2);
	int[] result = new int[num1.value.length + 1];
	int base = num1.radix;
	int carry = 0;
	for (int i = num1.value.length - 1; i >= 0; i--) {
            result[i] = num1.value[i] + num2.value[i] + carry;
            if (result[i] >= base) {
                result[i] = result[i] - base;
                carry = 1;
            } else {
                carry = 0;
            }
        }
        result[0] = carry;
        for(int s: num2.value){
            System.out.print(s);
        }
        System.out.print("\n");
        for(int s: num1.value){
            System.out.print(s);
        }
        System.out.print("\n");
        for(int s: result){
            System.out.print(s);
        }
        System.out.print("\n");
        return result;
    }
    
    Number sub(Number num1, Number num2) {
        if (num1.isPositive() && num2.isPositive()) {
            if (num1.thisBiggerThan(num2)) {
                return new Number(subtraction(num1, num2),num1.radix , true);
            } else {
                return new Number(subtraction(num2, num1), num1.radix, false);
            }
        } else if (!num1.isPositive() && num2.isPositive()) {
            return new Number(addition(num1, num2),num1.radix , false);
        } else if (num1.isPositive() && !num2.isPositive()) {
            return new Number(addition(num1, num2),num1.radix , true);
        } else {
            if (num1.thisBiggerThan(num2)) {
                return new Number(subtraction(num1, num2),num1.radix , false);
            } else {
                return new Number(subtraction(num2, num1), num1.radix, true);
            }
        }
    }
    
    int[] subtraction(Number num1, Number num2) {
	convert(num1, num2);
	int[] result = new int[num1.value.length];
	int base = num1.radix;
	int carry = 0;
	for (int i = num1.value.length - 1; i >= 0; i--) {
            result[i] = num1.value[i] - num2.value[i] - carry;
            if (result[i] < 0) {
                result[i] = result[i] + base;
                carry = 1;
            } else {
                carry = 0;
            }
        }
        return result;
    }
    
}
