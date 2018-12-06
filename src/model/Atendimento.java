package model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "atendimentos")
public class Atendimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_atendimentos")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;
    
    @ManyToOne
    @JoinColumn(name = "id_clientes")
    private Cliente cliente;
    
    @Column(name = "data_atendimento")
    @Temporal(TemporalType.DATE)
    private Date dataAtendimento;
    
    @Column(name = "titulo_atendimento")
    private String tituloAtendimento;
    
    @Column(name = "detalhes_atendimento")
    private String detalhesAtendimento;
    
    @Column(name = "concluido")
    private boolean concluido;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getTituloAtendimento() {
        return tituloAtendimento;
    }

    public void setTituloAtendimento(String tituloAtendimento) {
        this.tituloAtendimento = tituloAtendimento;
    }

    public String getDetalhesAtendimento() {
        return detalhesAtendimento;
    }

    public void setDetalhesAtendimento(String detalhesAtendimento) {
        this.detalhesAtendimento = detalhesAtendimento;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Atendimento other = (Atendimento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Atendimento{" + "id=" + id + ", funcionario=" + funcionario + ", cliente=" + cliente + ", dataAtendimento=" + dataAtendimento + ", tituloAtendimento=" + tituloAtendimento + ", detalhesAtendimento=" + detalhesAtendimento + ", concluido=" + concluido + '}';
    }
}
