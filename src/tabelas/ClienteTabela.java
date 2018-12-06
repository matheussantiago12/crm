package tabelas;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Cliente;
import model.Pessoa;
import repository.ClienteRep;
import repository.PessoaRep;

public class ClienteTabela extends AbstractTableModel {

    private ClienteRep crep = new ClienteRep();
    private List<Cliente> clientes = crep.listar();
    private List<Pessoa> pessoas = crep.listarPessoaCliente();

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public Cliente get(int row) {
        return clientes.get(row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return clientes.get(rowIndex).getId();
            case 1:
                return clientes.get(rowIndex).getCpfCliente();
            case 2:
                return pessoas.get(rowIndex).getNomePessoa();
            case 3:
                return pessoas.get(rowIndex).getEmailPessoa();
            case 4:
                return pessoas.get(rowIndex).getTelPessoa();

        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id Cliente";
            case 1:
                return "CPF";
            case 2:
                return "Nome";
            case 3:
                return "Email";
            case 4:
                return "Telefone";
        }
        return null;
    }

}
