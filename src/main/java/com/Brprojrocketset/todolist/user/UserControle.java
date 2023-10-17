package com.Brprojrocketset.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/user")
public class UserControle {
    /**
     * String (Texto)
     * Integer ou int (Numero inteiro) 1,2,3 etc..
     * double (Numero quebrado) 12,8765 etc..
     * float (Numero quebrado) 12,12 etc..
     * char (A C)
     * Date (Data)
     * void (Quando não tem retorno do metodo, somente a logica.)
     * 
     * Body (Corpo da riquisição)
     */

    /**
     * Get - Buscar info
     * Post - Adicionar dado/ informaçao
     * Put - Alterar o dado/ info com mais dados Ex: nome, email, rg etc..
     * Delet - Remover dado
     * Patch - Altera somente uma parte da info Ex:nome
     */
    // metodo criado. Passando no corpo o tipo dele *Rbody

    @Autowired
    private IUserRepositorio IuserRepositorio; // chamando a interface

    @PostMapping("/adicionar")
    //usando ResponseEntity não precisa criar objeto para retornar algo. Ele é um obj de retorno
    public ResponseEntity create(@RequestBody UserEntites userEntites) {

        var user = this.IuserRepositorio.findByUsername(userEntites.getUsername());
        if (user != null) {
            // mensagem de erro e tatus code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe");
        }

       var senhacrypto = BCrypt.withDefaults().hashToString(12, userEntites.getSenha().toCharArray());

       userEntites.setSenha(senhacrypto);

        var userCreated = this.IuserRepositorio.save(userEntites);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}
