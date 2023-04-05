package br.com.alura.linguagens.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import java.util.List;

@RestController
public class LinguagemController {

    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping("/teste")
    public List<Linguagem> obterLinguagens(){
        List<Linguagem> linguagens = repositorio.findAll();
        return linguagens;
    }

    @PostMapping("/teste")
    public Linguagem cadastraLinguagem(@RequestBody Linguagem linguagem){
        Linguagem linguagemSalva =  repositorio.save(linguagem);
        return linguagemSalva;

    }

    @GetMapping("/teste/{id}")
    public Linguagem obterLinguagensPorId (@PathVariable String id) {
        return repositorio.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/teste/{id}")
    public Linguagem atualizarLinguagem (@PathVariable String id, @RequestBody Linguagem linguagem){
       if(!repositorio.existsById(id)){
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND);
       }
        linguagem.setId(id);
        Linguagem linguagemSalva = repositorio.save(linguagem);
        return linguagemSalva;
    }

    @DeleteMapping("/teste/{id}")
    public void excluirLinguagem(@PathVariable String id) {
        repositorio.deleteById(id);

    }

    // @PathVariable informamos que o ID vai chegar como uma variavel 
}
