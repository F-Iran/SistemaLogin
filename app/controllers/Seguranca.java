package controllers;

import play.mvc.Before;
import play.mvc.Controller;


public class Seguranca extends Controller {

	@Before(unless={"Usuarios.listar"})
	static void verificar(){
		
		if (session.contains("usuario.email") == false){
			Login.form();
		}
		
	}
	
	@Before(unless={"Usuarios.listar"})
	//@Before(only={"Usuarios.form","Usuarios.salvar","Usuarios.remover"})
	static void permissoes(){
		if (session.get("usuario.nivel").equals("1") == false){
			renderText("Acesso negado");
		}
	}
	
	
}
