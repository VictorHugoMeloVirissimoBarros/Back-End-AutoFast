package br.gov.sp.fatec.autofast.repository;

import br.gov.sp.fatec.autofast.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    
    List<Mensagem> findByOrcamentoId(Long orcamentoId);

    List<Mensagem> findByRemetenteId(Long remetenteId);

    List<Mensagem> findByDestinatarioId(Long destinatarioId);
}