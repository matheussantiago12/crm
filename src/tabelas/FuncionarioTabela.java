package tabelas;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Funcionario;
import repository.FuncionarioRep;
public class FuncionarioTabela extends AbstractTableModel {

    private FuncionarioRep frep = new FuncionarioRep();
    private List<Funcionario> funcionarios = frep.listar();
    
    @Override
    public int getRowCount() {
        return funcionarios.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }
    
    public Funcionario getFuncionario(int row) {
        return funcionarios.get(row);

    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: 
                return funcionarios.get(rowIndex).getId();
            case 1: 
                return funcionarios.get(rowIndex).getLoginFuncionario();
            case 2: 
                return funcionarios.get(rowIndex).getSenhaFuncionario();   
            case 3:
                return funcionarios.get(rowIndex).getNomeFuncionario(); 
            case 4:
                return funcionarios.get(rowIndex).getEmailFuncionario(); 
            case 5:
                return funcionarios.get(rowIndex).getTelFuncionario();     
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Id";   
            case 1:
                return "Login";
            case 2:
                return "Senha";
            case 3:
                return "Nome";
            case 4:
                return "Email";
            case 5:
                return "Telefone";
        }
        return null;
    }
    
    
    
}
