package com.hyper.hotel.controller;

import com.hyper.hotel.model.Cliente;
import com.hyper.hotel.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }

    @PostMapping
    public Cliente crear(@RequestBody Cliente c) {
        return service.guardar(c);
    }
}
