package com.leo.rest;

import com.leo.CUser;
import com.leo.repositories.IRepositoryUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/users")
public class CControllerUsers {
    @Autowired
    IRepositoryUsers repositoryUsers;

    public CControllerUsers(){
    }
    @GetMapping("/findall")
    public List<CUser> findall(){
        return repositoryUsers.findAll();
    }
    @GetMapping("/findbyid")
    public CUser findbyid(@RequestParam UUID id){
        return repositoryUsers.findById(id);
    }
    @GetMapping("/findbygender")
    public List<CUser> findbygender(@RequestParam boolean gender){
        return repositoryUsers.findByGender(gender);
    }
    @GetMapping("/findbyname")
    public List<CUser> findbyname(@RequestParam String name){
        return repositoryUsers.findByName(name);
    }
    @GetMapping("/findbylogin")
    public List<CUser> findbylogin(@RequestParam String login){
        return repositoryUsers.findByLogin(login);
    }
    @PostMapping("/newuser")
    public void adduser(@RequestBody CUser user){
        repositoryUsers.save(user);
    }
    @GetMapping("/findmale")
    public List<CUser> findmale(){
        return repositoryUsers.findMale();
    }
    @DeleteMapping("/deleteuser")
    public void deleteuser(@RequestBody CUser user){
        repositoryUsers.delete(user);
    }
    @GetMapping("/updateuser")
    public void updateuser(@RequestParam UUID id, @RequestParam String login, @RequestParam String name, @RequestParam boolean gender, @RequestParam String datebirth) {
        repositoryUsers.updateUser(id,login,name,gender, LocalDate.parse(datebirth));
    }
}
