/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2wf90.assignment.pkg1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author s151810
 */
public class NumberTest {
    
    public NumberTest() {
    }

    /**
     * Test of stringToIntArr method, of class Number.
     */
    @Test
    public void testStringToIntArr() {
        boolean check = false;
        System.out.println("stringToIntArr");
        Number a = new Number("123", 10, true);
        int[] result = {1,2,3};
        a.stringToIntArr(result.length);
        for(int i = 0; i<a.getIntArr().length; i++){
            if(a.getIntArr()[i] == result[i]){
                check = true;
            } else {
                check = false;
                break;
            }
        }
        assertTrue(check);
    }
        
    @Test
    public void testStringToIntArrNeg() {
        boolean check = false;
        System.out.println("stringToIntArr");
        Number a = new Number("-123", 10, false);
        int[] result = {1,2,3};
        a.stringToIntArr(result.length);
        for(int i = 0; i<a.getIntArr().length; i++){
            if(a.getIntArr()[i] == result[i]){
                check = true;
            } else {
                check = false;
                break;
            }
        }
        assertTrue(check);
    }
    
    @Test
    public void testStringToIntArrBiggerLength() {
        boolean check = false;
        System.out.println("stringToIntArr");
        Number a = new Number("123", 10, true);
        int[] result = {0,0,1,2,3};
        a.stringToIntArr(5);
        for(int i = 0; i<a.getIntArr().length; i++){
            if(a.getIntArr()[i] == result[i]){
                check = true;
            } else {
                check = false;
                break;
            }
        }
        assertTrue(check);
    }
    
    @Test
    public void testStringToIntArr0(){
        boolean check = false;
        System.out.println("stringToIntArr");
        Number a = new Number("0", 2, true);
        int[] result = {0,0,0,0,0};
        a.stringToIntArr(5);
        for(int i = 0; i<a.getIntArr().length; i++){
            if(a.getIntArr()[i] == result[i]){
                check = true;
            } else {
                check = false;
                break;
            }
        }
        assertTrue(check);
    }
    
    @Test
    public void testStringToIntArrHex(){
        boolean check = false;
        System.out.println("stringToIntArr");
        Number a = new Number("abcdef", 16, true);
        int[] result = {10,11,12,13,14,15};
        a.stringToIntArr(result.length);
        for(int i = 0; i<a.getIntArr().length; i++){
            if(a.getIntArr()[i] == result[i]){
                check = true;
            } else {
                check = false;
                break;
            }
        }
        assertTrue(check);
    }

    /**
     * Test of intToStringArr method, of class Number.
     */
    @Test
    public void testIntToStringArr() {
        System.out.println("intToStringArr");
        Number a = new Number(new int[] {2,5,9}, 10, true);
        Number instance = null;
        char[] expResult = new char[] {'2', '5', '9'};
        char[] result = a.getChars();
        System.out.println(a.getChars().length);
        assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testIntToStringArrHex() {
        System.out.println("intToStringArr");
        Number a = new Number(new int[] {2,15,11}, 16, true);
        Number instance = null;
        char[] expResult = new char[] {'2', 'f', 'b'};
        char[] result = a.getChars();
        System.out.println(a.getChars().length);
        assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testIntToStringArrHexNeg() {
        System.out.println("intToStringArr");
        Number a = new Number(new int[] {2,15,11}, 16, false);
        Number instance = null;
        char[] expResult = new char[] {'-', '2', 'f', 'b'};
        char[] result = a.getChars();
        System.out.println(a.getChars().length);
        assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testIntToStringArrNeg0() {
        System.out.println("intToStringArr");
        Number a = new Number(new int[] {0,0,0}, 10, false);
        Number instance = null;
        char[] expResult = new char[] {'0'};
        char[] result = a.getChars();
        System.out.println(a.getChars().length);
        assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testIntToStringArrHexNegFrontZeroes() {
        System.out.println("intToStringArr");
        boolean check = false;
        Number a = new Number(new int[] {0, 0, 2,15,11}, 16, false);
        Number instance = null;
        char[] expResult = new char[] {'-', '2', 'f', 'b'};
        char[] result = a.getChars();
        System.out.println(a.getChars().length);
        for(int i = 0; i < a.getChars().length; i++){
            if(a.getChars()[i] == expResult[i]){
                check = true;
                System.out.println(a.getChars()[i]);
            } else {
                check = false; 
                break;
            }
        }
        assertTrue(check);
    }

    /**
     * Test of thisBiggerThan method, of class Number.
     */
    @Test
    public void testThisBiggerThan() {
        System.out.println("thisBiggerThan");
        Number other = null;
        Number instance = null;
        boolean expResult = false;
        boolean result = instance.thisBiggerThan(other);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
}
