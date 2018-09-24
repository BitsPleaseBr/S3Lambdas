package s3.api.users.cadastrar.cadastros.paciente;

import s3.api.users.cadastrar.cadastros.CadResponse;

public class CadPacResponse extends CadResponse {


  public CadPacResponse(boolean sucesso, int id) {

    super(sucesso, id);

    setLambdaInvocada("Lambda para Cadastro de Pacientes");
  }


  public CadPacResponse() {
    
    setLambdaInvocada("Lambda para Cadastro de Pacientes");
  }
}
