package br.gov.sp.fatec.autofast.controller;

import br.gov.sp.fatec.autofast.model.Cliente; 
import br.gov.sp.fatec.autofast.service.ClienteService; 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController 
@RequestMapping("/api/clientes")
public class ClienteController { 
    private final ClienteService service; public ClienteController(ClienteService service){this.service=service;
    }
@GetMapping 
public List<Cliente> listar(){return service.listar();} @GetMapping("/{id}")
public Cliente buscar(@PathVariable Long id){return service.buscarPorId(id);} 
@GetMapping("/email/{email}") public Cliente porEmail(@PathVariable String email)
{return service.buscarPorEmail(email);} @GetMapping("/buscar") public List<Cliente> porNome(@RequestParam String nome){return service.buscarPorNome(nome);}
@PostMapping @ResponseStatus(HttpStatus.CREATED) public Cliente criar(@RequestBody Cliente c){return service.salvar(c);} 
@PutMapping("/{id}") public Cliente atualizar(@PathVariable Long id,@RequestBody Cliente c){return service.atualizar(id,c);} 
@DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void excluir(@PathVariable Long id){service.excluir(id);} }

