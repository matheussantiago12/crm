package tabelas;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Cliente;
import model.Produto;
import model.Venda;
import repository.VendaRep;

public class VendasTabela extends AbstractTableModel {
    
    private VendaRep vrep = new VendaRep();
    private List<Produto> produtos = vrep.listarProduto();
    private List<Cliente> clientes = vrep.listarCliente();
    private List<Venda> vendas = vrep.listar();

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    public Produto get(int row) {
        return produtos.get(row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return vendas.get(rowIndex).getId();
            case 1:
                return produtos.get(rowIndex).getNomeProduto();
            case 2:
                return clientes.get(rowIndex).getCpfCliente();    
            case 3:
                return vendas.get(rowIndex).getValorVenda();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Produto";
            case 2:
                return "Cliente";    
            case 3:
                return "Valor total";
        }
        return null;
    }

}
