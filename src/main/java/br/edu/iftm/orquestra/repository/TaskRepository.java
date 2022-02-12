package br.edu.iftm.orquestra.repository;

import br.edu.iftm.orquestra.model.PesquisaTask;
import br.edu.iftm.orquestra.model.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskRepository {
    @Autowired
    UsuarioRepository UsuarioRepository;

    static Map mapa = DicionarioNaturezas();
    public static Map DicionarioNaturezas() {
        Map<String, String> mapa = new HashMap<>();
        mapa.put("D", "Desenvolvimento");
        mapa.put("T", "Testes");
        mapa.put("A", "Administrativo");
        return mapa;
    }
    public static Map AdicionarDicionario(String natureza, Map mapaInicial) {
        Map<String, String> mapa = mapaInicial;
        if(mapa.get(natureza) == null) {
            mapa.put(natureza.substring(0,1), natureza);
        }
        return mapa;
     }
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Task> buscaTodasTasks() {
        String consulta = "SELECT * FROM TASK;";
        //nomes de acordo com as variáveis criadas no banco de dados
        return jdbcTemplate.query(
                consulta,
                (resultado, numeroDaLinha) -> {
                    return new Task(
                            resultado.getInt("id_task"),
                            resultado.getDate("data_de_execucao"),
                            resultado.getDouble("total_de_horas"),
                            mapa.get(resultado.getString("tipo_natureza")).toString(), //buscar no mapa a descrição da natureza 
                            resultado.getString("descricao"),
                            UsuarioRepository.buscaPorId(resultado.getInt("id_usuario")).getNome_usuario(),
                            resultado.getInt("projeto")
                    );
                }
        );
    }

    public List<Task> pesquisaTaksData(PesquisaTask pesquisa) {
        String consulta = "SELECT * from task " +
                            "WHERE data_de_execucao " +
                            "BETWEEN ? " +
                            "AND ? ";
        return jdbcTemplate.query(
                consulta,
                (resultado, numeroDaLinha) -> {
                    return new Task(
                            resultado.getInt("id_task"),
                            resultado.getDate("data_de_execucao"),
                            resultado.getDouble("total_de_horas"),
                            mapa.get(resultado.getString("tipo_natureza")).toString(),
                            resultado.getString("descricao"),
                            UsuarioRepository.buscaPorId(resultado.getInt("id_usuario")).getNome_usuario(),
                            resultado.getInt("projeto")
                    );},
                pesquisa.getDataInicioPesquisa(),pesquisa.getDataFimPesquisa());
    }

    public int insereTask(Task task) {
        mapa = AdicionarDicionario(task.getTipoNatureza(), mapa);
        String consulta = "INSERT INTO TASK (data_de_execucao, total_de_horas, tipo_natureza, descricao, id_usuario, projeto) VALUES(? ,? ,? ,? ,? ,? )";
        return jdbcTemplate.update(consulta,
                task.getDataExecucao(),
                task.getTotalHoras(),
                task.getTipoNatureza().substring(0, 1),
                task.getDescricao(),
                UsuarioRepository.buscaPorUsuario(task.getUsuario()).getId_usuario(),
                task.getIdProjeto());
    }

    public int insereUsuarioProjeto(Task task) {
        try {String consulta = "INSERT INTO usuario_projeto (usuario_r, projeto_r) VALUES(?,?)";
        return jdbcTemplate.update(consulta,
                UsuarioRepository.buscaPorUsuario(task.getUsuario()).getId_usuario(),
                task.getIdProjeto());
        } catch(DataAccessException d) {
            return 0;
        }
    }

    public int atualizaTask(Task task) {
        mapa = AdicionarDicionario(task.getTipoNatureza(), mapa);
        String consulta = "UPDATE TASK SET " +
                                "data_de_execucao = ?, " +
                                "total_de_horas = ?, " +
                                "tipo_natureza = ?, " +
                                "descricao = ? " +
                           "WHERE " +
                                "id_task = ? ";
        return jdbcTemplate.update(consulta,
                                    task.getDataExecucao(),
                                    task.getTotalHoras(),
                                    task.getTipoNatureza().substring(0,1),
                                    task.getDescricao(),
                                    task.getId()
        );
    }

    public int excluiTask(Integer id) {
        String consulta = "DELETE FROM TASK WHERE id_task = ?";
        return jdbcTemplate.update(consulta, id);
    }

    public Task buscaPorId(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM TASK WHERE id_task = ? ",
                (resultado, numeroDaLinha) -> {
                    return new Task(
                            resultado.getInt("id_task"),
                            resultado.getDate("data_de_execucao"),
                            resultado.getDouble("total_de_horas"),
                            mapa.get(resultado.getString("tipo_natureza")).toString(), //buscar no mapa a descrição da natureza 
                            resultado.getString("descricao"),
                            UsuarioRepository.buscaPorId(resultado.getInt("id_usuario")).getNome_usuario(),
                            resultado.getInt("projeto")
                            );},
                id);
    }









}
