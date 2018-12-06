package model;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Visita")
@NamedQueries({
    @NamedQuery(name = "Visita.findAll", query = "SELECT v FROM Visita v")})
public class Visita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visita")
    private int IdVisita;
    @Column(name = "data_visita")
    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf = 
    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dataVisita = sdf.format(dt);
    @Column(name = "concluido_visita")
    private boolean concluidoVisita;
    @Column(name = "id_funcionario")
    private int idFuncionario;
    @Column(name = "id_cliente")
    private int idCliente;

    public int getIdVisita() {
        return IdVisita;
    }

    public void setIdVisita(int IdVisita) {
        this.IdVisita = IdVisita;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(String dataVisita) {
        this.dataVisita = dataVisita;
    }

    public boolean isConcluidoVisita() {
        return concluidoVisita;
    }

    public void setConcluidoVisita(boolean concluidoVisita) {
        this.concluidoVisita = concluidoVisita;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    
}
