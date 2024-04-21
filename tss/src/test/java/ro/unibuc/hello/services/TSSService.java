public class TSSService {

    TSSService tssService = new TSSService();

    @Test
    public void equivalencePartitioning() {
        // InputLengthIncorrectException() is thrown when n < 1 or n > 10
        tssService.checkAllEqualToC(0, "", 'a');
        tssService.checkAllEqualToC(20, "", 'a');
        // StringLengthDoesNotMatchNException() is thrown when s.length() != n
        tssService.checkAllEqualToC(3, "aaaaaa", 'a');
        tssService.checkAllEqualToC(10, "aaaaaa", 'a');
        // true is returned when all characters in s are equal to c
        tssService.checkAllEqualToC(6, "aaaaaa", 'a');
        // false is returned when at least one character in s is different from c
        tssService.checkAllEqualToC(6, "aaaaaa", 'b');
    }

    @Test
    public void boundaryValueAnalysis() {
        tssService.checkAllEqualToC(0, "", 'a');
        tssService.checkAllEqualToC(11, "", 'a');

        tssService.checkAllEqualToC(1, "aa", 'a');
        tssService.checkAllEqualToC(10, "aaaaaaaaa", 'a');

        tssService.checkAllEqualToC(10, "aaaaaaaaaa", 'a');
        tssService.checkAllEqualToC(10, "aaaaaaaaab", 'a');
        tssService.checkAllEqualToC(10, "bbbbbbbbbb", 'a');
    }

    @Test
    public void categoryPartitioning() {
        // Negative n category
        tssService.checkAllEqualToC(-5, "", 'a');
        // N = 0 category
        tssService.checkAllEqualToC(0, "", 'a');
        // N = 11 category
        tssService.checkAllEqualToC(11, "", 'a');
        // N > 11 category
        tssService.checkAllEqualToC(20, "", 'a');

        // 1  <= N <= 10 category
            // S.length() != N category
            tssService.checkAllEqualToC(1, "aaaaaa", 'a');
            // S.length() == N category
               // All characters in S are equal to C category
               tssService.checkAllEqualToC(10, "aaaaaaaaaa", 'a');
               // One character in S is different from C category
               tssService.checkAllEqualToC(6, "aaaaaaaaab", 'b');
               // All characters in S are different from C category
               tssService.checkAllEqualToC(6, "bbbbbbbbbb", 'a');
               // More than one but not all characters in S are different from C category
               tssService.checkAllEqualToC(6, "ababababab", 'a');
    }

    @Test
    public void statementCoverage() {
        // InputLengthIncorrectException()
        tssService.checkAllEqualToC(0, "", 'a');
        // StringLengthDoesNotMatchNException()
        tssService.checkAllEqualToC(4, "aaa", 'a');
        // s.charAt(i) == c
        tssService.checkAllEqualToC(6, "aaaaaa", 'a');
        // s.charAt(i) != c
        tssService.checkAllEqualToC(6, "ababab", 'a');
    }

    @Test
    public void branchCoverage() {
        // InputLengthIncorrectException()
        tssService.checkAllEqualToC(0, "", 'a');
        // StringLengthDoesNotMatchNException()
        tssService.checkAllEqualToC(4, "aaa", 'a');
        // s.charAt(i) == c
        tssService.checkAllEqualToC(6, "aaaaaa", 'a');
        // s.charAt(i) != c
        tssService.checkAllEqualToC(6, "ababab", 'a');
    }

    @Test
    public void conditionCoverage() {
        // n < 1
        tssService.checkAllEqualToC(0, "", 'a');
        // 1 <= n <= 10
        tssService.checkAllEqualToC(4, "aaa", 'a');
        // n > 10
        tssService.checkAllEqualToC(11, "aaaaaa", 'a');

        // s.length() != n
        tssService.checkAllEqualToC(6, "aaa", 'a');
        // s.length() == n
        tssService.checkAllEqualToC(6, "aaaaaa", 'a');

        // s.charAt(i) != c
        tssService.checkAllEqualToC(6, "ababab", 'a');
        // s.charAt(i) == c
        tssService.checkAllEqualToC(6, "aaaaaa", 'a');
    }
}