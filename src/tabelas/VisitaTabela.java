package tabelas;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Visita;
import repository.VisitaRep;

public class VisitaTabela extends AbstractTableModel {

    private VisitaRep vrep = new VisitaRep();
    private List<Visita> visitas = vrep.listar();

    @Override
    public int getRowCount() {
        return visitas.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
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
                return visitas.get(rowIndex).getFuncionario().getIdFuncionario();
            case 2:
                return visitas.get(rowIndex).getCliente().getId();
            case 3:
                return visitas.get(rowIndex).getDataVisita();
            case 4:
                return visitas.get(rowIndex).getDetalhes();
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
            case 4:
                return "Detalhes";
        }
        return null;
    }
}
