package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Fornecedor;
import util.ConexaoBD;

public class FornecedorRep {

    private static final String INSERT = "insert into fornecedor (cnpj_fornecedor, nome_contato, id_pessoa) values (?, ?, ?);";

    private static final String SELECT = "select id_fornecedor, cnpj_fornecedor, nome_contato from fornecedor";

    private static final String DELETE = "delete from fornecedor where id_fornecedor = ?";

    private static final String UPDATE = "update fornecedor set cnpj_fornecedor = ?, nome_contato = ? where id_fornecedor = ?";

    private Connection connection = ConexaoBD.conectarBanco();
    private PreparedStatement pstm;

    public void salvar(Fornecedor fornecedor) {

        try {

            pstm = connection.prepareStatement(INSERT);
            pstm.setString(1, fornecedor.getCnpjFornecedor());
            pstm.setString(2, fornecedor.getNomeContato());
            pstm.setInt(3, fornecedor.getPessoa().getIdPessoa());
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }

    public void excluir(Fornecedor fornecedor) {
        try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setInt(1, fornecedor.getIdFornecedor());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar excluir: " + ex.getMessage());
        }

    }

    public void alterar(Fornecedor fornecedor) {
        try {
            pstm = connection.prepareStatement(UPDATE);
            pstm.setString(1, fornecedor.getCnpjFornecedor());
            pstm.setString(2, fornecedor.getNomeContato());
            pstm.setInt(3, fornecedor.getIdFornecedor());
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar alterar: " + ex.getMessage());
        }

    }

    public List<Fornecedor> listar() {
        List<Fornecedor> fornecedor = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT);
            res = pstm.executeQuery();

            while (res.next()) {
                Fornecedor f = new Fornecedor();
                f.setIdFornecedor(res.getInt("id_fornecedor"));
                f.setCnpjFornecedor(res.getString("cnpj_fornecedor"));
                f.setNomeContato(res.getString("nome_contato"));
                fornecedor.add(f);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os fornecedores do banco: " + ex.getMessage());
        }
        return fornecedor;
    }
}
