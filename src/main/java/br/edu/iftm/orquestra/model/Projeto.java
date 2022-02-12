package br.edu.iftm.orquestra.model;

import java.util.List;

public class Projeto {

    private Integer id_projeto;
    private String nome_projeto;
    private List<Integer> item_task; //buscar id_task da tabela task where projeto
    private Double totalHoras; //somar horas das tasks
    private List<String> usuarioProjeto;

    public Projeto() {
    }     

    public Projeto(Integer id_projeto, String nome_projeto) {
        this.id_projeto = id_projeto;
        this.nome_projeto = nome_projeto;
    }
    
    public Projeto(Integer id_projeto, String nome_projeto, List item_task, Double totalHoras, List usuarioProjeto) {
        this.id_projeto = id_projeto;
        this.nome_projeto = nome_projeto;
        this.item_task = item_task;
        this.totalHoras = totalHoras;
        this.usuarioProjeto = usuarioProjeto;
    }

    public Double getTotalHoras() {
        return this.totalHoras;
    }

    public void setTotalHoras(Double totalHoras) {
        this.totalHoras = totalHoras;
    }

    
    public Integer getId_projeto() {
        return this.id_projeto;
    }

    public void setId_projeto(Integer id_projeto) {
        this.id_projeto = id_projeto;
    }

    public String getNome_projeto() {
        return this.nome_projeto;
    }

    public void setNome_projeto(String nome_projeto) {
        this.nome_projeto = nome_projeto;
    }

    public List getItem_task() {
        return this.item_task;
    }

    public void setItem_task(List item_task) {
        this.item_task = item_task;
    }

    public List getUsuarioProjeto() {
        return this.usuarioProjeto;
    }

    public void setUsuarioProjeto(List usuarioProjeto) {
        this.usuarioProjeto = usuarioProjeto;
    }
    

}
