package br.gov.sp.fatec.autofast.service;

import br.gov.sp.fatec.autofast.model.*;
import br.gov.sp.fatec.autofast.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrcamentoService {
    private final OrcamentoRepository repository; private final ClienteRepository clientes; private final MecanicoRepository mecanicos; private final VeiculoRepository veiculos; private final PecaRepository pecas;
    public OrcamentoService(OrcamentoRepository repository, ClienteRepository clientes, MecanicoRepository mecanicos, VeiculoRepository veiculos, PecaRepository pecas) { this.repository=repository; this.clientes=clientes; this.mecanicos=mecanicos; this.veiculos=veiculos; this.pecas=pecas; }
    public List<Orcamento> listar() { return repository.findAll(); }
    public Orcamento buscarPorId(Long id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Orçamento não encontrado")); }
    public List<Orcamento> buscarPorStatus(StatusOrcamento status) { return repository.findByStatus(status); }
    public List<Orcamento> buscarPorCliente(Long id) { return repository.findByClienteId(id); }
    public List<Orcamento> buscarPorMecanico(Long id) { return repository.findByMecanicoId(id); }
    public List<Orcamento> buscarPorVeiculo(Long id) { return repository.findByVeiculoId(id); }
    @Transactional public Orcamento salvar(Orcamento o) { o.setId(null); if(o.getDataSolicitacao()==null) o.setDataSolicitacao(LocalDateTime.now()); if(o.getStatus()==null) o.setStatus(StatusOrcamento.PENDENTE); o.setCliente(clientes.findById(id(o.getCliente(),"cliente")).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"))); o.setVeiculo(veiculos.findById(id(o.getVeiculo(),"veículo")).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Veículo não encontrado"))); o.setPeca(pecas.findById(id(o.getPeca(),"peça")).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Peça não encontrada"))); if(o.getMecanico()!=null && o.getMecanico().getId()!=null) o.setMecanico(mecanicos.findById(o.getMecanico().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Mecânico não encontrado"))); return repository.save(o); }
    @Transactional public Orcamento atualizar(Long id, Orcamento dados) { Orcamento atual=buscarPorId(id); if(dados.getStatus()!=null) atual.setStatus(dados.getStatus()); atual.setValorPecas(dados.getValorPecas()); atual.setValorMaoDeObra(dados.getValorMaoDeObra()); atual.setPrazoEntrega(dados.getPrazoEntrega()); if(dados.getValorTotal()!=null) atual.setValorTotal(dados.getValorTotal()); if(dados.getMecanico()!=null && dados.getMecanico().getId()!=null) atual.setMecanico(mecanicos.findById(dados.getMecanico().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Mecânico não encontrado"))); return repository.save(atual); }
    public void excluir(Long id) { if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Orçamento não encontrado"); repository.deleteById(id); }
    private Long id(Object entidade, String nome) { if(entidade instanceof Cliente c && c.getId()!=null) return c.getId(); if(entidade instanceof Veiculo v && v.getId()!=null) return v.getId(); if(entidade instanceof Peca p && p.getId()!=null) return p.getId(); throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Informe o id de " + nome); }
}
