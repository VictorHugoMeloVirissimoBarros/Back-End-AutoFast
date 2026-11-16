package br.gov.sp.fatec.autofast.service;

import br.gov.sp.fatec.autofast.model.Mensagem;
import br.gov.sp.fatec.autofast.repository.MensagemRepository;
import br.gov.sp.fatec.autofast.repository.OrcamentoRepository;
import br.gov.sp.fatec.autofast.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MensagemService {
    private final MensagemRepository repository; private final OrcamentoRepository orcamentos; private final UsuarioRepository usuarios;
    public MensagemService(MensagemRepository repository, OrcamentoRepository orcamentos, UsuarioRepository usuarios) { this.repository=repository; this.orcamentos=orcamentos; this.usuarios=usuarios; }
    public List<Mensagem> listar() { return repository.findAll(); }
    public Mensagem buscarPorId(Long id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Mensagem não encontrada")); }
    public List<Mensagem> buscarPorOrcamento(Long id) { return repository.findByOrcamentoId(id); }
    public List<Mensagem> buscarPorRemetente(Long id) { return repository.findByRemetenteId(id); }
    public List<Mensagem> buscarPorDestinatario(Long id) { return repository.findByDestinatarioId(id); }
    @Transactional public Mensagem salvar(Mensagem m) { m.setId(null); if(m.getOrcamento()==null || m.getOrcamento().getId()==null || m.getRemetente()==null || m.getRemetente().getId()==null || m.getDestinatario()==null || m.getDestinatario().getId()==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Informe os ids do orçamento, remetente e destinatário"); m.setOrcamento(orcamentos.findById(m.getOrcamento().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Orçamento não encontrado"))); m.setRemetente(usuarios.findById(m.getRemetente().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Remetente não encontrado"))); m.setDestinatario(usuarios.findById(m.getDestinatario().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Destinatário não encontrado"))); if(m.getDataHoraEnvio()==null) m.setDataHoraEnvio(LocalDateTime.now()); return repository.save(m); }
    @Transactional public Mensagem atualizarConteudo(Long id, Mensagem dados) { Mensagem atual=buscarPorId(id); if(dados.getConteudo()==null || dados.getConteudo().isBlank()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"O conteúdo da mensagem é obrigatório"); atual.setConteudo(dados.getConteudo()); return repository.save(atual); }
    public void excluir(Long id) { if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Mensagem não encontrada"); repository.deleteById(id); }
}
