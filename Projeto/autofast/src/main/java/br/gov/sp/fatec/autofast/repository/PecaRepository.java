package br.gov.sp.fatec.autofast.repository;

import br.gov.sp.fatec.autofast.model.Peca;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PecaRepository extends JpaRepository<Peca, Long> {

    List<Peca> findByDescricaoProblemaContainingIgnoreCase(String descricao);
}