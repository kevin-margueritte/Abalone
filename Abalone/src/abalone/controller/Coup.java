package abalone.controller;

import java.util.Arrays;

/**
 * Classe qui permet d'enregistrer un coup, un coup posse des coordonnees initials et des coordonnees final
 * cette classe est utilise pour connaitre la trajectoire d'une bille
 * @author groupe projet
 * @mail kevin.margueritte@gmil.com
 *
 */

public class Coup {
	int[] iInit;
	int[] jInit;
	int[] iFin;
	int[] jFin;

	/**CONSTRUCTEUR**/
	public Coup( int iInit[] , int jInit[] , int iFin[] , int jFin[]){	
		this.iInit = iInit;
		this.jInit = jInit;
		this.iFin = iFin;
		this.jFin = jFin;
	}
	
	public Coup(){
		this(new int[] {0},new int[] {0},new int[] {0},new int[] {0});
	}
	
	public Coup( int iInit , int jInit , int iFin , int jFin){
		this.iInit = new int[1];
		this.iInit[0] = iInit;
		this.jInit = new int[1];
		this.jInit[0] = jInit;
		this.iFin = new int[1];
		this.iFin[0] = iFin;
		this.jFin = new int[1];
		this.jFin[0] = jFin;
	}
	
	/**GETTERS**/
	
	@Override
	public String toString() {
		return "Coup [iInit=" + Arrays.toString(iInit) + ", jInit="
				+ Arrays.toString(jInit) + ", iFin=" + Arrays.toString(iFin)
				+ ", jFin=" + Arrays.toString(jFin) + "]";
	}

	public int getiInit( int index ) {
		return iInit[index];
	}
	
	public int getiInit() {
		return iInit[0];
	}

	public int getjInit( int index ) {
		return jInit[index];
	}

	public int getjInit() {
		return jInit[0];
	}

	public int getiFin( int index ) {
		return iFin[index];
	}
	
	public int getiFin() {
		return iFin[0];
	}

	public int getjFin( int index ) {
		return jFin[index];
	}
	
	public int getjFin() {
		return jFin[0];
	}
	
	public int tailleCoup(){
		return iInit.length;
	}

}
