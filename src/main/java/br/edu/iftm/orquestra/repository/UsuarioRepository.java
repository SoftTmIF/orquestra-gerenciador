package br.edu.iftm.orquestra.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.orquestra.model.Usuario;

@Repository
public class UsuarioRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean registraUsuario(Usuario usuario) {
        Usuario banco = buscaPorUsuario(usuario.getNome_usuario());
        if (banco.getNome_usuario() == null) {
            String update = "INSERT INTO usuario(nome_usuario, senha) VALUES(?,?)";
            jdbcTemplate.update(update, usuario.getNome_usuario(), usuario.getSenha());
            return true;
        } else if (banco.getNome_usuario().equals(usuario.getNome_usuario())) {
            return false;
        } else {
            String update = "INSERT INTO usuario(nome_usuario, senha) VALUES(?,?)";
            jdbcTemplate.update(update, usuario.getNome_usuario(), usuario.getSenha());
            return true;
        }
        /*
         * } catch (NestedRuntimeException e) {
         * String update = "INSERT INTO usuario(nome_usuario, senha) VALUES(?,?)";
         * jdbcTemplate.update(update, usuario.getNome_usuario(), usuario.getSenha());
         * return true;
         * }
         */
    }

    public Usuario buscaPorUsuario(String usuario) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM usuario WHERE nome_usuario = ? group by 1 limit 1;",
                    (resultSet, rowNum) -> new Usuario(resultSet.getInt("id_usuario"),
                            resultSet.getString("nome_usuario"),
                            resultSet.getString("senha")),
                    usuario);
        } catch (DataAccessException a) {
            return new Usuario(0, "null", "null");
        }
    }

    public boolean autenticar(Usuario testeLogin) {
        try {
            Usuario banco = buscaPorUsuario(testeLogin.getNome_usuario());
            if (banco.getNome_usuario().equals(testeLogin.getNome_usuario())
                    && banco.getSenha().equals(testeLogin.getSenha()))
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Usuario buscaPorId(Integer idUsuario) {
        return jdbcTemplate.queryForObject("SELECT * FROM usuario WHERE id_usuario = ? group by 1 limit 1;",
                (resultSet, rowNum) -> new Usuario(resultSet.getInt("id_usuario"),
                        resultSet.getString("nome_usuario"),
                        resultSet.getString("senha")),
                idUsuario);
    }
}
