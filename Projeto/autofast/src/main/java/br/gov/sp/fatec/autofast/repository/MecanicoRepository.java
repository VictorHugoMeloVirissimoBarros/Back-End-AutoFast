package br.gov.sp.fatec.autofast.repository;

import br.gov.sp.fatec.autofast.model.Mecanico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MecanicoRepository extends JpaRepository<Mecanico, Long> {

    List<Mecanico> findByNomeContainingIgnoreCase(String nome);

    Optional<Mecanico> findByEmail(String email);

    List<Mecanico> findByNivelAcesso(String nivelAcesso);
}