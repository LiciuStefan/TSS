package ro.unibuc.hello.services;

import org.springframework.stereotype.Component;
import ro.unibuc.hello.exceptions.InputLengthIncorrectException;
import ro.unibuc.hello.exceptions.StringLengthDoesNotMatchNException;

@Component
public class TSSService {

    public boolean checkAllEqualToC(int n, String s, char c) {
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
