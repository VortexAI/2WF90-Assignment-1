
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
        if (num1.isPositive() == num2.isPositive()) {
            return mult(num1, num2, true);
        } else {
            return mult(num1, num2, false);
        }
    }
    /** Does the multiplication of two numbers
     * 
     * @param num1 The first number
     * @param num2 The Second number
     * @return An int array that contains the result of the multiplication
     */
    Number mult(Number num1, Number num2, boolean positive) {
        // create the result array
        int[] result = new int[2 * num1.getIntArr().length];
        int base = num1.getRadix();
        int carry = 0;
        int temp = 0;
        int countAdd = 0;
        int countMult = 0;
        
        Number reverseNum1 = new Number(reverse(num1.getIntArr()), num1.getRadix(), num1.isPositive());
        Number reverseNum2 = new Number(reverse(num2.getIntArr()), num2.getRadix(), num2.isPositive());
        
                
        // loop over all elements back to front
        for (int i = 0; i <= (reverseNum1.getIntArr().length - 1); i++){
            // reset the carry
            carry = 0;
            for (int j = 0; j <= (reverseNum2.getIntArr().length - 1); j++) {
                temp = result[i + j] + (reverseNum1.getIntArr()[i] * reverseNum2.getIntArr()[j]) + carry;
                carry = Math.floorDiv(temp, base);
                result[i + j] = temp - (carry * base);
                countAdd = countAdd + 3;
                countMult = countMult + 2;
            }
            result[i + reverseNum2.getIntArr().length] = carry;
        }
        return new Number(reverse(result), base, positive);
    }
    
    int[] reverse(int[] original) {
        int[] tempIntArray = new int[original.length];
        int counter = 0;
        
        for (int i = original.length - 1; i >= 0; i--) {
            tempIntArray[counter] = original[i];
            //System.out.println(counter);
            //System.out.println(tempIntArray[counter]);
            counter = counter + 1;
        }
        return tempIntArray;
    }
    
}
