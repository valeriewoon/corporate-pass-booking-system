package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.*;

import project.entity.*;
import project.service.*;

// import org.springframework.web.bind.annotation.*;
import java.util.*;

// Contains CRUD mapping for the API endpoints

@RestController
public class PassController {
    @Autowired
    private PassService passService;

    @GetMapping("/passes")
    public List<Pass> fetchPassList() {
        return passService.getAllPasses();
    }

    @GetMapping("/passes/{id}")
    // Path variable is input parameter to our method
    public Pass getPassById(@PathVariable("id") Long passId) {
        return passService.getPassById(passId);
    }

    @PostMapping("/passes")
    public Pass savePass(@RequestBody Pass pass) {
        return passService.savePass(pass);
    }

    @PutMapping("/passes/{id}")
    public Pass updatePassById(@PathVariable("id") Long passId, @RequestBody Pass pass) {
        return passService.updatePass(passId, pass);
    }

    @GetMapping("/getTotalPassNum")
    public Map<String, Integer> getTotalPassNum(){
        return passService.getTotalPassNum();
    }

    @PutMapping("/reportLostPass/{loanId}/{passId}")
    public void reportLostPass(@PathVariable("passId") Long passId, @PathVariable("loanId") Long loanId) {
        passService.reportLostPass(passId, loanId);
    }

    @PutMapping("/foundPass/{id}")
    public void foundPass(@PathVariable("id") Long passId) {
        passService.foundPass(passId);
    }

}
