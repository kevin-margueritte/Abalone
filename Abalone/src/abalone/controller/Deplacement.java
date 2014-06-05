package abalone.controller;

import java.io.Serializable;
import java.util.ArrayList;
import abalone.model.Plateau;

/**
 * Classe permettant d'effectuer des deplacements sur le model
 * @author groupe projer
 * @mail kevin.margueritte@gmail.com
 * 
 * Remarque : Certaines methodes de la classe peuvent être facilement améliorés 
 *
 */

@SuppressWarnings("serial")
public class Deplacement implements Serializable{
	private Plateau grille;
	private Refresh refresh;
	private boolean sav;
	
	/**
	 * Fonction qui permet de gerer les deplacements dans le model 
	 * @param plateau
	 */
	
	public Deplacement( Plateau plateau, Refresh refresh ){
		this.grille = plateau; 
		this.refresh =  refresh;
		sav = true;
	}
	
	public Deplacement( Plateau plateau ){
		this.grille = plateau; 
		sav = false;
	}
	
	/**Deplacement**/
	
	/**
	 * Methode qui permet savoir si un deplacement est possible
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @return true si le deplacement est possible
	 */
	
	private boolean deplacementSimplePossible(int iInit, int jInit, int iFin, int jFin) {
		boolean possible = false;
		if ( this.grille.getPion(iInit, jInit) == 1 || this.grille.getPion(iInit, jInit) == 2) {
			if ((iFin >= iInit - 1) || (iFin <= iInit + 1)) {
				if ((jFin == jInit - 2) || (jFin == jInit + 2)
						|| (jFin == jInit - 1) || (jFin == jInit + 1)) {
					if (this.grille.getPion(iFin, jFin)== 0) { 
						possible = true;
					} 
				} 
			} 
		} 
		return possible;
	}
	
	/**
	 * Cette fonction a un but ergonomique, elle facilite le cliquage, c'est a dire si on veut deplacer un pion de maniere horizontal
     * nous ne sommes pas oblige de cliquer sur la bonne case, on peut cliquer sur la case d'a cote si et seulement le clique se
     * fait sur un endroit de la droite reliant la position du point de depart et la position valide
     * Ex : 012345678901234567890
     * 		#####################0
            #####################1
            ######1 1 1 1 1######2
            #####1 1 1 1 1 1#####3
            ####0 0 1 0 1 0 0####4
            ###0 0 0 0 0 0 0 0###5
            ##0 0 0 0 1 0 0 0 0##6
            ###0 0 0 0 0 0 0 0###7
            ####0 0 2 2 2 0 0####8
            #####2 2 2 2 2 2#####9
            ######2 2 2 2 2######10
            #####################11
            #####################12
     *      Je veux deplacer mon point en 10,6 sur 10,8, je peux alors cliquer 10,14 ma fonction me retournera un tableau de taille 2
     *comportant un couple ( i,j ) avec cette exemple ma fontion va retourner un tableau avec 10,8
     *
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @return les bonnes coprdonnees dans un tableau a deux dimensions
	 */
	private int[] deplacementFacile(int iInit, int jInit, int iFin, int jFin) { // ne pas oublie le cas on clique sur une case -1
		int newIndices[] = new int[2]; // couples i,j
		if (iInit == iFin) { // si c'est un deplacement d'un piont
								// horizontalment
			newIndices[0] = iFin;
			if (jInit > jFin) { // cas deplacement horizontal gauche
				newIndices[1] = jInit - 2;
			} else {
				newIndices[1] = jInit + 2;// cas deplacement horizontal droit
			}
		} else if (jInit > jFin) { // deplacement diagonal gauche
			if (iInit - iFin == jInit - jFin) { // deplacement diagonal
														// gauche/haut
				newIndices[0] = iInit - 1;
				newIndices[1] = jInit - 1;
			} else if (iInit - iFin == jFin - jInit) { // deplacement
																// diagonal
																// gauche/bas
				newIndices[0] = iInit + 1;
				newIndices[1] = jInit - 1;
			} else { // est un mauvais deplacement
				newIndices[0] = 0;
				newIndices[1] = 0;
			}
		} else if (jInit < jFin) { // deplacement diagonal droite
			if (iInit - iFin == jFin - jInit) { // deplacement diagonal
														// droite/haut
				newIndices[0] = iInit - 1;
				newIndices[1] = jInit + 1;
			} else if (iInit - iFin == jInit - jFin) { // deplacement
																// diagonal
																// droite/bas
				newIndices[0] = iInit + 1;
				newIndices[1] = jInit + 1;
			} else { // est un mauvais deplacement
				newIndices[0] = 0;
				newIndices[1] = 0;
			}
		} else {
			// est un mauvais deplacement
			newIndices[0] = 0;
			newIndices[1] = 0;
		}
		return newIndices;
	}
	
	/**
	 * Cette fonction retourne un historique de deplacement;
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @return un List<Integer> avec l'historique des deplacements
	 */
	public ArrayList<Integer> deplacementFacileAux(int iInit, int jInit, int iFin, int jFin) { // ne pas oublie le cas on clique sur une case -1
		ArrayList<Integer> newIndices = new ArrayList<Integer>();
		if (iInit == iFin) { // si c'est un deplacement d'un piont horizontalment 
			if (jInit > jFin) { // cas deplacement horizontal gauche
				if (this.grille.getPion(iInit,jInit - 2) != this.grille.getPion(iInit,jInit)) { // 1fois
					newIndices.add(iFin);
					newIndices.add(jInit - 2);
				}
				if (this.grille.getPion(iInit,jInit) == this.grille.getPion(iInit, jInit - 2)
						&& this.grille.getPion(iInit,jInit - 4) != this.grille.getPion(iInit,jInit - 2)) {
					newIndices.add(iFin);
					newIndices.add(jInit - 2);
					newIndices.add(iFin);
					newIndices.add(jInit - 4);
				}
				if (this.grille.getPion(iInit,jInit) == this.grille.getPion(iInit,jInit - 2)
						&& this.grille.getPion(iInit,jInit - 4) == this.grille.getPion(iInit,jInit - 2)
						&& this.grille.getPion(iInit,jInit - 6) != this.grille.getPion(iInit,jInit - 4)) {
					newIndices.add(iFin);
					newIndices.add(jInit - 2);
					newIndices.add(iFin);
					newIndices.add(jInit - 4);
					newIndices.add(iFin);
					newIndices.add(jInit - 6);
				}
			} else {
				if (this.grille.getPion(iInit,jInit) != this.grille.getPion(iInit,jInit + 2)) {
					newIndices.add(iFin);
					newIndices.add(jInit + 2);
				}
				if (this.grille.getPion(iInit,jInit) == this.grille.getPion(iInit,jInit + 2)
						&& this.grille.getPion(iInit,jInit + 4) != this.grille.getPion(iInit,jInit + 2)) {
					newIndices.add(iFin);
					newIndices.add(jInit + 2);
					newIndices.add(iFin);
					newIndices.add(jInit + 4);
				}
				if (this.grille.getPion(iInit,jInit) == this.grille.getPion(iInit, jInit + 2)
						&& this.grille.getPion(iInit,jInit + 4) == this.grille.getPion(iInit,jInit + 2)
						&& this.grille.getPion(iInit,jInit + 4) != this.grille.getPion(iInit,jInit + 6)) {
					newIndices.add(iFin);
					newIndices.add(jInit + 2);
					newIndices.add(iFin);
					newIndices.add(jInit + 4);
					newIndices.add(iFin);
					newIndices.add(jInit + 6);
				}
			}
		} else if (jInit > jFin) { // deplacement diagonal gauche
			if (iInit - iFin == jInit - jFin) { // deplacement diagonal
														// gauche/haut
				if (this.grille.getPion(iInit,jInit) != this.grille.getPion(iInit - 1,jInit - 1)) {
					newIndices.add(iInit - 1);
					newIndices.add(jInit - 1);
				}
				if (this.grille.getPion(iInit,jInit) == this.grille.getPion(iInit - 1,jInit - 1)
						&& this.grille.getPion(iInit - 2,jInit - 2) != this.grille.getPion(iInit - 1,jInit - 1)) {
					newIndices.add(iInit - 1);
					newIndices.add(jInit - 1);
					newIndices.add(iInit - 2);
					newIndices.add(jInit - 2);
				}
				if (this.grille.getPion(iInit,jInit) == this.grille.getPion(iInit - 1,jInit - 1)
						&& this.grille.getPion(iInit - 2, jInit - 2) == this.grille.getPion(iInit - 1,jInit - 1)
						&& this.grille.getPion(iInit - 2, jInit - 2) != this.grille.getPion(iInit - 3,jInit - 3)) {
					newIndices.add(iInit - 1);
					newIndices.add(jInit - 1);
					newIndices.add(iInit - 2);
					newIndices.add(jInit - 2);
					newIndices.add(iInit - 3);
					newIndices.add(jInit - 3);
				}
			} else if (iInit - iFin == jFin - jInit) { // deplacement
																// diagonal
																// gauche/bas
				if (this.grille.getPion(iInit,jInit) != this.grille.getPion(iInit + 1,jInit - 1)) {
					newIndices.add(iInit + 1);
					newIndices.add(jInit - 1);
				}
				if (this.grille.getPion(iInit,jInit) == this.grille.getPion(iInit + 1,jInit - 1)
						&& this.grille.getPion(iInit + 2,jInit - 2) != this.grille.getPion(iInit + 1,jInit - 1)) {
					newIndices.add(iInit + 1);
					newIndices.add(jInit - 1);
					newIndices.add(iInit + 2);
					newIndices.add(jInit - 2);
				}
				if (this.grille.getPion(iInit,jInit) == this.grille.getPion(iInit + 1,jInit - 1)
						&& this.grille.getPion(iInit + 2,jInit - 2) == this.grille.getPion(iInit + 1,jInit - 1)
						&& this.grille.getPion(iInit + 2,jInit - 2) != this.grille.getPion(iInit + 3,jInit - 3)) {
					newIndices.add(iInit + 1);
					newIndices.add(jInit - 1);
					newIndices.add(iInit + 2);
					newIndices.add(jInit - 2);
					newIndices.add(iInit + 3);
					newIndices.add(jInit - 3);
				}
			}
		} else if (jInit < jFin) { // deplacement diagonal droite
			if (iInit - iFin == jFin - jInit) { // deplacement diagonal
														// droite/haut
				if (this.grille.getPion(iInit,jInit) != this.grille.getPion(iInit - 1,jInit + 1)) {
					newIndices.add(iInit - 1);
					newIndices.add(jInit + 1);
				}
				if (this.grille.getPion(iInit,jInit) == this.grille.getPion(iInit - 1,jInit + 1)
						&& this.grille.getPion(iInit - 2,jInit + 2) != this.grille.getPion(iInit - 1,jInit + 1)) {
					newIndices.add(iInit - 1);
					newIndices.add(jInit + 1);
					newIndices.add(iInit - 2);
					newIndices.add(jInit + 2);
				}
				if (this.grille.getPion(iInit,jInit) == this.grille.getPion(iInit - 1,jInit + 1)
						&& this.grille.getPion(iInit - 2,jInit + 2) == this.grille.getPion(iInit - 1,jInit + 1)
						&& this.grille.getPion(iInit - 2,jInit + 2) != this.grille.getPion(iInit - 3,jInit + 3)) {
					newIndices.add(iInit - 1);
					newIndices.add(jInit + 1);
					newIndices.add(iInit - 2);
					newIndices.add(jInit + 2);
					newIndices.add(iInit - 3);
					newIndices.add(jInit + 3);
				}
			}
			if (iInit - iFin == jInit - jFin) { // deplacement diagonal
														// droite/bas
				if (this.grille.getPion(iInit,jInit) != this.grille.getPion(iInit + 1,jInit + 1)) {
					newIndices.add(iInit + 1);
					newIndices.add(jInit + 1);
				}
				if (this.grille.getPion(iInit,jInit) == this.grille.getPion(iInit + 1,jInit + 1)
						&& this.grille.getPion(iInit + 2,jInit + 2) != this.grille.getPion(iInit + 1,jInit + 1)) {
					newIndices.add(iInit + 1);
					newIndices.add(jInit + 1);
					newIndices.add(iInit + 2);
					newIndices.add(jInit + 2);
				}
				if (this.grille.getPion(iInit,jInit) == this.grille.getPion(iInit + 1, jInit + 1)
						&& this.grille.getPion(iInit + 2,jInit + 2) == this.grille.getPion(iInit + 1,jInit + 1)
						&& this.grille.getPion(iInit + 2,jInit + 2) != this.grille.getPion(iInit + 3,jInit + 3)) {
					newIndices.add(iInit + 1);
					newIndices.add(jInit + 1);
					newIndices.add(iInit + 2);
					newIndices.add(jInit + 2);
					newIndices.add(iInit + 3);
					newIndices.add(jInit + 3);
				}
			}
		}
		return newIndices;
	}
	
	/**
	 * Execute les deplacements
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @see int[][] deplacermentFacile(int,int,int,int)
	 * @return true si le deplacement voulu est un succes
	 */
	
	public boolean deplacement(int iInit, int jInit, int iFin, int jFin) { 
		int indicesDeplacement[] = this.deplacementFacile(iInit, jInit, iFin, jFin);
		if (this.deplacementSimplePossible(iInit, jInit, indicesDeplacement[0], indicesDeplacement[1])) {
			this.grille.setCase(indicesDeplacement[0],indicesDeplacement[1],this.grille.getPion(iInit, jInit));
			if ( this.sav)
				this.refresh.add(iInit,jInit,indicesDeplacement[0],indicesDeplacement[1]);
			this.grille.setCase(iInit,jInit, 0 );
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Fonction maitresse des deplacements, execute le deplacement passe en parametre et fait tout les verifications nééssaires
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @param nbDeplacement : le nombre de deplacements qui ont deja ete effectues
	 * @return le nombre de deplacement effectue
	 */
	
	public int deplacementLineaire(int iInit, int jInit, int iFin,
			int jFin, int nbDeplacement) {
		int deplacement = 0;
		if (this.verifDeplacementHorizontalValide(iInit, jInit, iFin,jFin)) {
			if (this.verifDeplacementHorizontalValideDroite(iInit, jInit, iFin, jFin)) {
				if (this.grille.verifMemePion(iInit, jInit, iFin, jInit + 2) && this.grille.verifCaseVide(iInit, jInit + 4)) {
					if (nbDeplacement + 2 <= 3) {
						this.deplacement(iInit, jInit + 2, iFin, jInit + 4);
						this.deplacement(iInit, jInit, iFin, jInit + 2);
						deplacement = 2;
					}
				} else if (this.grille.verifMemePion(iInit, jInit, iFin, jInit + 2) && this.grille.verifAdversaire( this.grille.getPion(iInit,jInit), iInit, jInit + 4)) {
					deplacement = this.sumitoHorizontaleD(iInit, jInit,iInit, jInit + 4);
				} else if (this.grille.verifMemePion(iInit, jInit, iFin,jInit + 2) 
						&& this.grille.verifMemePion(iInit, jInit, iFin, jInit + 4) && this.grille.verifCaseVide(iInit, jInit + 6)) {
					if (nbDeplacement + 3 <= 3) {
						this.deplacement(iInit, jInit + 4, iFin, jInit + 6);
						this.deplacement(iInit, jInit + 2, iFin, jInit + 4);
						this.deplacement(iInit, jInit + 0, iFin, jInit + 2);
						deplacement = 3;
					}
				} else if (this.grille.verifMemePion(iInit, jInit, iFin, jInit + 2) 
						&& this.grille.verifMemePion(iInit, jInit, iFin,jInit + 4)
						&& this.grille.verifAdversaire( this.grille.getPion(iInit,jInit), iInit, jInit + 6)) {
					deplacement = this.sumitoHorizontaleD(iInit, jInit,iInit, jInit + 6);
				} else if (this.grille.verifCaseVide(iInit, jInit + 2)) {
					this.deplacement(iInit, jInit, iFin, jInit + 1);
					deplacement = 1;
				}
			} else {
				if (this.grille.verifMemePion(iInit, jInit, iFin, jInit - 2) && this.grille.verifCaseVide(iInit, jInit - 4)) {
					if (nbDeplacement + 2 <= 3) {
						this.deplacement(iInit, jInit - 2, iFin, jInit - 4);
						this.deplacement(iInit, jInit, iFin, jInit - 2);
						deplacement = 2;
					}
				} else if (this.grille.verifMemePion(iInit, jInit, iFin, jInit - 2)
						&& this.grille.verifAdversaire(this.grille.getPion(iInit,jInit), iInit,jInit - 4)) {
					deplacement = this.sumitoHorizontaleG(iInit, jInit,iInit, jInit - 4);
				} else if (this.grille.verifMemePion(iInit, jInit, iFin, jInit - 2)
						&& this.grille.verifMemePion(iInit, jInit, iFin, jInit - 4)
						&& this.grille.verifCaseVide(iInit, jInit - 6)) {
					if (nbDeplacement + 3 <= 3) {
						this.deplacement(iInit, jInit - 4, iFin,jInit - 6);
						this.deplacement(iInit, jInit - 2, iFin,jInit - 4);
						this.deplacement(iInit, jInit - 0, iFin,jInit - 2);
						deplacement = 3;
					}
				} else if (this.grille.verifMemePion(iInit, jInit, iFin, jInit - 2)
						&& this.grille.verifMemePion(iInit, jInit, iFin,jInit - 4)
						&& this.grille.verifAdversaire(this.grille.getPion(iInit,jInit), iInit,jInit - 6)) {
					deplacement = this.sumitoHorizontaleG(iInit, jInit, iInit, jInit - 6);
				} else if (this.grille.verifCaseVide(iInit, jInit - 2)) {
					this.deplacement(iInit, jInit, iFin, jInit - 2);
					deplacement = 1;
				}
			}
		} else if (this.verifDeplacementDiagonalValide(iInit, jInit, iFin, jFin)) {
			if (this.verifDeplacementDiagonalValideDB(iInit, jInit, iFin,jFin)) {
				if (this.grille.verifMemePion(iInit, jInit, iInit + 1, jInit + 1)
						&& this.grille.verifCaseVide(iInit + 2, jInit + 2)) {
					if (nbDeplacement + 2 <= 3) {
						this.deplacement(iInit + 1, jInit + 1,iInit + 2, jInit + 2);
						this.deplacement(iInit, jInit, iInit + 1, jInit + 1);
						deplacement = 2;
					} 
				} else if (this.grille.verifMemePion(iInit, jInit, iInit + 1, jInit + 1)
						&& this.grille.verifAdversaire(this.grille.getPion(iInit, jInit), iInit + 2, jInit + 2)) {
					deplacement = this.sumitoDiagonalDB(iInit, jInit,iInit + 2, jInit + 2);
				} else if (this.grille.verifMemePion(iInit, jInit, iInit + 1, jInit + 1)
						&& this.grille.verifMemePion(iInit + 1, jInit + 1, iInit + 2, jInit + 2)
						&& this.grille.verifCaseVide(iInit + 3, jInit + 3)) {
					if (nbDeplacement + 3 <= 3) {
						this.deplacement(iInit + 2, jInit + 2,iInit + 3, jInit + 3);
						this.deplacement(iInit + 1, jInit + 1, iInit + 2, jInit + 2);
						this.deplacement(iInit, jInit, iInit + 1, jInit + 1);
						deplacement = 3;
					}
				} else if (this.grille.verifMemePion(iInit, jInit, iInit + 1, jInit + 1)
						&& this.grille.verifMemePion(iInit + 1, jInit + 1, iInit + 2, jInit + 2)
						&& this.grille.verifAdversaire(this.grille.getPion(iInit,jInit), iInit + 3, jInit + 3)) {
					deplacement = this.sumitoDiagonalDB(iInit, jInit, iInit + 3, jInit + 3);
				} else if (this.grille.verifCaseVide(iInit + 1, jInit + 1)) {
					this.deplacement(iInit, jInit, iInit + 1, jInit + 1);
					deplacement = 1;
				}
			} else if (this.verifDeplacementDiagonalValideDH(iInit, jInit, iFin, jFin)) {
				if (this.grille.verifMemePion(iInit, jInit, iInit - 1, jInit + 1)
						&& this.grille.verifCaseVide(iInit - 2, jInit + 2)) {
					if (nbDeplacement + 2 <= 3) {
						this.deplacement(iInit - 1, jInit + 1, iInit - 2, jInit + 2);
						this.deplacement(iInit, jInit, iInit - 1, jInit + 1);
						deplacement = 2;
					}
				} else if (this.grille.verifMemePion(iInit, jInit, iInit - 1, jInit + 1)
						&& this.grille.verifAdversaire(this.grille.getPion(iInit,jInit), iInit - 2, jInit + 2)) {
					return this.sumitoDiagonalDH(iInit, jInit, iInit - 2, jInit + 2);
				} else if (this.grille.verifMemePion(iInit, jInit, iInit - 1, jInit + 1)
						&& this.grille.verifMemePion(iInit - 1, jInit + 1, iInit - 2, jInit + 2)
						&& this.grille.verifCaseVide(iInit - 3, jInit + 3)) {
					if (nbDeplacement + 3 <= 3) {
						this.deplacement(iInit - 2, jInit + 2,iInit - 3, jInit + 3);
						this.deplacement(iInit - 1, jInit + 1,iInit - 2, jInit + 2);
						this.deplacement(iInit, jInit, iInit - 1,jInit + 1);
						deplacement = 3;
					}
				} else if (this.grille.verifMemePion(iInit, jInit, iInit - 1,jInit + 1)
						&& this.grille.verifMemePion(iInit - 1, jInit + 1,iInit - 2, jInit + 2)
						&& this.grille.verifAdversaire(this.grille.getPion(iInit, jInit), iInit - 3,jInit + 3)) {
					deplacement = this.sumitoDiagonalDH(iInit, jInit, iInit - 3, jInit + 3);
				} else if (this.grille.verifCaseVide(iInit - 1, jInit + 1)) {
					this.deplacement(iInit, jInit, iInit - 1, jInit + 1);
					deplacement = 1;
				}
			} else if (this.verifDeplacementDiagonalValideGH(iInit, jInit, iFin, jFin)) {
				if (this.grille.verifMemePion(iInit, jInit, iInit - 1, jInit - 1)
						&& this.grille.verifCaseVide(iInit - 2, jInit - 2)) {
					if (nbDeplacement + 2 <= 3) {
						this.deplacement(iInit - 1, jInit - 1, iInit - 2, jInit - 2);
						this.deplacement(iInit, jInit, iInit - 1, jInit - 1);
						deplacement = 2;
					}
				} else if (this.grille.verifMemePion(iInit, jInit, iInit - 1, jInit - 1)
						&& this.grille.verifAdversaire(this.grille.getPion(iInit, jInit), iInit - 2,jInit - 2)) {
					deplacement = this.sumitoDiagonalGH(iInit, jInit, iInit - 2, jInit - 2);
				} else if (this.grille.verifMemePion(iInit, jInit, iInit - 1, jInit - 1)
						&& this.grille.verifMemePion(iInit - 1, jInit - 1,iInit - 2, jInit - 2)
						&& this.grille.verifCaseVide(iInit - 3, jInit - 3)) {
					if (nbDeplacement + 3 <= 3) {
						this.deplacement(iInit - 2, jInit - 2,iInit - 3, jInit - 3);
						this.deplacement(iInit - 1, jInit - 1,iInit - 2, jInit - 2);
						this.deplacement(iInit, jInit, iInit - 1,jInit - 1);
						deplacement = 3;
					}
				} else if (this.grille.verifMemePion(iInit, jInit, iInit - 1,jInit - 1)
						&& this.grille.verifMemePion(iInit - 1, jInit - 1,iInit - 2, jInit - 2)
						&& this.grille.verifAdversaire(this.grille.getPion(iInit, jInit), iInit - 3,jInit - 3)) {
					deplacement = this.sumitoDiagonalGH(iInit, jInit,iInit - 3, jInit - 3);
				} else if (this.grille.verifCaseVide(iInit - 1, jInit - 1)) {
					this.deplacement(iInit, jInit, iInit - 1, jInit - 1);
					deplacement = 1;
				}
			} else {
				if (this.grille.verifMemePion(iInit, jInit, iInit + 1,jInit - 1)
						&& this.grille.verifCaseVide(iInit + 2, jInit - 2)) {
					if (nbDeplacement + 2 <= 3) {
						this.deplacement(iInit + 1, jInit - 1,iInit + 2, jInit - 2);
						this.deplacement(iInit, jInit, iInit + 1,jInit - 1);
						deplacement = 2;
					}
				} else if (this.grille.verifMemePion(iInit, jInit, iInit + 1,jInit - 1)
						&& this.grille.verifAdversaire(this.grille.getPion(iInit, jInit), iInit + 2,jInit - 2)) {
					return this.sumitoDiagonalGB(iInit, jInit,iInit + 2, jInit - 2);
				} else if (this.grille.verifMemePion(iInit, jInit, iInit + 1,jInit - 1)
						&& this.grille.verifMemePion(iInit + 1, jInit - 1,iInit + 2, jInit - 2)
						&& this.grille.verifCaseVide(iInit + 3, jInit - 3)) {
					if (nbDeplacement + 3 <= 3) {
						this.deplacement(iInit + 2, jInit - 2,iInit + 3, jInit - 3);
						this.deplacement(iInit + 1, jInit - 1,iInit + 2, jInit - 2);
						this.deplacement(iInit, jInit, iInit + 1,jInit - 1);
						deplacement = 3;
					}
				} else if (this.grille.verifMemePion(iInit, jInit, iInit + 1,jInit - 1)
						&& this.grille.verifMemePion(iInit + 1, jInit - 1,iInit + 2, jInit - 2)
						&& this.grille.verifAdversaire(this.grille.getPion(iInit,jInit), iInit + 3,jInit - 3)) {
					deplacement = this.sumitoDiagonalGB(iInit, jInit,iInit + 3, jInit - 3);
				} else if (this.grille.verifCaseVide(iInit + 1, jInit - 1)) {
					this.deplacement(iInit, jInit, iInit + 1,jInit - 1);
					deplacement = 1;
				}
			}
		}
		return deplacement;
	}
	
	private boolean ejectPion(int iInit, int jInit, int iFin, int jFin) {
		if (this.grille.getPion(iFin, jFin) == -1) {
			if (this.grille.getPion(iInit, jInit) == 1) {
				this.grille.decrementeBilleBlanche();
			} else {
				this.grille.decrementeBilleNoir();
			}
			if (sav){
				System.out.println(iInit +  " " + jInit + " " + iFin + " " + jFin);
				this.refresh.detruit(Coordonnee.getCoordonne(iInit, jInit),TypeBille.getBille(this.grille.getPion(iInit, jInit)));				
			this.grille.setCase(iInit, jInit, 0 );
			}
			return true;
		} else {
			this.deplacement(iInit, jInit, iFin, jFin);
			return false;
		}
	}
	
	/**
	 * Effectue un sumito du cote horizontal et vers la droite
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jnew
	 * @return le nombre de deplacements efectues
	 */
	
	public int sumitoHorizontaleD(int iInit, int jInit, int iFin, int jFin) {
		int sumito = 0;
		if (jInit < jFin) { // sumito droit
			int nbPousseur = this.grille.getVoisinsHorizontaleDroite(iInit, jInit);
			int nbPousser = this.grille.getVoisinsHorizontaleDroite(iFin, jFin);
			if (nbPousseur > nbPousser && nbPousser < 3) {
				int jdeplace = jFin + 2 * nbPousser;
				for (int ind = 0; ind != nbPousseur + nbPousser; ind++) {
					if (ind == 0) {
						this.ejectPion(iInit, jdeplace - 2, iInit, jdeplace);
					} else {
						this.deplacement(iInit, jdeplace - 2, iInit, jdeplace);
					}
					jdeplace -= 2;
				}
				sumito = nbPousseur;
			}
		} 
		return sumito;
	}
	
	/**
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @return true si le sumito vers la droite est possible
	 */
	
	public boolean sumitoHorizontaleDPossible(int iInit, int jInit, int iFin, int jFin) {
		boolean possible = false;
		if ( iInit == iFin && jInit < jFin ){
			if ( this.grille.memePion(iInit, jInit, iInit, jInit + 2) ){ //2meme pions cote a cote
				if ( this.grille.caseAdvairse(iInit, jInit, iInit, jInit + 4 ) && jFin == jInit + 6 ) // 2 VS 1
					possible = true;
				else if ( this.grille.memePion(iInit, jInit, iInit, jInit + 4 )){ //3meme pion cote a cote
					if ( this.grille.caseAdvairse(iInit, jInit, iInit, jInit + 6) ){ //il y a au moins 1 pion advairse ensuite
						if ( jFin == jInit + 8) possible = true; // 3 VS 1
						else if ( this.grille.caseAdvairse(iInit, jInit, iInit, jInit + 8) && jFin == jInit + 10) possible = true; //3 VS 2
					}
				}
			}
		}
		return possible;
	}
	
	/**
	 * Effectue un sumito du cote horizontal et vers la gauche
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jnew
	 * @return le nombre de deplacements efectues 
	 */

	public int sumitoHorizontaleG(int i, int j, int inew, int jnew) {
		int sumito = 0;
		if (j > jnew) { // sumito gauche
			int nbPousseur = this.grille.getVoisinsHorizontaleGauche(i, j);
			int nbPousser = this.grille.getVoisinsHorizontaleGauche(inew, jnew);
			if (nbPousseur > nbPousser && nbPousser < 3) {
				int jdeplace = jnew - 2 * nbPousser;
				for (int ind = 0; ind != nbPousseur + nbPousser; ind++) {
					if (ind == 0) {
						this.ejectPion(i, jdeplace + 2, i, jdeplace);
					} else {
						this.deplacement(i, jdeplace + 2, i, jdeplace);
					}
					jdeplace += 2;
				}
				sumito = nbPousseur;
			}
		} 
		return sumito;
	}
	
	/**
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @return true si le sumito vers la gauche est possible
	 */
	public boolean sumitoHorizontaleGPossible(int iInit, int jInit, int iFin, int jFin) {
		boolean possible = false;
		if ( iInit == iFin && jInit > jFin ){
			if ( this.grille.memePion(iInit, jInit, iInit, jInit - 2) ){ //2meme pions cote a cote
				if ( this.grille.caseAdvairse(iInit, jInit, iInit, jInit - 4 ) && jFin == jInit - 6 ) // 2 VS 1
					possible = true;
				else if ( this.grille.memePion(iInit, jInit, iInit, jInit - 4 )){ //3meme pion cote a cote
					if ( this.grille.caseAdvairse(iInit, jInit, iInit, jInit - 6) ){ //il y a au moins 1 pion advairse ensuite
						if ( jFin == jInit - 8) possible = true; // 3 VS 1
						else if ( this.grille.caseAdvairse(iInit, jInit, iInit, jInit - 8) && jFin == jInit - 10) possible = true; //3 VS 2
					}
				}
			}
		}
		return possible;
	}
	
	/**
	 * Effectue un sumito diagonal droite/bas
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jnew
	 * @return le nombre de deplacements effectues 
	 */
	
	public int sumitoDiagonalDB(int i, int j, int inew, int jnew) {
		int sumito = 0;
		if (j < jnew && i < inew) { // sumito Diagonale neg haut
			int nbPousseur = this.grille.getVoisinsDiagonaleDB(i, j);
			int nbPousser = this.grille.getVoisinsDiagonaleDB(inew, jnew);
			if (nbPousseur > nbPousser && nbPousser < 3) {
				int jdeplace = jnew + nbPousser;
				int ideplace = inew + nbPousser;
				for (int ind = 0; ind != nbPousseur + nbPousser; ind++) {
					if (ind == 0) {
						this.ejectPion(ideplace - 1, jdeplace - 1, ideplace,
								jdeplace);
					} else {
						this.deplacement(ideplace - 1, jdeplace - 1, ideplace,
								jdeplace);
					}
					jdeplace -= 1;
					ideplace -= 1;
				}
				sumito = nbPousseur;
			}
		}
		return sumito;
	}
	
	/**
	 * Methode utilise avant d'execute un sumito
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @return si le sumito diagonal droite/bas est possible
	 */
	public boolean sumitoDiagonalDBPossible(int iInit, int jInit, int iFin, int jFin) {
		boolean possible = false;
		if ( iInit < iFin && jInit < jFin ){
			if ( this.grille.memePion(iInit, jInit, iInit + 1, jInit + 1) ){ //2meme pions cote a cote
				if ( this.grille.caseAdvairse(iInit, jInit, iInit + 2, jInit + 2 ) && jFin == jInit + 3 && iFin == iInit + 3 ) // 2 VS 1
					possible = true;
				else if ( this.grille.memePion(iInit, jInit, iInit + 2, jInit + 2 )){ //3meme pion cote a cote
					if ( this.grille.caseAdvairse(iInit, jInit, iInit + 3, jInit + 3) ){ //il y a au moins 1 pion advairse ensuite
						if ( jFin == jInit + 4 && iFin == iInit + 4) possible = true; // 3 VS 1
						else if ( this.grille.caseAdvairse(iInit, jInit, iInit + 4, jInit + 4) && jFin == jInit + 5 && iFin == iInit + 5) possible = true; //3 VS 2
					}
				}
			}
		}
		return possible;
	}
	
	
	/**
	 * Effectue un sumito diagonal gauche/haut
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jnew
	 * @return true si le nombre de deplacements effectues 
	 */
	public int sumitoDiagonalGH(int i, int j, int inew, int jnew) {
		if (j > jnew && i > inew) { // sumito diagonal neg basse
			int nbPousseur = this.grille.getVoisinsDiagonaleGH(i, j);
			int nbPousser = this.grille.getVoisinsDiagonaleGH(inew, jnew);
			if (nbPousseur > nbPousser && nbPousser < 3) {
				int jdeplace = jnew - nbPousser;
				int ideplace = inew - nbPousser;
				for (int ind = 0; ind != nbPousseur + nbPousser; ind++) {
					if (ind == 0) {
						this.ejectPion(ideplace + 1, jdeplace + 1, ideplace,
								jdeplace);
					} else {
						this.deplacement(ideplace + 1, jdeplace + 1, ideplace,
								jdeplace);
					}
					jdeplace += 1;
					ideplace += 1;
				}
				return nbPousseur;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	/**
	 * Methode avant d'execute un sumito
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @return true si le sumito diagonal gauche/haut est possible
	 */
	public boolean sumitoDiagonalGHPossible(int iInit, int jInit, int iFin, int jFin) {
		boolean possible = false;
		if ( iInit > iFin && jInit > jFin ){
			if ( this.grille.memePion(iInit, jInit, iInit - 1, jInit - 1) ){ //2meme pions cote a cote
				if ( this.grille.caseAdvairse(iInit, jInit, iInit - 2, jInit - 2 ) && jFin == jInit - 3 && iFin == iInit - 3 ) // 2 VS 1
					possible = true;
				else if ( this.grille.memePion(iInit, jInit, iInit - 2, jInit - 2 )){ //3meme pion cote a cote
					if ( this.grille.caseAdvairse(iInit, jInit, iInit - 3, jInit - 3) ){ //il y a au moins 1 pion advairse ensuite
						if ( jFin == jInit - 4 && iFin == iInit - 4) possible = true; // 3 VS 1
						else if ( this.grille.caseAdvairse(iInit, jInit, iInit - 4, jInit - 4) && jFin == jInit - 5 && iFin == iInit - 5) possible = true; //3 VS 2
					}
				}
			}
		}
		return possible;
	}
	
	/**
	 * Effectue un sumito diagonal droite/haut
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jnew
	 * @return true si le nombre de deplacements effectues 
	 */
	public int sumitoDiagonalDH(int i, int j, int inew, int jnew) {
		int sumito = 0;
		if (j < jnew && i > inew) { // sumito Diagonale neg haut
			int nbPousseur = this.grille.getVoisinsDiagonaleDH(i, j);
			int nbPousser = this.grille.getVoisinsDiagonaleDH(inew, jnew);
			if (nbPousseur > nbPousser && nbPousser < 3) {
				int jdeplace = jnew + nbPousser;
				int ideplace = inew - nbPousser;
				for (int ind = 0; ind != nbPousseur + nbPousser; ind++) {
					if (ind == 0) {
						this.ejectPion(ideplace + 1, jdeplace - 1, ideplace,
								jdeplace);
					} else {
						this.deplacement(ideplace + 1, jdeplace - 1, ideplace,
								jdeplace);
					}
					jdeplace -= 1;
					ideplace += 1;
				}
				sumito = nbPousseur;
			}
		}
		return sumito;
	}
	
	/**
	 * Methode utilise avant d'execute un sumito
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @return true si le sumito diagonal droite/haut est possible
	 */
	public boolean sumitoDiagonalDHPossible(int iInit, int jInit, int iFin, int jFin) {
		boolean possible = false;
		if ( iInit > iFin && jInit < jFin ){
			if ( this.grille.memePion(iInit, jInit, iInit - 1, jInit + 1) ){ //2meme pions cote a cote
				if ( this.grille.caseAdvairse(iInit, jInit, iInit - 2, jInit + 2 ) && jFin == jInit + 3 && iFin == iInit - 3 ) // 2 VS 1
					possible = true;
				else if ( this.grille.memePion(iInit, jInit, iInit - 2, jInit + 2 )){ //3meme pion cote a cote
					if ( this.grille.caseAdvairse(iInit, jInit, iInit - 3, jInit + 3) ){ //il y a au moins 1 pion advairse ensuite
						if ( jFin == jInit + 4 && iFin == iInit - 4) possible = true; // 3 VS 1
						else if ( this.grille.caseAdvairse(iInit, jInit, iInit - 4, jInit + 4) && jFin == jInit + 5 && iFin == iInit - 5) possible = true; //3 VS 2
					}
				}
			}
		}
		return possible;
	}
	
	/**
	 * Effectue un sumito diagonal gauche/bas
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jnew
	 * @return true si le nombre de deplacements effectues 
	 */
	public int sumitoDiagonalGB(int i, int j, int inew, int jnew) {
		int sumito = 0;
		if (j > jnew && i < inew) { // sumito diagonal neg basse
			int nbPousseur = this.grille.getVoisinsDiagonaleGB(i, j);
			int nbPousser = this.grille.getVoisinsDiagonaleGB(inew, jnew);
			if (nbPousseur > nbPousser && nbPousser < 3) {
				int jdeplace = jnew - nbPousser;
				int ideplace = inew + nbPousser;
				for (int ind = 0; ind != nbPousseur + nbPousser; ind++) {
					if (ind == 0) {
						this.ejectPion(ideplace - 1, jdeplace + 1, ideplace,
								jdeplace);
					} else {
						this.deplacement(ideplace - 1, jdeplace + 1, ideplace,
								jdeplace);
					}
					jdeplace += 1;
					ideplace -= 1;
				}
				sumito = nbPousseur;
			}
		}
		return sumito;
	}
	
	/**
	 * Methode utilise avant d'execute un sumito
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @return true si le sumito diagonal gauche/bas est possible
	 */
	public boolean sumitoDiagonalGBPossible(int iInit, int jInit, int iFin, int jFin) {
		boolean possible = false;
		if ( iInit < iFin && jInit > jFin ){
			if ( this.grille.memePion(iInit, jInit, iInit + 1, jInit - 1) ){ //2meme pions cote a cote
				if ( this.grille.caseAdvairse(iInit, jInit, iInit + 2, jInit - 2 ) && jFin == jInit - 3 && iFin == iInit + 3 ) // 2 VS 1
					possible = true;
				else if ( this.grille.memePion(iInit, jInit, iInit + 2, jInit - 2 )){ //3meme pion cote a cote
					if ( this.grille.caseAdvairse(iInit, jInit, iInit + 3, jInit - 3) ){ //il y a au moins 1 pion advairse ensuite
						if ( jFin == jInit - 4 && iFin == iInit + 4) possible = true; // 3 VS 1
						else if ( this.grille.caseAdvairse(iInit, jInit, iInit + 4, jInit - 4) && jFin == jInit - 5 && iFin == iInit + 5) possible = true; //3 VS 2
					}
				}
			}
		}
		return possible;
	}

	/**
	 * methode utilise par lIA regarde si un deplacement est possible
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @return true sile deplacement est possible, false sinon
	 */
	public boolean deplacementIsPossible(int iInit, int jInit, int iFin, int jFin) {
		boolean deplacement = false;
		if ( !this.grille.isOut(iInit, jInit) && this.grille.isCase(iFin, jFin) ){
			if (this.deplacementSansSumitoPosible(iInit, jInit, iFin, jFin)){
				deplacement = true;}
			else if ( this.sumitoPossible(iInit, jInit, iFin, jFin))
				deplacement = true;	
			else 
				deplacement = false;
		}
			return deplacement;
	}
	
	/**
	 * Methode utilisee par lIA, verifie si un sumito est possible
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @return
	 */
	public boolean deplacementSansSumitoPosible(int iInit, int jInit, int iFin, int jFin){ 
		boolean deplacement = false;
		if ( this.grille.verifCaseVide(iFin, jFin)){
			if ( iInit == iFin && jFin < jInit ){ //deplacement hori/gauche
				if(jInit - 2 == jFin ) deplacement = true; //deplace 1 pion
				if ( this.grille.verifMemePion(iInit, jInit - 2, iInit, jInit) ) { //verifie case intermediaire est vide elle permet d'eviter des tests deja faient 
					if (jInit - 4 == jFin ) deplacement = true; //deplace 2 pion
					if ( this.grille.verifMemePion(iInit, jInit - 4, iInit, jInit) ) 
						if ( jInit - 6 == jFin) deplacement = true; //deplace 3 pion
				}
			}
			else if ( iInit == iFin && jFin > jInit ){ //deplacement hori/droite
				if(jInit + 2 == jFin ) deplacement = true; //deplace 1 pion
				if ( this.grille.verifMemePion(iInit, jInit + 2, iInit, jInit) ) { //verifie case intermediaire est vide elle permet d'eviter des tests deja faient 
					if (jInit + 4 == jFin ) deplacement = true; //deplace 2 pions
						if ( this.grille.verifMemePion(iInit, jInit + 4, iInit, jInit) ) 
							if ( jInit + 6 == jFin) deplacement = true; //deplace 3 pions
				}
			}
			else if ( iInit > iFin && jFin > jInit){ //deplacement diag. droite/haut
				if(iInit - 1 == iFin && jInit + 1 == jFin) deplacement = true; //deplace 1 pion
				if ( this.grille.verifMemePion(iInit - 1, jInit + 1, iInit, jInit) ) { //verifie case intermediaire est vide elle permet d'eviter des tests deja faient 
					if (iInit - 2 == iFin && jInit + 2 == jFin) deplacement = true; //deplace 2 pions
						if ( this.grille.verifMemePion(iInit - 2, jInit + 2, iInit, jInit) ) 
							if ( iInit - 3 == iFin && jInit + 3 == jFin ) deplacement = true; //deplace 3 pions
				}
			}
			else if ( iInit < iFin && jFin > jInit){ //deplacement diag. droite/bas
				if(iInit + 1 == iFin && jInit + 1 == jFin) deplacement = true; //deplace 1 pion
				if ( this.grille.verifMemePion(iInit + 1, jInit + 1, iInit, jInit) ) { //verifie case intermediaire est vide elle permet d'eviter des tests deja faient 
					if (iInit + 2 == iFin && jInit + 2 == jFin) deplacement = true; //deplace 2 pions
						if ( this.grille.verifMemePion(iInit + 2, jInit + 2, iInit, jInit) ) 
							if ( iInit + 3 == iFin && jInit + 3 == jFin ) deplacement = true; //deplace 3 pions
				}
			}
			else if ( iInit > iFin && jFin < jInit){ //deplacement diag. gauche/haut
				if(iInit - 1 == iFin && jInit - 1 == jFin) deplacement = true; //deplace 1 pion
				if ( this.grille.verifMemePion(iInit - 1, jInit - 1, iInit, jInit) ) { //verifie case intermediaire est vide elle permet d'eviter des tests deja faient 
					if (iInit - 2 == iFin && jInit - 2 == jFin) deplacement = true; //deplace 2 pions
						if ( this.grille.verifMemePion(iInit - 2, jInit - 2, iInit, jInit) ) 
							if ( iInit - 3 == iFin && jInit - 3 == jFin ) deplacement = true; //deplace 3 pions
				}
			}
			else if ( iInit < iFin && jFin < jInit){ //deplacement diag. gauche/bas
				if(iInit + 1 == iFin && jInit - 1 == jFin) deplacement = true; //deplace 1 pion
				if ( this.grille.verifMemePion(iInit + 1, jInit - 1, iInit, jInit) ) { //verifie case intermediaire est vide elle permet d'eviter des tests deja faient 
					if (iInit + 2 == iFin && jInit - 2 == jFin) deplacement = true; //deplace 2 pions
						if ( this.grille.verifMemePion(iInit + 2, jInit - 2, iInit, jInit) ) 
							if ( iInit + 3 == iFin && jInit - 3 == jFin ) deplacement = true; //deplace 3 pions
				}
			}
			else 
				deplacement = false;
		}
			return deplacement;
	}
	
	/**
	 * Methode utilisee pour lIA
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @return true si le sumito est possible, false sinon
	 */
	public boolean sumitoPossible(int iInit, int jInit, int iFin, int jFin) {
		if ( this.grille.isOut(iFin, jFin ) || this.grille.caseVide(iFin, jFin)) 
			return this.sumitoHorizontaleDPossible(iInit, jInit, iFin, jFin) || this.sumitoDiagonalDBPossible(iInit, jInit, iFin, jFin)
					|| this.sumitoDiagonalDHPossible(iInit, jInit, iFin, jFin) || this.sumitoHorizontaleGPossible(iInit, jInit,iFin, jFin)
					|| this.sumitoDiagonalGBPossible(iInit, jInit, iFin, jFin) || this.sumitoDiagonalGHPossible(iInit, jInit, iFin, jFin);
		else 
			return false;
	}
	
	/**
	 * Methode utilise par la classe Jeu, elle regarde si les deplacements venant de la vue en direction du modelesont possibles
	 * @param positionsInit
	 * @param positionsFinal
	 * @param copie
	 * @return true si les deplacements sont tous valides, false sinon
	 */
	public boolean sontVoisins(ArrayList<Integer> positionsInit, ArrayList<Integer> positionsFinal, Plateau copie) {
		if (this.memeTranslation(positionsInit, positionsFinal, copie)) {
			if (positionsInit.size() == 4) {
				return this.grille.auxsontVoisins(positionsInit.get(0),
						positionsInit.get(1), positionsInit.get(2),
						positionsInit.get(3))
						&& copie.auxsontVoisins(positionsFinal.get(0),
								positionsFinal.get(1), positionsFinal.get(2),
								positionsFinal.get(3));
			} else {
				int nbTrue = 0;
				if (this.grille.auxsontVoisins(positionsInit.get(0),
						positionsInit.get(1), positionsInit.get(2),
						positionsInit.get(3))
						&& copie.auxsontVoisins(positionsFinal.get(0),
								positionsFinal.get(1), positionsFinal.get(2),
								positionsFinal.get(3))) {
					nbTrue++;
				}
				if (this.grille.auxsontVoisins(positionsInit.get(0),
						positionsInit.get(1), positionsInit.get(4),
						positionsInit.get(5))
						&& copie.auxsontVoisins(positionsFinal.get(0),
								positionsFinal.get(1), positionsFinal.get(4),
								positionsFinal.get(5))) {
					nbTrue++;
				}
				if (this.grille.auxsontVoisins(positionsInit.get(2),
						positionsInit.get(3), positionsInit.get(4),
						positionsInit.get(5))
						&& copie.auxsontVoisins(positionsFinal.get(2),
								positionsFinal.get(3), positionsFinal.get(4),
								positionsFinal.get(5))) {
					nbTrue++;
				}
				if (nbTrue > 1) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	/**
	 * Methode utilisant le coeficient directeur pour verifier si des deplacements sefont dans la meme directions
	 * Remarque cette fonction peut être ameliore mais des bugs de fn de projet sont apparus et nous avons avons isoler tous les cas
	 * qui ne respectait pas les regles du jeu dans des "if"
	 * @param positionsInit
	 * @param positionsFinal
	 * @param copie
	 * @return true si les deplacements sont dans la memes direction, false sinon
	 */
	public boolean memeTranslation(ArrayList<Integer> positionsInit,
			ArrayList<Integer> positionsFinal, Plateau copie) {
		if (positionsInit.size() == 4) {
			return positionsInit.get(0) - positionsFinal.get(0) == positionsInit
					.get(2) - positionsFinal.get(2)
					&& positionsInit.get(1) - positionsFinal.get(1) == positionsInit
							.get(3) - positionsFinal.get(3);
		} else {
			if (positionsInit.get(0) == positionsInit.get(2)
					&& positionsInit.get(0) == positionsInit.get(4)) {
				return (positionsInit.get(0) - positionsFinal.get(0) == positionsInit
						.get(2) - positionsFinal.get(2))
						&& (positionsInit.get(4) - positionsFinal.get(4) == positionsInit
								.get(2) - positionsFinal.get(2))
						&& (positionsInit.get(1) - positionsFinal.get(1) == positionsInit
								.get(3) - positionsFinal.get(3))
						&& (positionsInit.get(5) - positionsFinal.get(5) == positionsInit
								.get(3) - positionsFinal.get(3));
			}
			else if ( positionsInit.get(0) - positionsFinal.get(0) == positionsInit.get(2) - positionsFinal.get(2) &&
					positionsInit.get(4) - positionsFinal.get(4) == positionsInit.get(0) - positionsFinal.get(0) &&
					positionsInit.get(1) - positionsFinal.get(1) == positionsInit.get(3) - positionsFinal.get(3) &&
					positionsInit.get(5) - positionsFinal.get(5) == positionsInit.get(1) - positionsFinal.get(1)) {  //A retirer si BUG	
				return 
							(positionsFinal.get(4) == positionsInit.get(0) && positionsFinal.get(5) == positionsInit.get(1) && positionsFinal.get(0) == positionsInit.get(2) && positionsFinal.get(1) == positionsInit.get(3)/*&& positionsFinal.get(2) != positionsFinal.get(2) && positionsFinal.get(4) != positionsFinal.get(4) && positionsFinal.get(0) != positionsFinal.get(0) && positionsFinal.get(2) != positionsFinal.get(2)*/) ||
							(positionsFinal.get(0) == positionsInit.get(0) && positionsFinal.get(2) == positionsInit.get(2) && positionsFinal.get(4) == positionsInit.get(4) && positionsFinal.get(0) == positionsInit.get(2) && positionsFinal.get(2) == positionsInit.get(4) )
							|| (positionsFinal.get(0) - positionsInit.get(0) == 1 && positionsFinal.get(1) - positionsInit.get(1) == -1 && positionsFinal.get(2) - positionsInit.get(2) == 1 && positionsFinal.get(3) - positionsInit.get(3) == -1 && positionsFinal.get(4) - positionsInit.get(4) == 1 && positionsFinal.get(5) - positionsInit.get(5) == -1 && positionsFinal.get(0) != positionsFinal.get(2) && positionsFinal.get(2) != positionsFinal.get(4)/* && positionsFinal.get(0) != positionsFinal.get(0) && positionsFinal.get(2) != positionsFinal.get(2)*/)
							|| (positionsFinal.get(0) - positionsInit.get(0) == -1 && positionsFinal.get(1) - positionsInit.get(1) == 1 && positionsFinal.get(2) - positionsInit.get(2) == -1 && positionsFinal.get(3) - positionsInit.get(3) == 1 && positionsFinal.get(4) - positionsInit.get(4) == -1 && positionsFinal.get(5) - positionsInit.get(5) == 1 && positionsFinal.get(0) != positionsFinal.get(2) && positionsFinal.get(2) != positionsFinal.get(4)/* && positionsFinal.get(0) != positionsFinal.get(0) && positionsFinal.get(2) != positionsFinal.get(2)*/)
							|| (positionsFinal.get(0) - positionsInit.get(0) == 1 && positionsFinal.get(1) - positionsInit.get(1) == 1 && positionsFinal.get(2) - positionsInit.get(2) == 1 && positionsFinal.get(3) - positionsInit.get(3) == 1 && positionsFinal.get(4) - positionsInit.get(4) == 1 && positionsFinal.get(5) - positionsInit.get(5) == 1 && positionsFinal.get(0) != positionsFinal.get(2) && positionsFinal.get(2) != positionsFinal.get(4)/* && positionsFinal.get(0) != positionsFinal.get(0) && positionsFinal.get(2) != positionsFinal.get(2)*/)
							|| (positionsFinal.get(0) - positionsInit.get(0) == -1 && positionsFinal.get(1) - positionsInit.get(1) == -1 && positionsFinal.get(2) - positionsInit.get(2) == -1 && positionsFinal.get(3) - positionsInit.get(3) == -1 && positionsFinal.get(4) - positionsInit.get(4) == -1 && positionsFinal.get(5) - positionsInit.get(5) == -1 && positionsFinal.get(0) != positionsFinal.get(2) && positionsFinal.get(2) != positionsFinal.get(4)/* && positionsFinal.get(0) != positionsFinal.get(0) && positionsFinal.get(2) != positionsFinal.get(2)*/)
							|| ((positionsFinal.get(0) == positionsInit.get(0) && positionsFinal.get(2) == positionsInit.get(2) && positionsFinal.get(4) == positionsInit.get(4) && ((positionsFinal.get(1) - positionsInit.get(1) == 2 && positionsFinal.get(3) - positionsInit.get(3) == 2 && positionsFinal.get(5) - positionsInit.get(5) == 2) ||
									(positionsFinal.get(1) - positionsInit.get(1) == -2 && positionsFinal.get(3) - positionsInit.get(3) == -2 && positionsFinal.get(5) - positionsInit.get(5) == -2)) && positionsFinal.get(0) != positionsFinal.get(2) && positionsFinal.get(2) != positionsFinal.get(4))) ; }
			else if ((positionsInit.get(0) - positionsFinal.get(0))
					/ (positionsInit.get(1) - positionsFinal.get(1)) == (positionsInit
					.get(2) - positionsFinal.get(2))
					/ (positionsInit.get(3) - positionsFinal.get(3))
					&& (positionsInit.get(0) - positionsFinal.get(0))
							/ (positionsInit.get(1) - positionsFinal.get(1)) == (positionsInit
							.get(4) - positionsFinal.get(4))
							/ (positionsInit.get(5) - positionsFinal.get(5))) {
				return 
						(positionsFinal.get(2) == positionsInit.get(0)
						&& positionsFinal.get(3) == positionsInit.get(1)
						&& positionsFinal.get(4) == positionsInit.get(2) && positionsFinal
							.get(5) == positionsInit.get(3) || positionsFinal.get(0) == positionsInit.get(2)
									&& positionsFinal.get(1) == positionsInit.get(3)
									&& positionsFinal.get(4) == positionsInit.get(0) && positionsFinal
										.get(5) == positionsInit.get(1));
																
			} else {
				return false;
			}
		}
	}
	
	public boolean verifDeplacementHorizontalValide(int ideplace, int jdeplace,
			int inew, int jnew) {
		return ideplace == inew;
	}

	public boolean verifDeplacementHorizontalValideGauche(int ideplace,
			int jdeplace, int inew, int jnew) {
		return this.verifDeplacementHorizontalValide(ideplace, jdeplace, inew,
				jnew) && jnew < jdeplace;
	}

	public boolean verifDeplacementHorizontalValideDroite(int ideplace,
			int jdeplace, int inew, int jnew) {
		return this.verifDeplacementHorizontalValide(ideplace, jdeplace, inew,
				jnew) && jnew > jdeplace;
	}

	public boolean verifDeplacementDiagonalValide(int ideplace, int jdeplace,
			int inew, int jnew) {
		return (ideplace - inew == jdeplace - jnew)
				|| (ideplace - inew == jnew - jdeplace);
	}

	public boolean verifDeplacementDiagonalValideGH(int ideplace, int jdeplace,
			int inew, int jnew) {
		return ideplace - inew == jdeplace - jnew && jdeplace > jnew;
	}

	public boolean verifDeplacementDiagonalValideGB(int ideplace, int jdeplace,
			int inew, int jnew) {
		return ideplace - inew == jnew - jdeplace && jdeplace > jnew;
	}

	public boolean verifDeplacementDiagonalValideDH(int ideplace, int jdeplace,
			int inew, int jnew) {
		return ideplace - inew == jnew - jdeplace && jdeplace < jnew;
	}

	public boolean verifDeplacementDiagonalValideDB(int ideplace, int jdeplace,
			int inew, int jnew) {
		return ideplace - inew == jdeplace - jnew && jdeplace < jnew;
	}
	
	/**
	 * Methode ergonomique, verifie si le deplacement est possible
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 * @return true si le deplacement est facile, false sinon
	 */
	public boolean verifDeplacementFacile(int iInit, int jInit, int iFin, int jFin) { // ne pas oublie le cas on clique sur une case -1
		boolean deplacement = false;
		if (iInit == iFin) { // si c'est un deplacement d'un pion horizontalment
			deplacement = true;
		} else if ((iInit - iFin == jInit - jFin)
				|| (iInit - iFin == jFin - jInit)) { // deplacement diagonal
			deplacement = true;
		}
		return deplacement;
	}
	
	public int nbVoisins(int i, int j){
		int cpt = 0;
		if ( this.grille.auxsontVoisins(i, j, i, j + 2) ) //voisin droite
			cpt ++;
		if ( this.grille.auxsontVoisins(i, j, i, j - 2) ) //voisin gauche
			cpt ++;
		if ( this.grille.auxsontVoisins(i, j, i + 1, j - 1) ) //voisin bas/gauche
			cpt ++;
		if ( this.grille.auxsontVoisins(i, j, i + 1, j + 1) ) //voisin bas/droite
			cpt ++;
		if ( this.grille.auxsontVoisins(i, j, i - 1, j - 1) ) //voisin haut/gauche
			cpt ++;
		if ( this.grille.auxsontVoisins(i, j, i - 1, j + 1) ) //voisin haut/droite
			cpt ++;
		return cpt;
	}



	
}
