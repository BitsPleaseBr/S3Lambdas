package s3.api.users.cadastrar.cadastros.usuario;

import java.sql.SQLException;
import java.util.Map.Entry;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import control.crypto.PswdStorage;
import model.bean.EnderecoBean;
import model.bean.EspecialidadeBean;
import model.bean.MedicoBean;
import model.bean.PacienteBean;
import model.bean.TelefoneBean;
import model.bean.UserBean;
import model.bean.info.MedicoInfo;
import model.bean.info.UserInfo;
import model.dao.MedicoDao;
import model.dao.PacienteDao;
import s3.api.Handler;
import s3.api.users.cadastrar.cadastros.NotificadorSNS;

public class CadUserHandler extends Handler
    implements RequestHandler<CadUserRequest, CadUserResponse> {

  @Override
  public CadUserResponse handleRequest(CadUserRequest input, Context context) {

    setContext(context);

    CadUserResponse response = new CadUserResponse();

    
    UserBean ub = new UserBean() {};

    log("Definindo informações básicas do usuário...");
    for (Entry<UserInfo, Object> entrada : input.getInfosUser().entrySet())
      ub.setInfo(entrada.getKey(), entrada.getValue());

    String senha = input.getInfosUser().get(UserInfo.Senha).toString();
    String email = input.getInfosUser().get(UserInfo.Email).toString();

    ub.setInfo(UserInfo.Senha, PswdStorage.clientPswdHash(senha, email));

    if (input.getEnderecos() != null) {
      log("Definindo endereços do usuário...");
      for (EnderecoBean endereco : input.getEnderecos())
        ub.addEndereco(endereco);
    }
    
    if (input.getTelefones() != null) {
      log("Definindo telefones do usuário...");
      for (TelefoneBean telefone : input.getTelefones())
        ub.addTelefone(telefone);
    }
    
    
    if ((double) input.getInfosUser().get(UserInfo.Tipo) == 2) {

      MedicoBean mb = new MedicoBean();

      mb.getInfosUser().putAll(ub.getInfosUser());
      mb.addEnderecos(ub.getEnderecos());
      mb.addTelefones(ub.getTelefones());
      
      log("Definindo informações médicas do médico...");
      for (Entry<MedicoInfo, Object> entrada : input.getInfosMed().entrySet())
        mb.setInfo(entrada.getKey(), entrada.getValue());

      if (input.getEspecialidades() != null) {
        
        log("Definindo especialidades do médico...");
        for (EspecialidadeBean especialidade : input.getEspecialidades())
          mb.addEspecialidade(especialidade);
      }
      
      try {

        log("Tentando cadastrar médico...");
        response.setId(new MedicoDao().cadastrar(mb));

        log("Cadastro concluído com sucesso!");

      } catch (SQLException e) {

        e.printStackTrace();
        log("Erro ao cadastrar médico!");

        response.addMessage("Falha", System.getenv("SQLException"));
        response.addMessage("Erro de cadastro",
            "Não foi possível cadastrar o médico: " + mb.toString());
        response.setSucesso(false);

        log(System.getenv("SQLException") + ": " + e.getMessage());

        return response;
      }
    } else {

      PacienteBean pb = new PacienteBean();

      pb.getInfosUser().putAll(ub.getInfosUser());
      pb.addEnderecos(ub.getEnderecos());
      pb.addTelefones(ub.getTelefones());

      try {

        log("Tentando cadastrar paciente...");
        response.setId(new PacienteDao().cadastrar(pb));

        log("cadastro concluído com sucesso!");
      } catch (SQLException e) {

        e.printStackTrace();
        log("Erro ao cadastrar paciente!");

        response.addMessage("Falha", System.getenv("SQLException"));
        response.addMessage("Erro de cadastro",
            "Não foi possível cadastrar o paciente: " + pb.toString());
        response.setSucesso(false);

        log(System.getenv("SQLException") + ": " + e.getMessage());

        return response;
      }
    }

    response.setSucesso(true);
    log("A função foi executa com sucesso!");

    String userEmail = input.getInfosUser().get(UserInfo.Email).toString();
    String userNome = input.getInfosUser().get(UserInfo.Nome).toString();

    log("Notificando cadastro no canal SNS");
    NotificadorSNS.publicarCadastro(userEmail, userNome);

    return response;
  }
}
