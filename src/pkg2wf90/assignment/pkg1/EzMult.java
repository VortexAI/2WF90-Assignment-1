
package pkg2wf90.assignment.pkg1;

/**
 *
 * @author s1233570
 */
public class EzMult extends Function{
    /** Case distinction to determine of the answer will be positive or negative
     * 
     * @param num1 First Number
     * @param num2 Second Number
     * @return The result of multiplying both numbers
     */
    @Override
    Number run(Number num1, Number num2) {
        convert(num1, num2);
        // If both numbers are negative or positive the answer will be positive
        if (num1.positive == num2.positive) {
            return new Number(mult(num1, num2), num1.getRadix(), true);
        } else {
            return new Number(mult(num1, num2), num1.getRadix(), false);
        }
    }
    /** Does the multiplication of two numbers
     * 
     * @param num1 The first number
     * @param num2 The Second number
     * @return An int array that contains the result of the multiplication
     */
    int[] mult(Number num1, Number num2) {
        // create the result array
        int[] result = new int[2 * num1.value.length];
        int base = num1.radix;
        int carry = 0;
        int temp = 0;
        
        // loop over all elements back to front
        for (int i = num1.value.length - 1; i >= 0; i--){
            // reset the carry
            carry = 0;
            for (int j = num2.value.length - 1; j >= 0; j--) {
                temp = result[i + j] + (num1.value[i] * num2.value[j]) + carry;
                carry = Math.floorMod(temp, base);
                result[i + j] = temp - (carry * base);
            }
            result[i + num2.value.length - 1] = carry;
        }
        return result;
    }
    
}
