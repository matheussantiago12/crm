package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;
import util.ConexaoBD;

public class FuncionarioRep {

    private static final String INSERT = "insert into funcionario (login_funcionario, senha_funcionario, id_pessoa) values (?,?,?);";

    private static final String SELECT = "select id_funcionario, login_funcionario, senha_funcionario from funcionario";

    private static final String DELETE = "delete from funcionario where id_funcionario = ?";

    private static final String LOGAR = "select login_funcionario, senha_funcionario from funcionario where login_funcionario = ? and senha_funcionario = ?";

    private static final String UPDATE = "update funcionario set login_funcionario = ?,  senha_funcionario = ?, id_pessoa = ? where id_funcionario = ?";

    private Connection connection = ConexaoBD.conectarBanco();
    public PreparedStatement pstm;

    public void salvar(Funcionario funcionario) {

        try {
            pstm = connection.prepareStatement(INSERT);
            pstm.setString(1, funcionario.getLoginFuncionario());
            pstm.setString(2, funcionario.getSenhaFuncionario());
            pstm.setInt(3, funcionario.getPessoa().getIdPessoa());
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar salvar: " + ex.getMessage());
        }
    }

    public void alterar(Funcionario funcionario) {

        try {
            pstm = connection.prepareStatement(UPDATE);
            pstm.setString(1, funcionario.getLoginFuncionario());
            pstm.setString(2, funcionario.getSenhaFuncionario());
            pstm.setInt(3, funcionario.getPessoa().getIdPessoa());
            pstm.setInt(4, funcionario.getIdFuncionario());
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar alterar: " + ex.getMessage());
        }
    }

    public void excluir(Funcionario funcionario) {
        try {
            pstm = connection.prepareStatement(DELETE);
            pstm.setInt(1, funcionario.getIdFuncionario());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar excluir: " + ex.getMessage());
        }

    }

    public boolean Logar(String login, String senha) {
        boolean loginValido = false;
        ResultSet res;
        try {
            pstm = connection.prepareStatement(LOGAR);
            pstm.setString(1, login);
            pstm.setString(2, senha);
            res = pstm.executeQuery();

            loginValido = res.next();

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar logar no banco: " + ex.getMessage());
        }
        return loginValido;
    }

    public List<Funcionario> listar() {
        List<Funcionario> funcionario = new ArrayList<>();
        ResultSet res;

        try {
            pstm = connection.prepareStatement(SELECT);
            res = pstm.executeQuery();

            while (res.next()) {
                Funcionario f = new Funcionario();
                f.setIdFuncionario(res.getInt("id_funcionario"));
                f.setLoginFuncionario(res.getString("login_funcionario"));
                f.setSenhaFuncionario(res.getString("senha_funcionario"));
                funcionario.add(f);
            }

        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao tentar buscar os funcionários do banco: " + ex.getMessage());
        }
        return funcionario;
    }
}
