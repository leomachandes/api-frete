package br.com.trabalhofinal.trabalhofinal.controller;

import br.com.trabalhofinal.trabalhofinal.controller.dto.tabelafrete.CadastrarTabelaFreteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/tabelas")
@Validated
public class TabelaFreteController {

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody CadastrarTabelaFreteDto dto) {

        String id = null;
        return ResponseEntity.created(URI.create("/tabelas/" + id)).build();
    }

    @PutMapping
    public  ResponseEntity<?> alterar(@Valid @RequestBody CadastrarTabelaFreteDto dto) {

        return  null;

    }


    
    

}
