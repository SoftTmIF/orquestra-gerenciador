package br.edu.iftm.orquestra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iftm.orquestra.model.Projeto;
import br.edu.iftm.orquestra.repository.ProjetoRepository;

@Controller
public class ProjetoControlador {

    @Autowired
    ProjetoRepository repo;
    
    //Retorna lista de projetos
    @GetMapping("/projetos")
    public String projetosTabela(Model modelo) {
        List<Projeto> lista = repo.buscaTodosProjetos();
        modelo.addAttribute("projetos", lista);
        return "lista-projetos";
    }

    //Direciona para págica de cadastro de novo projeto 
    @GetMapping("/novo-projeto")
    public String projeto(Model modelo) {
        modelo.addAttribute("projeto", new Projeto());
        return "cadastro-projeto";
    }
    @PostMapping(value = "/novo-projeto")
    public String cadastroProjeto(Projeto projeto) {
        if (projeto.getId_projeto() == null) {
            repo.insereProjeto(projeto);
        } else {
            repo.atualizaProjeto(projeto);
        }
        return "redirect:/projetos";
    }
    
    //Editar projeto da lista 
    @GetMapping(value = "/editar-projeto")
    public String editaProjeto(@RequestParam(name = "id_projeto", required = true) Integer cod, Model modelo) {
        modelo.addAttribute("projeto", repo.buscaPorId(cod));
        return "cadastro-projeto";
    }

    //Excluir projeto da lista
    @GetMapping(value = "/excluir-projeto")
    public String excluiProjeto(@RequestParam(name = "id_projeto", required = true) Integer cod) {
        repo.excluiProjeto(cod);
        return "redirect:/projetos";
    }
}
