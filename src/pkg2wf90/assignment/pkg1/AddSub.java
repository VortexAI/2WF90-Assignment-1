package pkg2wf90.assignment.pkg1;

public class AddSub extends Function{

    boolean add;
    
    AddSub(boolean add){
        this.add = add;
    }
    
    /** Sends the numbers to the correct section (adding/subtracting)
     * 
     * @param num1 The first number
     * @param num2 The second number
     * @return the result of the addition/subtraction
     */
    @Override
    Number run(Number num1, Number num2) {
        
        if(add) {
            return add(num1, num2);
        } else {
            return sub(num1, num2);
        }
    }
    
    /** Does a case distinction on given numbers for addition
     * 
     * @param num1 First Number
     * @param num2 Second Number
     * @return The result of adding both numbers
     */
    Number add(Number num1, Number num2) {
        convert(num1, num2);
        
        // If both are positive or both negative we add
	if (num1.isPositive() && num2.isPositive() || !num1.isPositive() && !num2.isPositive()) {
            return new Number(addition(num1.getIntArr(), num2.getIntArr(), num1.getRadix()), num1.getRadix(), num1.isPositive());
            
        // If one is negative, we subtract the negative from the positive
	} else if (num1.isPositive() && !num2.isPositive()) {
            num2.flip();
            return sub(num1, num2);
	} else {
            num1.flip();
            return sub(num2, num1);
	}
    }
    
    /** Does the addition of two int arrays
     * 
     * @param num1 The first number in an int array
     * @param num2 The second number in an int array
     * @param radix The radix
     * @return An int array containing the result of the addition
     */
    int[] addition(int[] num1, int[] num2, int radix) {
        // create result array
	int[] result = new int[num1.length + 1];
	int carry = 0;
        
        // loop over all elements from back to front
	for (int i = num1.length - 1; i >= 0; i--) {
            // do the addition
            result[i + 1] = num1[i] + num2[i] + carry;
            
            // change number to correct base if needed and adjust carry
            if (result[i + 1] >= radix) {
                result[i + 1] = result[i + 1] - radix;
                carry = 1;
            } else {
                carry = 0;
            }
        }
        result[0] = carry;
        return result;
    }
    
    /** Does a case distinction on the two numbers for subtraction
     * 
     * @param num1 The first number
     * @param num2 The second number
     * @return The result of subtracting num2 from num1
     */
    Number sub(Number num1, Number num2) {
        convert(num1, num2);
        
        // if both are positive
        if (num1.isPositive() && num2.isPositive()) {
            // if num1 is bigger than num2, then subtract as normal
            if (num1.thisBiggerThan(num2)) {
                return new Number(subtraction(num1.getIntArr(), num2.getIntArr(), num1.getRadix()), num1.getRadix(), true);
            // else subtract num1 from num2 and change the sign
            } else {
                return new Number(subtraction(num2.getIntArr(), num1.getIntArr(), num1.getRadix()), num1.getRadix(), false);
            }
            
        // We have to add when the signs of the numbers differ
        } else if (!num1.isPositive() && num2.isPositive()) {
            return new Number(addition(num1.getIntArr(), num2.getIntArr(), num1.getRadix()), num1.getRadix(), false);
        } else if (num1.isPositive() && !num2.isPositive()) {
            return new Number(addition(num1.getIntArr(), num2.getIntArr(), num1.getRadix()), num1.getRadix(), true);
            
        // if both are negative
        } else {
            if (num1.thisBiggerThan(num2)) {
                // if num1 is bigger than num2, then subtract as normal and stay negative
                return new Number(subtraction(num1.getIntArr(), num2.getIntArr(), num1.getRadix()), num1.getRadix(), false);
            } else {
                // else subtract num1 from num2 and change signs
                return new Number(subtraction(num2.getIntArr(), num1.getIntArr(), num1.getRadix()), num1.getRadix(), true);
            }
        }
    }
    
    /** Does the subtraction of two int arrays
     * 
     * @param num1 The first number in an int array
     * @param num2 The second number in an int array
     * @param radix The radix
     * @return An int array containing the result of the subtraction
     */
    int[] subtraction(int[] num1, int[] num2, int radix) {
	// create result array
	int[] result = new int[num1.length];
	int carry = 0;
        
        // loop over all elements from back to front
	for (int i = num1.length - 1; i >= 0; i--) {
            // do the subtraction
            result[i] = num1[i] - num2[i] - carry;
            
            // Adjust to the correct base and change the carry
            if (result[i] < 0) {
                result[i] = result[i] + radix;
                carry = 1;
            } else {
                carry = 0;
            }
        }
        return result;
    }
    
}
