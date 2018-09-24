package s3.api.users.cadastrar.cadastros.medico;

import java.util.HashMap;
import java.util.List;
import model.bean.EnderecoBean;
import model.bean.TelefoneBean;
import model.bean.info.MedicoInfo;
import model.bean.info.PacienteInfo;
import model.bean.info.UserInfo;
import s3.api.users.cadastrar.cadastros.CadRequest;

public class CadMedRequest extends CadRequest {
  

  public CadMedRequest(HashMap<UserInfo, Object> infosUser, HashMap<MedicoInfo, Object> infosMed,
      HashMap<PacienteInfo, Object> infosPac, List<EnderecoBean> enderecos,
      List<TelefoneBean> telefones) {
    
    super(infosUser, infosMed, infosPac, enderecos, telefones);
  }
  

  public CadMedRequest() {}
}