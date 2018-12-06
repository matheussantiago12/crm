package tabelas;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Fornecedor;
import model.Pessoa;
import repository.FornecedorRep;
import repository.PessoaRep;

public class FornecedoresTabela extends AbstractTableModel {

    private FornecedorRep frep = new FornecedorRep();
    private PessoaRep prep = new PessoaRep();
    private List<Fornecedor> fornecedores = frep.listar();
    private List<Pessoa> pessoas = prep.listar();

    @Override
    public int getRowCount() {
        return fornecedores.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public Fornecedor get(int row) {
        return fornecedores.get(row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return fornecedores.get(rowIndex).getIdFornecedor();
            case 1:
                return fornecedores.get(rowIndex).getCnpjFornecedor();
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
                return "Id";
            case 1:
                return "CNPJ";
            case 2:
                return "Nome";
            case 3:
                return "Email";
            case 4:
                return "Tel";    
        }
        return null;
    }

}
