package br.gov.sp.fatec.autofast.repository;

import br.gov.sp.fatec.autofast.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByClienteId(Long clienteId);

    List<Veiculo> findByMarcaContainingIgnoreCase(String marca);

    List<Veiculo> findByModeloContainingIgnoreCase(String modelo);
}