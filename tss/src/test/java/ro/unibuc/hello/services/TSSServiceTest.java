package ro.unibuc.hello.services;


import org.junit.jupiter.api.Test;
import ro.unibuc.hello.exceptions.InputLengthIncorrectException;
import ro.unibuc.hello.exceptions.StringLengthDoesNotMatchNException;

import static org.junit.jupiter.api.Assertions.*;

public class TSSServiceTest {

    TSSService tssService = new TSSService();

    @Test
    public void equivalencePartitioning() {
        // InputLengthIncorrectException() is thrown when n < 1 or n > 10
        assertThrows(InputLengthIncorrectException.class, () -> tssService.checkAllEqualToC(0, "", 'a'));
        assertThrows(InputLengthIncorrectException.class, () -> tssService.checkAllEqualToC(20, "", 'a'));

        // StringLengthDoesNotMatchNException() is thrown when s.length() != n
        assertThrows(StringLengthDoesNotMatchNException.class, () -> tssService.checkAllEqualToC(3, "aaaaaa", 'a'));
        assertThrows(StringLengthDoesNotMatchNException.class, () -> tssService.checkAllEqualToC(10, "aaaaaa", 'a'));

        // true is returned when all characters in s are equal to c

        assertTrue(tssService.checkAllEqualToC(6, "aaaaaa", 'a'));
        // false is returned when at least one character in s is different from c
        assertFalse(tssService.checkAllEqualToC(6, "aaaaaa", 'b'));
    }

    @Test
    public void boundaryValueAnalysis() {
        assertThrows(InputLengthIncorrectException.class, () -> tssService.checkAllEqualToC(0, "", 'a'));
        assertThrows(InputLengthIncorrectException.class, () -> tssService.checkAllEqualToC(11, "", 'a'));

        assertThrows(StringLengthDoesNotMatchNException.class, () -> tssService.checkAllEqualToC(1, "aa", 'a'));
        assertThrows(StringLengthDoesNotMatchNException.class, () -> tssService.checkAllEqualToC(10, "aaaaaaaaa", 'a'));

        assertTrue(tssService.checkAllEqualToC(10, "aaaaaaaaaa", 'a'));
        assertFalse(tssService.checkAllEqualToC(10, "aaaaaaaaab", 'a'));
        assertFalse(tssService.checkAllEqualToC(10, "bbbbbbbbbb", 'a'));
    }

    @Test
    public void categoryPartitioning() {
        // Negative n category
        assertThrows(InputLengthIncorrectException.class, () -> tssService.checkAllEqualToC(-5, "", 'a'));
        // N = 0 category
        assertThrows(InputLengthIncorrectException.class, () -> tssService.checkAllEqualToC(0, "", 'a'));
        // N = 11 category
        assertThrows(InputLengthIncorrectException.class, () -> tssService.checkAllEqualToC(11, "", 'a'));
        // N > 11 category
        assertThrows(InputLengthIncorrectException.class, () -> tssService.checkAllEqualToC(20, "", 'a'));

        // 1  <= N <= 10 category
            // S.length() != N category
            assertThrows(StringLengthDoesNotMatchNException.class, () -> tssService.checkAllEqualToC(1, "aaaaaa", 'a'));
            // S.length() == N category
               // All characters in S are equal to C category
               assertTrue(tssService.checkAllEqualToC(10, "aaaaaaaaaa", 'a'));
               // One character in S is different from C category
               assertFalse(tssService.checkAllEqualToC(10, "aaaaaaaaab", 'b'));
               // All characters in S are different from C category
               assertFalse(tssService.checkAllEqualToC(10, "bbbbbbbbbb", 'a'));
               // More than one but not all characters in S are different from C category
               assertFalse(tssService.checkAllEqualToC(10, "ababababab", 'a'));
    }

    @Test
    public void statementCoverage() {
        // InputLengthIncorrectException()
        assertThrows(InputLengthIncorrectException.class, () -> tssService.checkAllEqualToC(0, "", 'a'));
        // StringLengthDoesNotMatchNException()
        assertThrows(StringLengthDoesNotMatchNException.class, () -> tssService.checkAllEqualToC(4, "aaa", 'a'));
        // s.charAt(i) == c
        assertTrue(tssService.checkAllEqualToC(6, "aaaaaa", 'a'));
        // s.charAt(i) != c
        assertFalse(tssService.checkAllEqualToC(6, "ababab", 'a'));
    }

    @Test
    public void branchCoverage() {
        // InputLengthIncorrectException()
        assertThrows(InputLengthIncorrectException.class, () -> tssService.checkAllEqualToC(0, "", 'a'));
        // StringLengthDoesNotMatchNException()
        assertThrows(StringLengthDoesNotMatchNException.class, () -> tssService.checkAllEqualToC(4, "aaa", 'a'));
        // s.charAt(i) == c
        assertTrue(tssService.checkAllEqualToC(6, "aaaaaa", 'a'));
        // s.charAt(i) != c
        assertFalse(tssService.checkAllEqualToC(6, "ababab", 'a'));
    }

    @Test
    public void conditionCoverage() {
        // n < 1
        assertThrows(InputLengthIncorrectException.class, () -> tssService.checkAllEqualToC(0, "", 'a'));
        // 1 <= n <= 10
        assertTrue(tssService.checkAllEqualToC(4, "aaaa", 'a'));
        // n > 10
        assertThrows(InputLengthIncorrectException.class, () -> tssService.checkAllEqualToC(11, "aaaaaa", 'a'));

        // s.length() != n
        assertThrows(StringLengthDoesNotMatchNException.class, () -> tssService.checkAllEqualToC(6, "aaa", 'a'));
        // s.length() == n
        assertTrue(tssService.checkAllEqualToC(6, "aaaaaa", 'a'));

        // s.charAt(i) != c
        assertFalse(tssService.checkAllEqualToC(6, "ababab", 'a'));
        // s.charAt(i) == c
        assertTrue(tssService.checkAllEqualToC(6, "aaaaaa", 'a'));
    }
}