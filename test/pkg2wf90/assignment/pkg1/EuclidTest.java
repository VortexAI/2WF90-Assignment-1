/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2wf90.assignment.pkg1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author s166268
 */
public class EuclidTest {
    
    public EuclidTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of run method, of class Euclid.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        Number a = new Number("36", 10, true);
        Number b = new Number("30", 10, true);
        Euclid instance = new Euclid();
        Number expResult = new Number("6", 10, true);
        Number result = instance.run(a, b, a);
        for(int i = result.getChars().length-1; i >= 0; i--) {
            assertEquals(expResult.getChars() [i], result.getChars() [i]);
        }
    }

    /**
     * Test of euclid method, of class Euclid.
     */
    @Test
    public void testEuclid() {
        System.out.println("euclid");
        Number num1 = null;
        Number num2 = null;
        Euclid instance = new Euclid();
        Number expResult = null;
        Number result = instance.euclid(num1, num2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of division method, of class Euclid.
     */
    @Test
    public void testDivision() {
        System.out.println("division");
        int[] a = new int[] {2, 5};
        int[] b = new int[] {6};
        int[] q = new int[] {0, 4};
        Number num1 = new Number(a, 10, true);
        Number num2 = new Number(b, 10, true);
        Euclid instance = new Euclid();
        Number expResult = new Number(q, 10, true);
        Number result = instance.division(num1, num2);
        for (int i = result.getIntArr().length - 1; i >= 0; i--) {
            assertEquals(expResult.getIntArr() [i], result.getIntArr() [i]);
        }
    }
    
}
