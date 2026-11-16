package br.gov.sp.fatec.autofast.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "mecanico")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Mecanico extends Usuario {

    @Column(name = "nivel_acesso", nullable = false)
    private String nivelAcesso;

    @JsonIgnore
    @OneToMany(mappedBy = "mecanico", cascade = CascadeType.ALL)
    private List<Orcamento> orcamentos;

    public Mecanico() {
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }
}
