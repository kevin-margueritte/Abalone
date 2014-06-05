package abalone.model;

import java.io.Serializable;

/**
 * Model du jeu il comporte l'etat du plateau et le nombre de bille qu'il contient
 * @author groupe projet
 * @mail kevin.margueritte@gmail.com
 *
 */

@SuppressWarnings("serial")
public class Plateau implements Serializable{
	private int nbBillesBlanches;
	private int nbBillesNoirs;
	public int plateau[][];// Plateau de l'abalone

	/**
	 * Matrice 13x21, les -1 representent des cases injouables, les 0 sont des cases ou l'on peut se deplacer
	 * les 1 eux sont le joueur avec les billes blanches, les cases 2 representent le joueur avec les billes noires
	 */
	
	public Plateau() {
		this.plateau = new int[13][21];
		this.plateau = this.getPlateauInitial();
		this.nbBillesBlanches = 14;
		this.nbBillesNoirs = 14;
	}

	public Plateau(String s) {
		/*this.plateau = new int[13][21];
		int res[][] = {
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, 0, -3, 0, -3, 0, -3, 0, -3, 0, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, 0, -3, 1, -3, 0, -3, 0, -3, 1, -3, 0, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, 0, -3, 0, -3, 1, -3, 0, -3, 2, -3, 0, -3, 0, -1, -1, -1, -1 },
				{ -1, -1, -1, 0, -3, 0, -3, 0, -3, 2, -3, 2, -3, 0, -3, 0, -3, 0, -1, -1, -1 },
				{ -1, -1, 0, -3, 0, -3, 0, -3, 2, -3, 2, -3, 2, -3, 2, -3, 1, -3, 0, -1, -1 },
				{ -1, -1, -1, 0, -3, 0, -3, 0, -3, 2, -3, 2, -3, 0, -3, 0, -3, 0, -1, -1, -1 },
				{ -1, -1, -1, -1, 0, -3, 0, -3, 2, -3, 0, -3, 2, -3, 0, -3, 0, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, 0, -3, 1, -3, 0, -3, 0, -3, 1, -3, 0, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, 0, -3, 0, -3, 0, -3, 0, -3, 0, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 } };*/
		this.plateau = this.getPlateauInitial();
		this.nbBillesBlanches = 14;
		this.nbBillesNoirs = 14;
	}
	
	/**SETTERS**/

	public void setNbBillesBlanches(int nbBillesBlanches) {
		this.nbBillesBlanches = nbBillesBlanches;
	}

	public void setNbBillesNoirs(int nbBillesNoirs) {
		this.nbBillesNoirs = nbBillesNoirs;
	}
	
	/**
	 * Modifie une case par la valeur passe en param.
	 * @param i
	 * @param j
	 * @param valeur
	 */
	public void setCase( int i, int j, int valeur){
		this.plateau[i][j] = valeur;
	}
	
	/**
	 * Set la le plateau avec la matrice passe en parametre
	 * @param matrice
	 */
	
	public void copiePlateau(int[][] matrice) {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 21; j++) {
				this.plateau[i][j] = matrice[i][j];
			}
		}
	}
	
	public void decrementeBilleNoir(){
		this.nbBillesNoirs-- ;
	}
	
	public void decrementeBilleBlanche(){
		this.nbBillesBlanches-- ;
	}
	
	/** Methodes utiles **/
	
	/**
	 * Copie le tableau courant
	 * 
	 * @return la copie du plateau courant
	 */

	public Plateau copie() {
		Plateau copie = new Plateau();
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 21; j++) {
				copie.plateau[i][j] = this.plateau[i][j];
			}
		}
		return copie;
	}
	
	/**
	 * @return la grille du jeu a l'etat initial
	 */
	public int[][] getPlateauInitial(){
		int res[][] = {
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, 1, -3, 1, -3, 1, -3, 1, -3, 1, -1,
						-1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, 1, -3, 1, -3, 1, -3, 1, -3, 1, -3, 1, -1,
						-1, -1, -1, -1 },
				{ -1, -1, -1, -1, 0, -3, 0, -3, 1, -3, 1, -3, 1, -3, 0, -3, 0,
						-1, -1, -1, -1 },
				{ -1, -1, -1, 0, -3, 0, -3, 0, -3, 0, -3, 0, -3, 0, -3, 0, -3,
						0, -1, -1, -1 },
				{ -1, -1, 0, -3, 0, -3, 0, -3, 0, -3, 0, -3, 0, -3, 0, -3, 0,
						-3, 0, -1, -1 },
				{ -1, -1, -1, 0, -3, 0, -3, 0, -3, 0, -3, 0, -3, 0, -3, 0, -3,
						0, -1, -1, -1 },
				{ -1, -1, -1, -1, 0, -3, 0, -3, 2, -3, 2, -3, 2, -3, 0, -3, 0,
						-1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, 2, -3, 2, -3, 2, -3, 2, -3, 2, -3, 2, -1,
						-1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, 2, -3, 2, -3, 2, -3, 2, -3, 2, -1,
						-1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1 } };
		return res;
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return true si une case n'est pas dehors
	 */
	public boolean caseJouable(int i, int j){
		return this.plateau[i][j] != -1;
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @param ii
	 * @param jj
	 * @return true si les deux cases sont advaires
	 */
	
	public boolean caseAdvairse(int i, int j, int ii, int jj){
		return (this.plateau[i][j] == 1 && this.plateau[ii][jj] == 2) || (this.plateau[i][j] == 2 && this.plateau[ii][jj] == 1) ;
	}
	
	/**
	 * 
	 * @param ideplace
	 * @param jdeplace
	 * @param inew
	 * @param jnew
	 * @return true si deux cases sont voisines, false sinon
	 */
	public boolean auxsontVoisins(int ideplace, int jdeplace, int inew, int jnew) { 
		boolean voisin = false;
		if ((this.plateau[ideplace][jdeplace] == 1 || this.plateau[ideplace][jdeplace] == 2)
				&& (this.plateau[inew][jnew] == 1 || this.plateau[inew][jnew] == 2)
				&& (this.plateau[ideplace][jdeplace] == this.plateau[inew][jnew])) {
			if ((ideplace - 1 == inew && jdeplace - 1 == jnew)
					|| (ideplace + 1 == inew && jdeplace + 1 == jnew)
					|| (ideplace + 1 == inew && jdeplace - 1 == jnew)
					|| (ideplace - 1 == inew && jdeplace + 1 == jnew)
					|| (ideplace == inew && jdeplace - 2 == jnew)
					|| (ideplace == inew && jdeplace + 2 == jnew)) {
				voisin = true;
			}
		} 
		return voisin;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 21; j++) {
				if (this.plateau[i][j] == -1) {
					s += '#';
				}
				if (this.plateau[i][j] == 0) {
					s += 0;
				}
				if (this.plateau[i][j] == 1) {
					s += 1;
				}
				if (this.plateau[i][j] == 2) {
					s += 2;
				}
				if (this.plateau[i][j] == -3) {
					s += ' ';
				}
				if (j == 20) {
					s += System.getProperty("line.separator");
				}
			}
		}
		return s;
	}
	
	/**
	 * @param i
	 * @param j
	 * @param ii
	 * @param jj
	 * @return true si les deux cases possede le meme pion, false sinon
	 */
	public boolean memePion(int i, int j, int ii, int jj){
		return this.plateau[i][j] == this.plateau[ii][jj];
	}
	
	/**
	 * Fonctin moins tolerante que memePion(int,int,int,int)
	 * @param i
	 * @param j
	 * @param ii
	 * @param jj
	 * @return true si les deux cases possede le meme pion, false sinon
	 */
	public boolean verifMemePion(int i, int j, int ii, int jj) {
		return (this.plateau[i][j] == 1 || this.plateau[i][j] == 2)
				&& this.plateau[i][j] == this.plateau[ii][jj];
	}
	
	/**
	 * Verifie si une case est jouable
	 * @param i
	 * @param j
	 * @return true si c'est en dehors, false sinon
	 */
	public boolean isOut(int i, int j){
		if ( j > 1 && j< 19 && i < 11 && i > 1 ) {
			return this.plateau[i][j] == -1;
		}
		else return true;
	}
	
	/**
	 * @param i
	 * @param j
	 * @return true si une case fait parti du jeu, false sinon
	 */
	public boolean isCase(int i, int j){
		return i>=0 && i<=12 && j>=0 && j<=20;
	}
	
	/**
	 * 
	 * @return true si c'est la fin du jeu, false sinon
	 */
	public boolean endGame(){
		//return this.getNbBillesBlanches() < 8 || this.getNbBillesNoirs() < 8;
		return this.getNbBillesBlanches() <= 8 || this.getNbBillesNoirs() <= 8;
	}
	
	/**
	 * Methode utilise pour le MinMax
	 * @param plateau
	 * @return true si c'est lka fin du jeu, false sinon
	 */
	public static boolean endGame( Plateau plateau){
		return plateau.getNbBillesBlanches() <= 8 || plateau.getNbBillesNoirs() <= 8;
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return true si la case est jouable, false sinon
	 */
	
	public boolean caseVide(int i, int j){
		return this.plateau[i][j] == 0;
	}
	
	/**
	 * Methode moins tolérante que caseVide(int,int)
	 * @param i
	 * @param j
	 * @return true si la case est jouable, false sinon
	 */
	
	public boolean verifCaseVide(int i, int j) {
		if ( j > 1 && j < 19 && i < 11 && i > 1)
			return this.plateau[i][j] == 0;
		else
			return false;
	}
	
	public boolean verifAdversaire(int pion, int ivoisin, int jvoisin) {
		if (pion == 1) {
			return this.plateau[ivoisin][jvoisin] == 2;
		} else if (pion == 2) {
			return this.plateau[ivoisin][jvoisin] == 1;
		} else
			return false;
		}
	
	public void reset(){
		this.plateau = this.getPlateauInitial();
		this.nbBillesBlanches = 14;
		this.nbBillesNoirs = 14;
	}
	
	/** GETTERS **/
	
	/**
	 * get le pion avec les coordonnées i,j en parametre
	 * 
	 * @param i
	 * @param j
	 * @return
	 */

	public int getPion(int i, int j) {
		return this.plateau[i][j];
	}

	public int getNbBillesNoirs() {
		return this.nbBillesNoirs;
	}

	public int getNbBillesBlanches() {
		return this.nbBillesBlanches;
	}
	
	public int getCase(int i, int j){
		return this.plateau[i][j];
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return le nombre de voisin de droite, de la case designee 
	 */
	
	public int getVoisinsHorizontaleDroite(int i, int j) {
		int pion = -1;
		boolean plusDeVoisins = false;
		if ( j > 1 && j < 19 && i < 11 && i > 1 )
			pion = this.plateau[i][j];
		else
			plusDeVoisins = true;
		int nbVoisins = 0;
		while (!plusDeVoisins) {
			if (j > 1 && j < 19 && i < 11 && i > 1 && this.plateau[i][j] == pion) {
				nbVoisins++;
				j += 2;
			} else {
				plusDeVoisins = true;
			}
		}
		return nbVoisins;
	}
	
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return le nombre de voisin de gauche, de la case designee 
	 */
	public int getVoisinsHorizontaleGauche(int i, int j) {
		int pion = -1;
		int nbVoisins = 0;
		boolean plusDeVoisins = false;
		if ( j > 1 && j < 19 && i < 11 && i > 1 )
			pion = this.plateau[i][j];
		else
			plusDeVoisins = true;
		while (!plusDeVoisins) {
			if (j > 1 && j < 19 && i < 11 && i > 1 && this.plateau[i][j] == pion) {
				nbVoisins++;
				j -= 2;
			} else {
				plusDeVoisins = true;
			}
		}
		return nbVoisins;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @return le nombre de voisin de diagonal droite/bas, de la case designee 
	 */
	public int getVoisinsDiagonaleDB(int i, int j) { // coef directeur negatif
		int pion = -1;
		int nbVoisins = 0;
		boolean plusDeVoisins = false;
		if ( j > 1 && j < 19 && i < 11 && i > 1 )
			pion = this.plateau[i][j];
		else
			plusDeVoisins = true;
		while (!plusDeVoisins) {
			if (j > 1 && j < 19 && i < 11 && i > 1 && this.plateau[i][j] == pion) {
				nbVoisins++;
				j += 1;
				i += 1;
			} else {
				plusDeVoisins = true;
			}
		}
		return nbVoisins;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @return le nombre de voisin diagonal de droite/gauche, de la case designee 
	 */
	public int getVoisinsDiagonaleGH(int i, int j) { // coef directeur negatif
		int pion = -1;
		int nbVoisins = 0;
		boolean plusDeVoisins = false;
		if ( j > 1 && j < 19 && i < 11 && i > 1 )
			pion = this.plateau[i][j];
		else
			plusDeVoisins = true;
		while (!plusDeVoisins) {
			if (j > 1 && j < 19 && i < 11 && i > 1 && this.plateau[i][j] == pion) {
				nbVoisins++;
				j -= 1;
				i -= 1;
			} else {
				plusDeVoisins = true;
			}
		}
		return nbVoisins;
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return le nombre de voisin diagonal de droite/haut, de la case designee 
	 */
	public int getVoisinsDiagonaleDH(int i, int j) { // coef directeur positif
		int pion = this.plateau[i][j];
		int nbVoisins = 0;
		boolean plusDeVoisins = false;
		while (!plusDeVoisins) {
			if (j > 1 && j < 19 && i < 11 && i > 1 && this.plateau[i][j] == pion) {
				nbVoisins++;
				i -= 1;
				j += 1;
			} else {
				plusDeVoisins = true;
			}
		}
		return nbVoisins;
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @return le nombre de voisin diagonal de gauche/bas, de la case designee 
	 */
	public int getVoisinsDiagonaleGB(int i, int j) { // coef directeur positif
		int pion = this.plateau[i][j];
		int nbVoisins = 0;
		boolean plusDeVoisins = false;
		while (!plusDeVoisins) {
			if (j > 1 && j < 19 && i < 11 && i > 1 && this.plateau[i][j] == pion) {
				nbVoisins++;
				i += 1;
				j -= 1;
			} else {
				plusDeVoisins = true;
			}
		}
		return nbVoisins;
	}
	
	/**
	 * 
	 * @return le plateau du jeu
	 */
	public int[][] getPlateau(){
		return this.plateau;
	}
	
	


}