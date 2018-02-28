package br.com.caelum.contas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {
	
	private ContaDAO contaDAO;
	
	@Autowired
	public ContaController(ContaDAO contaDAO) {
		this.contaDAO = contaDAO;
	}

	@RequestMapping("/pagarConta")
	public void pagarConta(Conta conta, HttpServletResponse response) {
		contaDAO.paga(conta.getId());

		response.setStatus(200);
	}
	
	@RequestMapping("/form")
	public String formulario() {
		return "conta/formulario";
	}
	
	@RequestMapping("/adicionaConta")
	public String adiciona(@Valid Conta conta, BindingResult result) {
		if(result.hasErrors()) {
			return "conta/formulario";
		}
		
		System.out.println("Conta adicionada é: " +  conta.getDescricao());
		
		contaDAO.adiciona(conta);
		
		return "conta/conta-adicionada";
	}
	
	@RequestMapping("/listaContas")
	public ModelAndView lista(){
		List<Conta> contas = contaDAO.lista();
		
		ModelAndView mv = new ModelAndView("conta/lista");
		mv.addObject("contas", contas);
		
		return mv;
	}
	
	@RequestMapping("/removeConta")
	public String remove(Conta conta) {
		contaDAO.remove(conta);
		return "redirect:listaContas";
	}
	
	@RequestMapping("/mostraConta")
	public ModelAndView mostra(Long id) {
	  ModelAndView mv = new ModelAndView("/conta/mostra");
	  mv.addObject("conta", contaDAO.buscaPorId(id));
	  return mv;
	}
	
	@RequestMapping("/alteraConta")
	public String altera(Conta conta) {
	  contaDAO.altera(conta);
	  return "redirect:listaContas";
	}
}
