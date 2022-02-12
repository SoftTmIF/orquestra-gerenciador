package br.edu.iftm.orquestra.controller;

import br.edu.iftm.orquestra.model.PesquisaTask;
import br.edu.iftm.orquestra.model.Task;
import br.edu.iftm.orquestra.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskControlador {

    @Autowired
    TaskRepository repo;

//    Retorna lista completa de tasks
    @GetMapping(value = "/tasks")
    public String tasksTabela(Model modelo) {
        List<Task> lista = repo.buscaTodasTasks();
        modelo.addAttribute("tasks", lista);
        modelo.addAttribute("pesquisa", new PesquisaTask());
        return "lista-tasks";
    }

//    Recebe datas que deverão ser pesquisadas
    @PostMapping(value = "/tasks")
    public String tasksPesquisa(Model modelo, PesquisaTask pesquisa) {
        List<Task> lista = repo.pesquisaTaksData(pesquisa);
        modelo.addAttribute("tasks", lista);
        modelo.addAttribute("pesquisa", new PesquisaTask());
        return "lista-tasks";
    }

//    Redireciona para página de criação de nova task
    @GetMapping(value = "/cadastro-task")
    public String Task(Model modelo) {
        modelo.addAttribute("task", new Task());
        return "cadastro-task";
    }

//    Cria nova task
    @PostMapping(value = "/cadastro-task")
    public String tasksCadastro(Task task) {
        if(task.getId() == null) {
            repo.insereTask(task);
            repo.insereUsuarioProjeto(task);
        } else {
            repo.atualizaTask(task);
        }
        return "redirect:/tasks";
    }

//    Edita task na lista de tasks
    @GetMapping(value = "/editar-task")
    public String editaTask(@RequestParam(name = "id", required = true) Integer cod, Model modelo) {
        modelo.addAttribute("task", repo.buscaPorId(cod));
        return "cadastro-task";
    }

//    Exclui task
    @GetMapping(value = "excluir-task")
    public String excluiTask(@RequestParam(name = "id", required = true) Integer cod) {
        repo.excluiTask(cod);
        return "redirect:/tasks";

    }

}
