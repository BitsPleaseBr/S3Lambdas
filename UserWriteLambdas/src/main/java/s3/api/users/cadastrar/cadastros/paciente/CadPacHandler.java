package s3.api.users.cadastrar.cadastros.paciente;

import java.sql.SQLException;
import java.util.Map.Entry;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import model.bean.PacienteBean;
import model.bean.info.PacienteInfo;
import model.bean.info.UserInfo;
import model.dao.PacienteDao;
import s3.api.Handler;
import s3.api.users.cadastrar.cadastros.NotificadorSNS;

public class CadPacHandler extends Handler
    implements RequestHandler<CadPacRequest, CadPacResponse> {

  @Override
  public CadPacResponse handleRequest(CadPacRequest input, Context context) {

    setContext(context);

    CadPacResponse response = new CadPacResponse();

    PacienteBean pb = new PacienteBean();

    log("Definindo informações de usuário do paciente...");
    for (Entry<PacienteInfo, Object> entrada : input.getInfosPac().entrySet())
      pb.setInfo(entrada.getKey(), entrada.getValue());
 
    int id = -1;
    try {

      log("Cadastrando paciente...");

      id = new PacienteDao().cadastrar(pb);

      log("Paciente cadastrado com sucesso!");
    } catch (SQLException e) {

      response.setSucesso(false);
      response.addMessage("Falha", System.getenv("SQLException"));

      log(System.getenv("SQLException") + ": " + e.getMessage());

      return response;
    }

    response.setId(id);
    response.setSucesso(true);

    log("Notificando cadastro no canal SNS");
    NotificadorSNS.publicarCadastro(input.getInfosUser().get(UserInfo.Email).toString(),
        input.getInfosUser().get(UserInfo.Nome).toString());

    log("A função foi executada com sucesso!");

    return response;
  }
}
