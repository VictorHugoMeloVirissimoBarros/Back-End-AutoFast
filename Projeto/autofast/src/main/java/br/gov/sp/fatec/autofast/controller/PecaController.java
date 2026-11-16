package br.gov.sp.fatec.autofast.controller;

import br.gov.sp.fatec.autofast.model.Peca; 
import br.gov.sp.fatec.autofast.service.PecaService; 
import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.*; 
import java.util.List;

@RestController @RequestMapping("/api/pecas")
public class PecaController { private final PecaService service; public PecaController(PecaService service){this.service=service;}
@GetMapping public List<Peca> listar(){return service.listar();} 
@GetMapping("/{id}") public Peca buscar(@PathVariable Long id){return service.buscarPorId(id);} 
@GetMapping("/buscar") public List<Peca> porDescricao(@RequestParam String descricao){return service.buscarPorDescricao(descricao);} 
@PostMapping @ResponseStatus(HttpStatus.CREATED) public Peca criar(@RequestBody Peca p){return service.salvar(p);} 
@PutMapping("/{id}") public Peca atualizar(@PathVariable Long id,@RequestBody Peca p){return service.atualizar(id,p);} 
@DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void excluir(@PathVariable Long id){service.excluir(id);} }
