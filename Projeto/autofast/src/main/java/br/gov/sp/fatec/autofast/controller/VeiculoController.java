package br.gov.sp.fatec.autofast.controller;

import br.gov.sp.fatec.autofast.model.Veiculo; 
import br.gov.sp.fatec.autofast.service.VeiculoService; 
import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.*; 
import java.util.List;

@RestController @RequestMapping("/api/veiculos")
public class VeiculoController { private final VeiculoService service; public VeiculoController(VeiculoService service){this.service=service;}
@GetMapping public List<Veiculo> listar(){return service.listar();} @GetMapping("/{id}") public Veiculo buscar(@PathVariable Long id){return service.buscarPorId(id);} 
@GetMapping("/cliente/{id}") public List<Veiculo> porCliente(@PathVariable Long id){return service.buscarPorCliente(id);} 
@GetMapping("/marca") public List<Veiculo> porMarca(@RequestParam String nome){return service.buscarPorMarca(nome);} 
@GetMapping("/modelo") public List<Veiculo> porModelo(@RequestParam String nome){return service.buscarPorModelo(nome);} 
@PostMapping @ResponseStatus(HttpStatus.CREATED) public Veiculo criar(@RequestBody Veiculo v){return service.salvar(v);} 
@PutMapping("/{id}") public Veiculo atualizar(@PathVariable Long id,@RequestBody Veiculo v){return service.atualizar(id,v);} 
@DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void excluir(@PathVariable Long id){service.excluir(id);} }
