package abalone.controller;


/**
 * Classe de type enumere avec les couleurs des billes
 * @author groupe projet
 *
 */

public enum TypeBille {
	BLACK,WHITE;
	
	/**
	 * Renvoi une bille blanche si pion = 1 et noir sinon
	 * @param int pion
	 * @return TypeBille
	 */
	public static TypeBille getBille(int pion ){ 
		return (pion == 1 ) ? TypeBille.WHITE : TypeBille.BLACK;
	}
}
