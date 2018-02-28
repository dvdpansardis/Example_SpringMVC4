package br.com.caelum.contas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaMundoController {

	@RequestMapping(value = "/olaMundoSpring")
	public String execute() {
		System.out.println("Executando uma logica com spring mvc");
		return "ok";
	}
	
}
