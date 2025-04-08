package br.com.vemprafam.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.vemprafam.dao.DaoAluno;
import br.com.vemprafam.pojo.Aluno;

@Controller
@RequestMapping("/alunos")
public class AlunosController {

	private DaoAluno dao = new DaoAluno();

	@GetMapping
	public String homeEmpty() {
		return "alunos";
	}

	@GetMapping("/")
	public String home() {
		return "alunos";
	}


	@GetMapping("/new")
	public String showInsertForm(Model model) {
		model.addAttribute("aluno", new Aluno());
		return "create-aluno";
	}

	@PostMapping
	public String createAluno(@ModelAttribute Aluno aluno) {
		dao.insert(aluno);
		return "redirect:alunos";
	}

	@GetMapping("/lista")
	public String showListaAlunos(Model model) {
		List<Aluno> lista = dao.getLista();
		model.addAttribute("alunos", lista);
		return "lista-alunos";
	}

	@GetMapping("/busca")
	public String showBusca(Model model) {
		return "busca-aluno";
	}

	@GetMapping("/showUpdate")
	public String showUpdateAluno(@RequestParam int ra, Model model) {
		Aluno aluno = dao.buscarPeloRa(ra);
		model.addAttribute("aluno", aluno);
		return "alterar-aluno";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Aluno aluno) {
		dao.update(aluno);
		return "alunos";
	}

	@GetMapping("/apagar")
	public String showDelete() {
		return "apagar-aluno";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam int ra) {
		Aluno aluno = dao.buscarPeloRa(ra);
		dao.delete(aluno);
		return "alunos";
	}
}
