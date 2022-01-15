package com.leo.rest;

import com.leo.CGood;
import com.leo.COrder;
import com.leo.CUser;
import com.leo.repositories.IRepositoryGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController()
@RequestMapping("/goods")
public class CControllerGoods {
    @Autowired
    IRepositoryGoods repositoryGoods;
    CControllerGoods(){}
    @GetMapping("/findall")
    public List<CGood> findall(){
        return repositoryGoods.findAll();
    }
    @GetMapping("/findbyid")
    public CGood findbyid(@RequestParam UUID id){ return repositoryGoods.findById(id);}
    @GetMapping("/findbyname")
    public List<CGood> findbyname(@RequestParam String name){ return repositoryGoods.findByName(name);}
    @GetMapping("/findbycategory")
    public List<CGood> findbycategory(@RequestParam String category){ return repositoryGoods.findByCategory(category);}
    @GetMapping("/findbyprice")
    public List<CGood> findbyprice(@RequestParam double price){ return repositoryGoods.findByPrice(price);}
    @GetMapping("/report")
    public List<CGood> report(){return repositoryGoods.report();}
    @PostMapping("/newgood")
    public void newgood(@RequestBody CGood good){
        repositoryGoods.save(good);
    }
    @DeleteMapping("/deletegood")
    public void deletegood(@RequestBody CGood good){
        repositoryGoods.delete(good);
    }
    @GetMapping("/updategood")
    public void updategood(@RequestParam UUID id, @RequestParam String name, @RequestParam double price, @RequestParam String category) {
        repositoryGoods.updateGood(id,name,price,category);
    }
}
