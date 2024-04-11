package ro.unibuc.hello.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.unibuc.hello.dtos.ActionDTO;
import ro.unibuc.hello.exceptions.UserNotAuthorizedException;

@Component
public class CheckUserRightsService {

    @Autowired
    private UserService userService;

    @Autowired
    private ActionService actionService;

    public Boolean checkUserRights(String id, String actionCode) {
        ActionDTO action = actionService.getActionById(actionCode);
        boolean hasRights = false;
        var user = userService.getUserById(id);
        var policies = user.getPolicies();
        for(var policy : policies) {
            if(policy.toPolicy().hasRightsToAction(action) == 1) {
                hasRights = true;
            }
            if(policy.toPolicy().hasRightsToAction(action) == -1) {
                throw new UserNotAuthorizedException(user.getId());
            }
        }

        if(hasRights) {
            return true;
        }

        var roles = user.getRoles();
        for(var role : roles) {
            for(var policy : role.getPolicies()) {
                if(policy.toPolicy().hasRightsToAction(action) == 1) {
                    hasRights = true;
                }
                if(policy.toPolicy().hasRightsToAction(action) == -1) {
                    throw new UserNotAuthorizedException(user.getId());
                }
            }
        }

        return hasRights;
    }

}
