package com.leo.rest;

import com.leo.COrder;
import com.leo.CUser;
import com.leo.repositories.IRepositoryOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@RestController()
@RequestMapping("/orders")
public class CCOntrollerOrders {
    @Autowired
    IRepositoryOrders repositoryOrders;
    public CCOntrollerOrders(){}
    @GetMapping("/findall")
    public List<COrder> findall(){
        return repositoryOrders.findAll();
    }
    @GetMapping("/findbyid")
    public COrder findbyid(@RequestParam UUID id){
        return repositoryOrders.findById(id);
    }
    @GetMapping("/findbygid")
    public List<COrder> findbygid(@RequestParam UUID Gid){
        return repositoryOrders.findByGid(Gid);
    }
    @GetMapping("/findbyuid")
    public List<COrder> findbyuid(@RequestParam UUID Uid){
        return repositoryOrders.findByUid(Uid);
    }
    @GetMapping("/findbydate")
    public List<COrder> findbydate(@RequestParam LocalDate date){
        return repositoryOrders.findByDate(date);
    }
    @PostMapping("/neworder")
    public void neworder(@RequestBody COrder order){
        repositoryOrders.save(order);
    }
    @DeleteMapping("/deleteorder")
    public void deleteorder(@RequestBody COrder order){
        repositoryOrders.delete(order);
    }
    @GetMapping("/updateorder")
    public void updateorder(@RequestParam UUID id, @RequestParam UUID uid, @RequestParam UUID gid, @RequestParam String date) {
        repositoryOrders.updateOrder(id,uid,gid,LocalDate.parse(date));
    }
}
