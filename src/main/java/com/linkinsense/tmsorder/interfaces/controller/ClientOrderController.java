package com.linkinsense.tmsorder.interfaces.controller;

import com.linkinsense.tmsorder.application.command.ClientOrderCmdService;
import com.linkinsense.tmsorder.application.command.cmd.CreateClientOrderCommand;
import com.linkinsense.tmsorder.application.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class ClientOrderController {

    @Autowired
    ClientOrderCmdService orderService;

    @PostMapping("/create")
    public Result create(@RequestBody CreateClientOrderCommand cmd){
        return orderService.createOrder(cmd);
    }

    @DeleteMapping("/delete/{orderId}")
    public Result delete(@PathVariable Long orderId){
        return orderService.deleteOrder(orderId);
    }

}
