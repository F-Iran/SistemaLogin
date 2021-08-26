package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.Max;
import play.data.validation.MaxSize;
import play.data.validation.Min;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class Usuario extends Model {

	
	@Required(message="requerido")
	public String nome;
	
	@Required (message="requerido") 
	@Email(message="Digite um email válido")
	public String email;
	@Required (message="requerido")
	public String cpf;
	@Required (message="requerido")
	public String data;
	
	@Required (message="requerido")
	@Equals(value="confirmacaoSenha", message="As senhas estão diferentes.")
	public String senha;
	
	@Transient
	public String confirmacaoSenha;
	
	@Required
	public int nivel;
	
	public void setSenha(String s){
		//123456
		//4QrcOUm6Wau+VuBX8g+IPg==
		senha = Crypto.passwordHash(s);
	}
	
	public void setConfirmacaoSenha(String s){
		if (s == null)// esse if permite inserir valores nulos na confirmacaosenha
			confirmacaoSenha = s;
		else
			confirmacaoSenha = Crypto.passwordHash(s);
			
	}
	
}
