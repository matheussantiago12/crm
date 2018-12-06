/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combos;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import repository.ClienteRep;
import model.Cliente;
/**
 *
 * @author Aluno
 */
public class IdClientesCombo extends AbstractListModel implements ComboBoxModel {
    
    private ClienteRep crep = new ClienteRep();
    private List<Cliente> clientes = crep.listar();
    String selection = null;
    private Cliente selectedClientes;
    
    @Override
    public int getSize() {
        return clientes.size();
    }

    @Override
    public Object getElementAt(int index) {
        return clientes.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedClientes = (Cliente) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedClientes;
    }
    
}
