package br.com.vendas_api.controller;

import br.com.vendas_api.dto.LivroDto;
import br.com.vendas_api.model.Livro;
import br.com.vendas_api.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroDto>> getAllLivros(){
        List<LivroDto> livros = livroService.getAllLivros();

        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<LivroDto> getLivroById(@PathVariable Long id){
        LivroDto livro = livroService.getLivroById(id);

        return new ResponseEntity<>(livro, HttpStatus.OK);
    }

    @GetMapping("procurar-nome")
    public ResponseEntity<List<LivroDto>> getLivroByNomeFiltro(@RequestParam String nome){
        List<LivroDto> retorno = livroService.getLivrosByNomeContaining(nome);

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @GetMapping("procurar-nome&autor")
    public ResponseEntity<List<LivroDto>> getLivroByNomeAutor(@RequestParam String nome, @RequestParam String autor){
        List<LivroDto> retorno = livroService.getLivrosByNomeEAutorContaining(nome, autor);

        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @GetMapping("{socioId}/alugar/{livroId}")
    public ResponseEntity<String> alugarLivro(@PathVariable Long socioId, @PathVariable Long livroId){
        String response = livroService.alugarLivro(socioId, livroId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{socioId}/devolver/{livroId}")
    public ResponseEntity<String> devolverLivro(@PathVariable Long socioId, @PathVariable Long livroId){
        String response = livroService.devolverLivro(socioId, livroId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postLivro(@Valid @RequestBody LivroDto livro){
        String response = livroService.saveLivro(livro);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> putLivro(@PathVariable Long id, @Valid @RequestBody LivroDto livro){
        String response = livroService.updateLivro(id, livro);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLivro(@PathVariable Long id){
        String response = livroService.deleteLivro(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
