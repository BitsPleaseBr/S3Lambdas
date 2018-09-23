package s3.api.users.selecionar;

import s3.api.Request;

public class SelecionarRequest extends Request {

  
  private int id;
  private String senha;
  protected String email;
  
  
  public void setId(int id) {
    
    this.id = id;
  }
  
  public int getId() {
    
    return this.id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }
  
  
}