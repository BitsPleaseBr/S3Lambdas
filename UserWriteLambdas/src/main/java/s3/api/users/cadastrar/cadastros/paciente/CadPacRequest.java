package s3.api.users.cadastrar.cadastros.paciente;

import java.util.HashMap;
import java.util.List;
import model.bean.EnderecoBean;
import model.bean.TelefoneBean;
import model.bean.info.MedicoInfo;
import model.bean.info.PacienteInfo;
import model.bean.info.UserInfo;
import s3.api.users.cadastrar.cadastros.CadRequest;

public class CadPacRequest extends CadRequest {
  
  
  public CadPacRequest(HashMap<UserInfo, Object> infosUser, HashMap<MedicoInfo, Object> infosMed,
      HashMap<PacienteInfo, Object> infosPac, List<EnderecoBean> enderecos,
      List<TelefoneBean> telefones) {
    
    super(infosUser, null, infosPac, enderecos, telefones, null);
  }
  

  public CadPacRequest() {}
}