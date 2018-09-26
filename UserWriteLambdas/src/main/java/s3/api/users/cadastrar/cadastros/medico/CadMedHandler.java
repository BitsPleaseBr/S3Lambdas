package s3.api.users.cadastrar.cadastros.medico;

import java.sql.SQLException;
import java.util.Map.Entry;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.bean.MedicoBean;
import model.bean.info.MedicoInfo;
import model.bean.info.UserInfo;
import model.dao.MedicoDao;
import s3.api.Handler;
import s3.api.users.cadastrar.cadastros.NotificadorSNS;

public class CadMedHandler extends Handler implements RequestHandler<CadMedRequest, CadMedResponse> {
  
  @Override
  public CadMedResponse handleRequest(CadMedRequest input, Context context) {
    
    setContext(context);
    
    //Fazendo resposta
    CadMedResponse response = new CadMedResponse();
    
    MedicoBean mb = new MedicoBean();

    log("Definindo informações médicas do médico...");
    for (Entry<MedicoInfo, Object> entrada : input.getInfosMed().entrySet())
      mb.setInfo(entrada.getKey(), entrada.getValue());
    
    //Cadastrando
    int id = -1;
    
    try {
      
      log("Cadastrando dados médicos...");
      
      id = new MedicoDao().cadastrar(mb);
      
      log("Médico cadastrado com sucesso!");
    } catch (SQLException e) {
      
      response.setSucesso(false);
      response.addMessage("Falha", System.getenv("SQLException"));
      
      log(System.getenv("SQLException") + ": " + e.getMessage());
      
      return response;
    }
    
    response.setId(id);
    response.setSucesso(true);
    
    log("Notificando cadastro no canal SNS");
    NotificadorSNS.publicarCadastro(mb.getInfo(UserInfo.Email).toString(), mb.getInfo(UserInfo.Nome).toString());
    
    log("A função foi executada com sucesso!");
    
    return response;
  }
}
