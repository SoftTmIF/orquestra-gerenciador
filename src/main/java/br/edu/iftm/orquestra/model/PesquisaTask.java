package br.edu.iftm.orquestra.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class PesquisaTask {
    private String dataInicioPesquisa;
    private String dataFimPesquisa;

    public PesquisaTask() {
    }

    public PesquisaTask(String dataInicioPesquisa, String dataFimPesquisa) {
        this.dataInicioPesquisa = dataInicioPesquisa;
        this.dataFimPesquisa = dataFimPesquisa;
    }

    public String getDataInicioPesquisa() {
        return dataInicioPesquisa;
    }

    public void setDataInicioPesquisa(String dataInicioPesquisa) {
        this.dataInicioPesquisa = dataInicioPesquisa;
    }

    public String getDataFimPesquisa() {
        return dataFimPesquisa;
    }

    public void setDataFimPesquisa(String dataFimPesquisa) {
        this.dataFimPesquisa = dataFimPesquisa;
    }
}
