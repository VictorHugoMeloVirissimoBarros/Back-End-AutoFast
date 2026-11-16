package br.gov.sp.fatec.autofast.controller;

import br.gov.sp.fatec.autofast.model.Mecanico; 
import br.gov.sp.fatec.autofast.service.MecanicoService; 
import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.*; 
import java.util.List;

@RestController @RequestMapping("/api/mecanicos")
public class MecanicoController { private final MecanicoService service; public MecanicoController(MecanicoService service){this.service=service;}
@GetMapping public List<Mecanico> listar(){return service.listar();} 
@GetMapping("/{id}") public Mecanico buscar(@PathVariable Long id){return service.buscarPorId(id);} 
@GetMapping("/email/{email}") public Mecanico porEmail(@PathVariable String email){return service.buscarPorEmail(email);} 
@GetMapping("/buscar") public List<Mecanico> porNome(@RequestParam String nome){return service.buscarPorNome(nome);} 
@GetMapping("/nivel/{nivel}") public List<Mecanico> porNivel(@PathVariable String nivel){return service.buscarPorNivelAcesso(nivel);} 
@PostMapping @ResponseStatus(HttpStatus.CREATED) public Mecanico criar(@RequestBody Mecanico m){return service.salvar(m);} 
@PutMapping("/{id}") public Mecanico atualizar(@PathVariable Long id,@RequestBody Mecanico m){return service.atualizar(id,m);} 
@DeleteMapping("/{id}") 
@ResponseStatus(HttpStatus.NO_CONTENT) public void excluir(@PathVariable Long id){service.excluir(id);} }
