package model;


import java.io.Serializable;
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
@Table(name = "Fornecedor")
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f")})
public class Fornecedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor")
    private int IdFornecedor;
    @Column(name = "cnpj_fornecedor")
    private String cnpjFornecedor;
    @Column(name = "nome_fornecedor")
    private String nomeFornecedor;
    @Column(name = "email_fornecedor")
    private String emailFornecedor;
    @Column(name = "tel_fornecedor")
    private String telFornecedor;

    public int getIdFornecedor() {
        return IdFornecedor;
    }

    public void setIdFornecedor(int IdFornecedor) {
        this.IdFornecedor = IdFornecedor;
    }

    public String getCnpjFornecedor() {
        return cnpjFornecedor;
    }

    public void setCnpjFornecedor(String cnpjFornecedor) {
        this.cnpjFornecedor = cnpjFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getEmailFornecedor() {
        return emailFornecedor;
    }

    public void setEmailFornecedor(String emailFornecedor) {
        this.emailFornecedor = emailFornecedor;
    }

    public String getTelFornecedor() {
        return telFornecedor;
    }

    public void setTelFornecedor(String telFornecedor) {
        this.telFornecedor = telFornecedor;
    }
}
