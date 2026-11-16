package br.gov.sp.fatec.autofast.repository;

import br.gov.sp.fatec.autofast.model.Orcamento;
import br.gov.sp.fatec.autofast.model.StatusOrcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

    List<Orcamento> findByStatus(StatusOrcamento status);

    List<Orcamento> findByClienteId(Long clienteId);

    List<Orcamento> findByMecanicoId(Long mecanicoId);

    List<Orcamento> findByVeiculoId(Long veiculoId);
}