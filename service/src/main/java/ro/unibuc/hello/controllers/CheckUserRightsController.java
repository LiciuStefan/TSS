package ro.unibuc.hello.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.entities.Action;
import ro.unibuc.hello.exceptions.UserNotAuthorizedException;
import ro.unibuc.hello.services.CheckUserRightsService;

@Controller
public class CheckUserRightsController {

    @Autowired
    private CheckUserRightsService checkUserRightsService;

    @PostMapping("/authorize/{id}")
    @ResponseBody
    public ResponseEntity<?> checkUserRights(@PathVariable String id, @RequestBody String actionCode) {
            return new ResponseEntity<>(checkUserRightsService.checkUserRights(id, actionCode), HttpStatus.ACCEPTED);
    }
}
