package ro.unibuc.hello.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason = "User does not have rights to access")
public class UserNotAuthorizedException extends RuntimeException {

    private static final String userNotAuthorizedTemplate = "User: %s does not have the required rights";

    public UserNotAuthorizedException(String userId) {
        super(String.format(userNotAuthorizedTemplate, userId));
    }
}
