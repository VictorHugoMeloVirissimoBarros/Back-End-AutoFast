package br.gov.sp.fatec.autofast.controller;

import br.gov.sp.fatec.autofast.model.Mensagem; 
import br.gov.sp.fatec.autofast.service.MensagemService; 
import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.*; 
import java.util.List;

@RestController @RequestMapping("/api/mensagens")
public class MensagemController { private final MensagemService service; public MensagemController(MensagemService service){this.service=service;}
@GetMapping public List<Mensagem> listar(){return service.listar();} 
@GetMapping("/{id}") public Mensagem buscar(@PathVariable Long id){return service.buscarPorId(id);} 
@GetMapping("/orcamento/{id}") public List<Mensagem> porOrcamento(@PathVariable Long id){return service.buscarPorOrcamento(id);} 
@GetMapping("/remetente/{id}") public List<Mensagem> porRemetente(@PathVariable Long id){return service.buscarPorRemetente(id);} 
@GetMapping("/destinatario/{id}") public List<Mensagem> porDestinatario(@PathVariable Long id){return service.buscarPorDestinatario(id);} 
@PostMapping @ResponseStatus(HttpStatus.CREATED) public Mensagem criar(@RequestBody Mensagem m){return service.salvar(m);} 
@PutMapping("/{id}") public Mensagem atualizar(@PathVariable Long id,@RequestBody Mensagem m){return service.atualizarConteudo(id,m);} 
@DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void excluir(@PathVariable Long id){service.excluir(id);} }
