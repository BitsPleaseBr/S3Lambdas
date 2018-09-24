package s3.api.users.cadastro.cadastros.usuario;

import java.util.HashMap;
import s3.api.users.cadastro.cadastros.CadRequest;

public class CadUserRequest extends CadRequest {


  public HashMap<String, Object> infos;

  public CadUserRequest() {
    // TODO Auto-generated constructor stub
  }

  public HashMap<String, Object> getInfos() {

    return this.infos;
  }
  
  public Object getInfo(String key) {
    
    return this.infos.get(key);
  }

  public void setInfos(HashMap<String, Object> infos) {

    this.infos = infos;
  }
}
