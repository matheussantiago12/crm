/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import javax.persistence.Transient;
/**
 *
 * @author Aluno
 */
@Entity
@Table(name = "cliente")
@NamedQueries({
@NamedQuery(name = "Cliente.findAll", query = "SELECT cl FROM Cliente cl")})
public class Cliente implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idCliente;
    @Column(name = "cpf_cliente")
    private String cpfCliente;
    @Column (name = "nome_cliente")
    private String nomeCliente;
    @Column(name = "email_cliente")
    private String emailCliente;
    @Column(name = "tel_cliente")
    private String telCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        int oldIdCliente = this.idCliente;
        this.idCliente = idCliente;
        changeSupport.firePropertyChange("idCliente", oldIdCliente, idCliente);
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        String oldCpfCliente = this.cpfCliente;
        this.cpfCliente = cpfCliente;
        changeSupport.firePropertyChange("cpfCliente", oldCpfCliente, cpfCliente);
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        String oldNomeCliente = this.nomeCliente;
        this.nomeCliente = nomeCliente;
        changeSupport.firePropertyChange("nomeCliente", oldNomeCliente, nomeCliente);
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        String oldEmailCliente = this.emailCliente;
        this.emailCliente = emailCliente;
        changeSupport.firePropertyChange("emailCliente", oldEmailCliente, emailCliente);
    }

    public String getTelCliente() {
        return telCliente;
    }

    public void setTelCliente(String telCliente) {
        String oldTelCliente = this.telCliente;
        this.telCliente = telCliente;
        changeSupport.firePropertyChange("telCliente", oldTelCliente, telCliente);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public String toString() {
        return nomeCliente;
    }
    
    
}
