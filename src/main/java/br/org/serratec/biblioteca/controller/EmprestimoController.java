package br.org.serratec.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.biblioteca.entity.Emprestimo;
import br.org.serratec.biblioteca.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    @Autowired
    EmprestimoService emprestimoService;

    @GetMapping
    public ResponseEntity<List<Emprestimo>> getAllEmprestimos(){
        return new ResponseEntity<>(emprestimoService.getAllEmprestimos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> getEmprestimoById(@PathVariable int id){
        Emprestimo emprestimo = emprestimoService.getEmprestimoById(id);
        if(emprestimo == null){
            return new ResponseEntity<>(emprestimo, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(emprestimo, HttpStatus.OK);
        }
        //return new ResponseEntity<>(emprestimoService.getEmprestimoById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Emprestimo> saveEmprestimo(@RequestBody Emprestimo emprestimo){
        return new ResponseEntity<>(emprestimoService.saveEmprestimo(emprestimo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> updateEmprestimo(@RequestBody Emprestimo emprestimo, @PathVariable int id) {
        return new ResponseEntity<>(emprestimoService.updateEmprestimo(emprestimo, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Emprestimo> deleteEmprestimo(@PathVariable int id){
        Emprestimo emprestimo = emprestimoService.getEmprestimoById(id);
        if(emprestimo == null){
            return new ResponseEntity<>(emprestimo, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(emprestimoService.deleteEmprestimo(id), HttpStatus.OK);
        }
    }
}