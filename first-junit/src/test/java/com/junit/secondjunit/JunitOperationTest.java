package com.junit.secondjunit;

import com.junit.secondjunit.JunitOperation;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class JunitOperationTest {

    JunitOperation opertaion;


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

    @Test
    @Order(5)
    void testSameAndNoSameReference() {
        String loc = "Pune";
        assertSame(opertaion.getCompany(), opertaion.getCompanyDuplicate(), "Object should refer to the same Object");
        assertNotSame(loc, opertaion.getCompany(), "Object should refer not to the same Object");


    }


    @Test
    @Order(4)
    void testArrayEquals() {
        String[] loc = {"A", "B", "C"};
        assertArrayEquals(loc, opertaion.getThreeletters(), "output should be A,B,C");

    }

    @Test
    @Order(3)
    void testArrayIterable() {
        List<String> strarr = List.of("syne", "apple", "samsung", "indus");
        assertIterableEquals(strarr, opertaion.getCompylist(), "Expected should match with actuals");

    }

    @Test
    @Order(2)
    void testLineMatch() {
        List<String> strarr = List.of("syne", "apple", "samsung", "indus");
        assertLinesMatch(strarr, opertaion.getCompylist(), "Expected should match with actuals");

    }

    @Test
    @Order(6)
    void testIsGreater() {
        assertTrue(opertaion.isGreater(10, 5), "This will return true ");
        assertFalse(opertaion.isGreater(10, 55), "This will return false");
    }


    @Test
    @Order(1)
    void testMethodHasException() {
        assertThrows(RuntimeException.class, () -> {
            opertaion.methodHasException(-1);
        }, "should throw exception");
        Assertions.assertDoesNotThrow(() -> {
            opertaion.methodHasException(2);
        }, "should throw exception");
    }
}

