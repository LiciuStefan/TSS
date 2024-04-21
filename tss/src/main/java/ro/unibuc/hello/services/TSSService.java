package ro.unibuc.hello.services;

@Component
public class TSSService {

    public boolean checkAllEqualToC(int n, String s, Char c) {
        if(n < 1 || n > 10)
            throw new InputLengthIncorrectException();
        if(s.length() != n)
            throw new StringLengthDoesNotMatchNException();
        for(int i=0; i<n; i++)
            if(s.charAt(i) != c)
                return false;
        return true;
    }
}
