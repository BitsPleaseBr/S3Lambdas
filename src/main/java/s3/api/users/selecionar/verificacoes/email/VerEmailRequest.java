package s3.api.users.selecionar.verificacoes.email;

import s3.api.users.selecionar.SelecionarRequest;

public class VerEmailRequest extends SelecionarRequest {
  
  
  public VerEmailRequest(String email) {
    
    this.email = email;
  }
  
  public VerEmailRequest() {}
}