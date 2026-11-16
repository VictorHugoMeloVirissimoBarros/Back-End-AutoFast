package br.gov.sp.fatec.autofast.service;

import br.gov.sp.fatec.autofast.model.Veiculo;
import br.gov.sp.fatec.autofast.repository.VeiculoRepository;
import br.gov.sp.fatec.autofast.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class VeiculoService {
    private final VeiculoRepository repository; private final ClienteRepository clienteRepository;
    public VeiculoService(VeiculoRepository repository, ClienteRepository clienteRepository) { this.repository=repository; this.clienteRepository=clienteRepository; }
    public List<Veiculo> listar() { return repository.findAll(); }
    public Veiculo buscarPorId(Long id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado")); }
    public List<Veiculo> buscarPorCliente(Long id) { return repository.findByClienteId(id); }
    public List<Veiculo> buscarPorMarca(String marca) { return repository.findByMarcaContainingIgnoreCase(marca); }
    public List<Veiculo> buscarPorModelo(String modelo) { return repository.findByModeloContainingIgnoreCase(modelo); }
    public Veiculo salvar(Veiculo v) { v.setId(null); if(v.getCliente()==null || v.getCliente().getId()==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Informe o id do cliente"); v.setCliente(clienteRepository.findById(v.getCliente().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"))); return repository.save(v); }
    public Veiculo atualizar(Long id, Veiculo dados) { Veiculo atual=buscarPorId(id); atual.setMarca(dados.getMarca()); atual.setModelo(dados.getModelo()); atual.setAno(dados.getAno()); if(dados.getCliente()!=null && dados.getCliente().getId()!=null) atual.setCliente(clienteRepository.findById(dados.getCliente().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"))); return repository.save(atual); }
    public void excluir(Long id) { if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Veículo não encontrado"); repository.deleteById(id); }
}
