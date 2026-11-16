package br.gov.sp.fatec.autofast.service;

import br.gov.sp.fatec.autofast.model.Mecanico;
import br.gov.sp.fatec.autofast.repository.MecanicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class MecanicoService {
    private final MecanicoRepository repository;
    public MecanicoService(MecanicoRepository repository) { this.repository = repository; }
    public List<Mecanico> listar() { return repository.findAll(); }
    public Mecanico buscarPorId(Long id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mecânico não encontrado")); }
    public Mecanico buscarPorEmail(String email) { return repository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mecânico não encontrado")); }
    public List<Mecanico> buscarPorNome(String nome) { return repository.findByNomeContainingIgnoreCase(nome); }
    public List<Mecanico> buscarPorNivelAcesso(String nivel) { return repository.findByNivelAcesso(nivel); }
    public Mecanico salvar(Mecanico mecanico) { mecanico.setId(null); return repository.save(mecanico); }
    public Mecanico atualizar(Long id, Mecanico dados) { Mecanico atual = buscarPorId(id); atual.setNome(dados.getNome()); atual.setEmail(dados.getEmail()); if (dados.getSenha()!=null && !dados.getSenha().isBlank()) atual.setSenha(dados.getSenha()); atual.setTelefone(dados.getTelefone()); atual.setNivelAcesso(dados.getNivelAcesso()); return repository.save(atual); }
    public void excluir(Long id) { if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mecânico não encontrado"); repository.deleteById(id); }
}
