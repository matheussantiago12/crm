package tabelas;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Cliente;
import model.Funcionario;
import model.Visita;
import repository.ClienteRep;
import repository.FuncionarioRep;
import repository.VisitaRep;

public class VisitaTabela extends AbstractTableModel {

    private VisitaRep vrep = new VisitaRep();
    private List<Visita> visitas = vrep.listar();
    private List<Funcionario> funcionarios = vrep.listarFuncionario();
    private List<Cliente> clientes = vrep.listarCliente();

    @Override
    public int getRowCount() {
        return visitas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    public Visita get(int row) {
        return visitas.get(row);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return visitas.get(rowIndex).getId();
            case 1:
                return funcionarios.get(rowIndex).getLoginFuncionario();
            case 2:
                return clientes.get(rowIndex).getCpfCliente();
            case 3:
                return visitas.get(rowIndex).getDataVisita();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Funcionario";
            case 2:
                return "Cliente";
            case 3:
                return "Data";
        }
        return null;
    }
}
