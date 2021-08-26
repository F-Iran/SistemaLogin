package controllers;

import models.Usuario;
import play.libs.Crypto;
import play.mvc.Controller;

public class Login extends Controller {
	
	
	public static void teste(){
		
		Usuario u = new Usuario();
		u.email = "admin@admin.com";
		u.senha = "123456";
		u.nivel = 1;
		u.save();
		
		form();
	}
	
	
	public static void form(){
		render();
	}
	
	public static void logar(String email, String senha){
		
		Usuario usu = Usuario.find("email = ?1 and senha = ?2 ",
									email, Crypto.passwordHash(senha) ).first();
		
		if (usu == null){
			
			flash.error("Login ou senha estão Incorretos");
			form();
		} else {
			session.put("usuario.email", usu.email);
			session.put("usuario.nivel", usu.nivel);
			
			Usuarios.listar();
		}
	}
	
	public static void sair(){
		session.clear();
		Login.form();
	}
	
}
