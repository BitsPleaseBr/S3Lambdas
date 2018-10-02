package s3.api.users.cadastrar;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import s3.api.Handler;
import s3.api.users.cadastrar.cadastros.medico.CadMedHandler;
import s3.api.users.cadastrar.cadastros.medico.CadMedRequest;
import s3.api.users.cadastrar.cadastros.paciente.CadPacHandler;
import s3.api.users.cadastrar.cadastros.paciente.CadPacRequest;
import s3.api.users.cadastrar.cadastros.usuario.CadUserHandler;
import s3.api.users.cadastrar.cadastros.usuario.CadUserRequest;

public class CadastroHandler extends Handler
    implements RequestHandler<CadastroRequest, CadastroResponse> {


  private final static String CADASTRO_MEDICO = System.getenv("CADASTRAR_MEDICO"),
                              CADASTRO_PACIENTE = System.getenv("CADASTRAR_PACIENTE"),
                              CADASTRO_USUARIO = System.getenv("CADASTRAR_USUARIO");


  @Override
  public CadastroResponse handleRequest(CadastroRequest input, Context context) {

    setContext(context);

    CadastroResponse response = new CadastroResponse();
    
    Gson g = new Gson().newBuilder().setPrettyPrinting().create();
    
    String json = g.toJson(input.getValores());
    
    log("Dados recebidos: " + json);
    
    log("Redirecionando request...");

    String tipo = input.getTipo();
    
    if (tipo.equals(CADASTRO_USUARIO))
      return new CadUserHandler().handleRequest(g.fromJson(json, CadUserRequest.class), context);
    
    if (tipo.equals(CADASTRO_MEDICO))
      return new CadMedHandler().handleRequest(g.fromJson(json, CadMedRequest.class), context);

    if (tipo.equals(CADASTRO_PACIENTE))
      return new CadPacHandler().handleRequest(g.fromJson(json, CadPacRequest.class), context);

    response.setSucesso(true);
    response.addMessage("Erro de Tipo", "Não foi encontrada nenhuma função lambda para o tipo " + tipo);
    
    log("A função foi executada com sucesso!");
    
    return response;
  }
}