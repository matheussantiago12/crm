package tabelas;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Pessoa;
import repository.PessoaRep;

public class PessoaTabela extends AbstractTableModel {

    private PessoaRep prep = new PessoaRep();
    private List<Pessoa> pessoas = prep.listar();

    @Override
    public int getRowCount() {
        return pessoas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    public Pessoa get(int row) {
        return pessoas.get(row);

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return pessoas.get(rowIndex).getIdPessoa();
            case 1:
                return pessoas.get(rowIndex).getNomePessoa();
            case 2:
                return pessoas.get(rowIndex).getEmailPessoa();
            case 3:
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
                return "Nome";
            case 2:
                return "Email";
            case 3:
                return "Telefone";
        }
        return null;
    }

}
