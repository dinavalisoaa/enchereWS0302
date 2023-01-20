
package model;

import BddObject.Connexion;
import BddObject.Ignore;
import BddObject.InfoDAO;
import BddObject.ObjectBDD;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author dina
 */
@InfoDAO(table = "EnchereAdj")
public class EnchereAdj extends ObjectBDD {  

	private int id=-1;
	private String dateAdj;
	private int usersId=-1;
	private int enchereMoveId=-1;
@Ignore
    Users user;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    
	public String getDateAdj() {
		return this.dateAdj;
	}

	/**
	 * 
	 * @param dateAdj
	 */
	public void setDateAdj(String dateAdj) {
		this.dateAdj = dateAdj;
	}

	public int getUsersId() {
		return this.usersId;
	}

	/**
	 * 
	 * @param usersId
	 */
	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public int getEnchereMoveId() {
		return this.enchereMoveId;
	}

	/**
	 * 
	 * @param enchereMoveId
	 */
	public void setEnchereMoveId(int enchereMoveId) {
		this.enchereMoveId = enchereMoveId;
	}

}