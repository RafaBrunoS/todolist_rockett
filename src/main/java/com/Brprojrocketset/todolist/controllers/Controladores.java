package com.Brprojrocketset.todolist.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController 
@RequestMapping("/PrimeiraRota")
//Criação de rota-- http://localhost:8080/ ---
public class Controladores {
    
/**
 * Get - Buscar info
 * Post - Adicionar dado/ informaçao
 * Put - Alterar o dado/ info com mais dados Ex: nome, email, rg etc..
 * Delet - Remover dado
 * Patch - Altera somente uma parte da info Ex:nome
 */
 
@GetMapping("/metodo1")
    public String primeiramensagem(){
        return "Funcinou";
    }
}
