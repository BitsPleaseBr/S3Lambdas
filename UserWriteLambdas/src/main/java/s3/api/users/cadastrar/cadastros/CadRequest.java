package s3.api.users.cadastrar.cadastros;

import java.util.HashMap;
import java.util.List;
import model.bean.EnderecoBean;
import model.bean.TelefoneBean;
import model.bean.info.MedicoInfo;
import model.bean.info.PacienteInfo;
import model.bean.info.UserInfo;
import s3.api.users.cadastrar.CadastroRequest;

public class CadRequest extends CadastroRequest {

  
  private HashMap<UserInfo, Object> infosUser;
  private HashMap<MedicoInfo, Object> infosMed;
  private HashMap<PacienteInfo, Object> infosPac;
  private List<EnderecoBean> enderecos;
  private List<TelefoneBean> telefones;
  
  
  public CadRequest(HashMap<UserInfo, Object> infosUser, HashMap<MedicoInfo, Object> infosMed,
      HashMap<PacienteInfo, Object> infosPac, List<EnderecoBean> enderecos,
      List<TelefoneBean> telefones) {
    super();
    this.infosUser = infosUser;
    this.infosMed = infosMed;
    this.infosPac = infosPac;
    this.enderecos = enderecos;
    this.telefones = telefones;
  }


  public HashMap<UserInfo, Object> getInfosUser() {
    return infosUser;
  }




  public void setInfosUser(HashMap<UserInfo, Object> infosUser) {
    this.infosUser = infosUser;
  }




  public HashMap<MedicoInfo, Object> getInfosMed() {
    return infosMed;
  }




  public void setInfosMed(HashMap<MedicoInfo, Object> infosMed) {
    this.infosMed = infosMed;
  }




  public HashMap<PacienteInfo, Object> getInfosPac() {
    return infosPac;
  }




  public void setInfosPac(HashMap<PacienteInfo, Object> infosPac) {
    this.infosPac = infosPac;
  }




  public List<EnderecoBean> getEnderecos() {
    return enderecos;
  }




  public void setEnderecos(List<EnderecoBean> enderecos) {
    this.enderecos = enderecos;
  }




  public List<TelefoneBean> getTelefones() {
    return telefones;
  }




  public void setTelefones(List<TelefoneBean> telefones) {
    this.telefones = telefones;
  }




  public CadRequest() {}
}