package com.junit.firstjunit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
public class JunitOperationTest {

    JunitOperation opertaion;

    /*
     * @BeforeAll static void beforeAllLifeCycleMethod() {
     * System.out.println("this method gets call before all the methods"); }
     *
     * @AfterAll static void afterAllLifeCycleMethod() {
     * System.out.println("this method gets call after all the methods"); }
     */

    @BeforeEach
    void tearUp() {
        opertaion = new JunitOperation();
        // System.out.println("Before each method");
    }

    @AfterEach
    void tearDown() {
        opertaion = null;
        // System.out.println("After each method");
    }

    @Nested
    class AddMethodTest {

        @Test
        @DisplayName("Method For Positive Input for AddMethod")
        @Disabled
        void add_For_Positive_Number() {

            // JunitOperation opertaion=new JunitOperation();
            int actual = opertaion.add(4, 8);
            int expected = 15;

            assertEquals(expected, actual, " expected Result is 12");
            // assertNotEquals(expected, actual);
        }

        @Test
        @DisplayName("For NegativeCheck in AddMethod")
        void add_For_Negative_Number() {
            // JunitOperation opertaion=new JunitOperation();

            assertEquals(25, opertaion.add(30, -5), " expected Result is 25 ");
        }

        @Test
        @DisplayName("For Zero in AddMethod")
        //@Disabled
        @Tag("addZero")
        void add_For_zero_Number() {
            // JunitOperation opertaion=new JunitOperation();

            assertEquals(0, opertaion.add(0, 0), " expected Result is 0 ");
        }
    }

    @Test
    void test_Multiply() {
        // JunitOperation opertaion=new JunitOperation();

        assertEquals(0, opertaion.multiply(0, -12), " expected Result is 0 ");
    }

    @Test
    @Tag("checkNull")
    void test_Check_Null() {
        // JunitOperation opertaion=new JunitOperation();
        String str = null;
        String str2 = "book";

        assertNull(opertaion.checkNull(str), "Object should be Null");
        assertNotNull(opertaion.checkNull(str2), "Object should be Not Null");
    }
}
