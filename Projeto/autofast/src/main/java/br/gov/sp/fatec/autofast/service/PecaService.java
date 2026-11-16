package br.gov.sp.fatec.autofast.service;

import br.gov.sp.fatec.autofast.model.Peca;
import br.gov.sp.fatec.autofast.repository.PecaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class PecaService {
    private final PecaRepository repository;
    public PecaService(PecaRepository repository) { this.repository=repository; }
    public List<Peca> listar() { return repository.findAll(); }
    public Peca buscarPorId(Long id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Peça não encontrada")); }
    public List<Peca> buscarPorDescricao(String descricao) { return repository.findByDescricaoProblemaContainingIgnoreCase(descricao); }
    public Peca salvar(Peca peca) { peca.setId(null); return repository.save(peca); }
    public Peca atualizar(Long id, Peca dados) { Peca atual=buscarPorId(id); atual.setDescricaoProblema(dados.getDescricaoProblema()); atual.setFotoUrl(dados.getFotoUrl()); return repository.save(atual); }
    public void excluir(Long id) { if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Peça não encontrada"); repository.deleteById(id); }
}
