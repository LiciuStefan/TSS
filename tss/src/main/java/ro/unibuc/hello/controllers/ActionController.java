package ro.unibuc.hello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.hello.dtos.ActionDTO;
import ro.unibuc.hello.entities.Action;
import ro.unibuc.hello.exceptions.EntityAlreadyExistsException;
import ro.unibuc.hello.exceptions.EntityNotFoundException;
import ro.unibuc.hello.services.ActionService;

import java.util.List;

@Controller
public class ActionController {

    @Autowired
    private ActionService actionService;

    @PostMapping("/actions")
    @ResponseBody
    public ActionDTO createAction(@RequestBody Action action) {
        return actionService.addAction(action.getCode(), action.getDescription());
    }

    @GetMapping("/actions")
    @ResponseBody
    public List<ActionDTO> seeActions(){
        return actionService.getActions();
    }

    @GetMapping("/actions/{code}")
    @ResponseBody
    public ActionDTO seeAction(@PathVariable String code){
        return actionService.getActionById(code);
    }

    @PutMapping("/actions/{code}")
    @ResponseBody
    public ActionDTO updateAction(@PathVariable String code, @RequestBody Action action) {
        return actionService.updateAction(code, action);
    }

    @DeleteMapping("/actions/{code}")
    @ResponseBody
    public void deleteAction(@PathVariable String code) {
        actionService.deleteAction(code);
    }
}
