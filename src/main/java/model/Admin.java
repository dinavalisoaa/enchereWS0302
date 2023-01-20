
package model;

import BddObject.Connexion;
import BddObject.InfoDAO;
import BddObject.ObjectBDD;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author dina
 */
@InfoDAO(table = "Admin")
public class Admin extends ObjectBDD {  

	private int id=-1;
	private String login;
	private String mdp;

	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	/**
	 * 
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return this.mdp;
	}

	/**
	 * 
	 * @param mdp
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
public int getLoginId() throws Exception{
    this.setMdp(mdp);
    this.setLogin(login);
    if(this.select(null).size()>0){
        return ((Admin)this.select(null).get(0)).getId();
    }
    return -1;
}
}