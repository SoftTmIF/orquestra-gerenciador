package br.edu.iftm.orquestra.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Task {
    private Integer id; //AUTO_INCREMENT
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date dataExecucao;
    private Double totalHoras; 
    private String tipoNatureza; 
    private String descricao;
    private String usuario; //buscar do objeto de login
    private Integer idProjeto;

    public Task() {
    }

    public Task(Integer id, Date dataExecucao, Double totalHoras, String tipoNatureza, String descricao, String usuario, Integer idProjeto) {
        this.id = id;
        this.dataExecucao = dataExecucao;
        this.totalHoras = totalHoras;
        this.tipoNatureza = tipoNatureza;
        this.descricao = descricao;
        this.usuario = usuario;
        this.idProjeto = idProjeto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataExecucao() {
        return dataExecucao;
    }

    public void setDataExecucao(Date dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

    public Double getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(Double totalHoras) {
        this.totalHoras = totalHoras;
    }

    public String getTipoNatureza() {
        return tipoNatureza;
    }

    public void setTipoNatureza(String tipoNatureza) {
        this.tipoNatureza = tipoNatureza;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    

    public Integer getIdProjeto() {
        return this.idProjeto;
    }

    public void setIdProjeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }

}
