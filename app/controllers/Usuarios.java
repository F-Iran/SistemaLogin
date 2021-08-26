package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Usuario;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Usuarios extends Controller {
	
	public static void form() {
            Usuario usuario = (Usuario) Cache.get("usuario");
            Cache.clear();
            render(usuario);
	}
	
	public static void salvar( Usuario usuario, String senha) {
		
		if (validation.hasErrors()) {
			validation.keep();
			
			flash.error("Falha ao salvar o usuario.");
			Cache.set("usuario", usuario);
			
			form();
		}
		
		if (senha.equals("") == false || usuario.id == null){
			usuario.senha = senha;
		
			
			if (senha.length() < 6) {
				validation.addError("usuario.senha", "A senha deve conter no minimo 6 digitos");
			}
		
		}
		
		
		validation.valid(usuario);
		
		if (senha.equals("") && usuario.id != null) {
			
			validation.removeErrors("usuario.senha");
			if (validation.errors().size() == 1) {
				validation.clear();
			}
		}
		
		if (validation.hasErrors()) {
			validation.keep();
			flash.error("Falha ao salvar o usuario");
			Cache.set("usuario", usuario);
			form();
		}
		
		usuario.senha=senha;

		usuario.save();
		flash.success("Cadastrado com Sucesso");
		listar();
	}
	
	public static void editar(Long id) {
		Usuario usuario = Usuario.findById(id);
		renderTemplate("Usuarios/form.html", usuario);
	}
	
	public static void remover(Long id) {
		Usuario usuario = Usuario.findById(id);
		usuario.delete();
		flash.success("usuario excluido com sucesso");
		listar();
	}
	
	
	public static void listar() {
		List<Usuario> usuarios = Usuario.findAll();
		render(usuarios);
	}
}
