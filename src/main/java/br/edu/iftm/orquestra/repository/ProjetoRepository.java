package br.edu.iftm.orquestra.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.orquestra.model.Projeto;

@Repository
public class ProjetoRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Integer> buscaTasksProjeto(Integer id_projeto) {
        String consulta = "SELECT ID_TASK FROM TASK WHERE PROJETO = ?;";
        return jdbcTemplate.query(
                consulta,
                (resultado, numeroDaLinha) -> {
                    return resultado.getInt("ID_TASK");
                }, id_projeto);
    }

    public Double buscaHorasProjeto(Integer id_projeto) {
        String consulta = "SELECT total_de_horas FROM TASK WHERE PROJETO = ?;";
        List<Double> horas = jdbcTemplate.query(
                consulta,
                (resultado, numeroDaLinha) -> {
                    return resultado.getDouble("total_de_horas");
                }, id_projeto);
        Double soma = 0.0;
        for (int i = 0; i < horas.size(); i++) {
            soma += (Double) horas.get(i);
        }
        return soma;
    }

    public List<String> buscaUsuariosProjeto(Integer id_projeto) {
        String consulta = "SELECT NOME_USUARIO FROM USUARIO_PROJETO UP INNER JOIN PROJETO P ON P.ID_PROJETO = UP.PROJETO_R INNER JOIN USUARIO U ON U.ID_USUARIO = UP.USUARIO_R WHERE ID_PROJETO = ?;";
        return jdbcTemplate.query(
                consulta,
                (resultado, numeroDaLinha) -> {
                    return resultado.getString("NOME_USUARIO");
                }, id_projeto);
    }

    public List<Projeto> buscaTodosProjetos() {
        String consulta = "SELECT * FROM PROJETO;";
        //nomes de acordo com as variáveis criadas no banco de dados
        return jdbcTemplate.query(
                consulta,
                (resultado, numeroDaLinha) -> {
                    return new Projeto(
                            resultado.getInt("id_projeto"),
                            resultado.getString("nome_projeto"),
                            buscaTasksProjeto(resultado.getInt("id_projeto")),
                            buscaHorasProjeto(resultado.getInt("id_projeto")),
                            buscaUsuariosProjeto(resultado.getInt("id_projeto")));
                });
    }
    
    public int insereProjeto(Projeto projeto) {
        String consulta = "INSERT INTO PROJETO (nome_projeto) VALUES(?)";
        return jdbcTemplate.update(consulta,
                projeto.getNome_projeto());
    }

    public int atualizaProjeto(Projeto projeto) {
        String consulta = "UPDATE PROJETO SET nome_projeto = ? WHERE id_projeto = ? ;";
        return jdbcTemplate.update(consulta, projeto.getNome_projeto(), projeto.getId_projeto());
    }

    public int excluiProjeto(Integer id) {
        String consulta = "DELETE FROM PROJETO WHERE id_projeto = ?";
        return jdbcTemplate.update(consulta, id);
    }

    public Projeto buscaPorId(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM PROJETO WHERE id_projeto = ? ",
                (resultado, numeroDaLinha) -> {
                    return new Projeto(
                        resultado.getInt("id_projeto"),
                        resultado.getString("nome_projeto")
                        );},
                id);
    }
    
}
