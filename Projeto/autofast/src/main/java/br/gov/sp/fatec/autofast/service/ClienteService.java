package br.gov.sp.fatec.autofast.service;

import br.gov.sp.fatec.autofast.model.Cliente;
import br.gov.sp.fatec.autofast.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;
    public ClienteService(ClienteRepository repository) { this.repository = repository; }
    public List<Cliente> listar() { return repository.findAll(); }
    public Cliente buscarPorId(Long id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")); }
    public Cliente buscarPorEmail(String email) { return repository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")); }
    public List<Cliente> buscarPorNome(String nome) { return repository.findByNomeContainingIgnoreCase(nome); }
    public Cliente salvar(Cliente cliente) { cliente.setId(null); return repository.save(cliente); }
    public Cliente atualizar(Long id, Cliente dados) { Cliente atual = buscarPorId(id); atual.setNome(dados.getNome()); atual.setEmail(dados.getEmail()); if (dados.getSenha()!=null && !dados.getSenha().isBlank()) atual.setSenha(dados.getSenha()); atual.setTelefone(dados.getTelefone()); return repository.save(atual); }
    public void excluir(Long id) { if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"); repository.deleteById(id); }
}
