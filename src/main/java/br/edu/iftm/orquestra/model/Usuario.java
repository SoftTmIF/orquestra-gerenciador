package br.edu.iftm.orquestra.model;

public class Usuario {
    private Integer id_usuario;
    private String nome_usuario;
    private String senha;

    public Usuario() {
    }

    public Usuario(String nome_usuario, String senha) {
        this.senha = senha;
        this.nome_usuario = nome_usuario;
    }

    public Usuario(Integer id_usuario, String nome_usuario, String senha) {
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.senha = senha;
    }
    
    public Integer getId_usuario() {
        return this.id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome_usuario() {
        return this.nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

}
