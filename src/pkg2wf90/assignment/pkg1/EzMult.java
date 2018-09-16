
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
    Number run(Number num1, Number num2, Number m) {
        convert(num1, num2);
        
        Number ans = null;
        // If both numbers are negative or positive the answer will be positive
        if (num1.isPositive() == num2.isPositive()) {
            ans = mult(num1, num2, true);
        } else {
            ans = mult(num1, num2, false);
        }
        if (m != null) {
            Reduce reduction = new Reduce();
            ans = reduction.run(ans, m, null);
        }
        return ans;
    }
    /** Does the multiplication of two numbers
     * 
     * @param num1 The first number
     * @param num2 The Second number
     * @return An int array that contains the result of the multiplication
     */
    Number mult(Number num1, Number num2, boolean positive) {
        // create the result array
        int[] result = new int[num1.getIntArr().length + num2.getIntArr().length];
        int base = num1.getRadix();
        int carry = 0;
        int temp = 0;
        int countAdd = 0;
        int countMult = 0;
        
        // reverse the numbers to make the calculations with the int Array easier
        Number reverseNum1 = new Number(reverse(num1.getIntArr()), num1.getRadix(), num1.isPositive());
        Number reverseNum2 = new Number(reverse(num2.getIntArr()), num2.getRadix(), num2.isPositive());
        
                
        // loop over all elements back to front
        for (int i = 0; i <= (reverseNum1.getIntArr().length - 1); i++){
            // reset the carry
            carry = 0;
            // loop over all elements back to front
            for (int j = 0; j <= (reverseNum2.getIntArr().length - 1); j++) {
                // set the temp value to the current value of Z and add X times Y and add the carry
                temp = result[i + j] + (reverseNum1.getIntArr()[i] * reverseNum2.getIntArr()[j]) + carry;
                // the carry is the temp value divided by the base
                carry = temp/base;
                // the result is the temp value minus the carry times the base
                result[i + j] = temp - (carry * base);
                // add to the counter for add and mult how many they have done
                countAdd = countAdd + 3;
                countMult = countMult + 2;
            }
            result[i + reverseNum2.getIntArr().length] = carry;
        }
        // Create a number number that will be returned as the result
        Number resultNumber = new Number(reverse(result), base, positive);
        resultNumber.setCountMult(countMult);
        resultNumber.setCountAdd(countAdd);
        return resultNumber;
    }
    
    /** Reverse the Int Array
     * 
     * @param original the original Int Array
     * @return the reversed Int Array
     */
    int[] reverse(int[] original) {
        int[] tempIntArray = new int[original.length];
        int counter = 0;
        
        for (int i = original.length - 1; i >= 0; i--) {
            tempIntArray[counter] = original[i];
            counter = counter + 1;
        }
        return tempIntArray;
    }
    
}
