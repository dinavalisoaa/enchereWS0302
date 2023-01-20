
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
@InfoDAO(table = "Parametrage")
public class Parametrage extends ObjectBDD {  


	private int id=-1;
	private String value;
	private String nom;

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
 public  Parametrage[] parametrages() throws Exception {
        ArrayList lis = new Parametrage().select(null);
        Parametrage[] oo = new Parametrage[lis.size()];
        for (int i = 0; i < oo.length; i++) {
            oo[i] = (Parametrage) lis.get(i);
        }
        return oo;
    }
	public String getValue() {
		return this.value;
	}

	/**
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public String getNom() {
		return this.nom;
	}

	/**
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

}