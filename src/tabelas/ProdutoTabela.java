package tabelas;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Fornecedor;
import model.Produto;
import repository.FornecedorRep;
import repository.ProdutoRep;

public class ProdutoTabela extends AbstractTableModel {

    private ProdutoRep prep = new ProdutoRep();
    private List<Produto> produtos = prep.listar();
    private FornecedorRep frep = new FornecedorRep();
    private List<Fornecedor> fornecedores = frep.listar();

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    public Produto get(int row) {
        return produtos.get(row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return produtos.get(rowIndex).getIdProduto();
            case 1:
                return fornecedores.get(rowIndex).getIdFornecedor();    
            case 2:
                return produtos.get(rowIndex).getNomeProduto();
            case 3:
                return produtos.get(rowIndex).getQtdeEstoque();
            case 4:
                return produtos.get(rowIndex).getPrecoCusto();
            case 5:
                return produtos.get(rowIndex).getPrecoVenda();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id ";
            case 1:
                return "Id";    
            case 2:
                return "Nome";
            case 3:
                return "Estoque";
            case 4:
                return "Custo";
            case 5:
                return "Venda";
        }
        return null;
    }

}
