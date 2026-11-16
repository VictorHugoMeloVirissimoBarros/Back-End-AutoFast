package br.gov.sp.fatec.autofast.controller;

import br.gov.sp.fatec.autofast.model.Orcamento; 
import br.gov.sp.fatec.autofast.model.StatusOrcamento; 
import br.gov.sp.fatec.autofast.service.OrcamentoService; 
import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.*; 
import java.util.List;

@RestController @RequestMapping("/api/orcamentos")
public class OrcamentoController { private final OrcamentoService service; public OrcamentoController(OrcamentoService service){this.service=service;}
@GetMapping public List<Orcamento> listar(){return service.listar();} @GetMapping("/{id}") public Orcamento buscar(@PathVariable Long id){return service.buscarPorId(id);} 
@GetMapping("/status/{status}") public List<Orcamento> porStatus(@PathVariable StatusOrcamento status){return service.buscarPorStatus(status);} 
@GetMapping("/cliente/{id}") public List<Orcamento> porCliente(@PathVariable Long id){return service.buscarPorCliente(id);} @GetMapping("/mecanico/{id}") public List<Orcamento> porMecanico(@PathVariable Long id){return service.buscarPorMecanico(id);} 
@GetMapping("/veiculo/{id}") public List<Orcamento> porVeiculo(@PathVariable Long id){return service.buscarPorVeiculo(id);} @PostMapping @ResponseStatus(HttpStatus.CREATED) public Orcamento criar(@RequestBody Orcamento o){return service.salvar(o);} 
@PutMapping("/{id}") public Orcamento atualizar(@PathVariable Long id,@RequestBody Orcamento o){return service.atualizar(id,o);} 
@DeleteMapping("/{id}") 
@ResponseStatus(HttpStatus.NO_CONTENT) public void excluir(@PathVariable Long id){service.excluir(id);} }
