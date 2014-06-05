package abalone.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import abalone.IA.Ordinateur;
import abalone.model.Plateau;
import abalone.view.EcranPlateau;

/**
 * Classe permettant de jouer contre un joueur ou contre l'ordinateur
 * @author kevine2710
 *
 */

@SuppressWarnings("serial")
public class Jeu implements Serializable{

	private transient Deplacement deplacement;
	private Plateau plateau;
	private Semaphore wait; 
	private transient Refresh refresh;
	private boolean black; //true si le joueur noir doit jouer, false sinon
	private boolean white; //true si le joueur blanc doit jouer, false sinon
	private int coupIA; //compte le nombre de coup predefinit
	private boolean facile; //true si le mode de jeu est facile
	private boolean moyen; //true si le mode de jeu est moyen
	private boolean difficile;//true si le mode de jeu est difficile
	private int nbDeplacement;
	private transient int[] input; // La classe InputAbalone gere cette variable, elle
							// contient les coordonnees d'un trou du plateau du
							// jeu sous la forme "i-j"
	
	/**CONSTRUCTEUR**/
	
	public Jeu( Plateau plateau, EcranPlateau ecranPlateau) {
		this.input = new int[2];
		this.plateau = plateau;
		this.wait = new Semaphore(0, true);
		this.white = true;
		this.black = false;
		this.coupIA = 0;
		this.nbDeplacement = 0;
	}
	
	/**METHODES**/
	
	/**
	 * Methode utilise pour charger une partir deja existante
	 * @param Jeu une partie existante
	 */
	public void copieJeu( Jeu jeu ){
		this.plateau = jeu.plateau;
		this.black = jeu.black;
		this.white = jeu.white;
	}
	
	/**
	 * Relance une partie avec un nouveau plateau
	 * @param Plateau
	 */
	public void refreshGame( Plateau plateau ){
		this.plateau = plateau;
		this.wait = new Semaphore(0, true);
		this.white = true;
		this.black = false;
		this.nbDeplacement = 0;
		this.coupIA = 0;
	}
	
	/**
	 * Methode permettant de ancer le mode joueur VS IA
	 * @param true si mode facile, false sinon
	 * @param true si mode moyen, false sinon
	 * @param true si mode difficile, false sinon
	 */
	public void jouerIA(boolean facile, boolean moyen, boolean difficile){
		this.facile = facile;
		this.moyen = moyen;
		this.difficile = difficile;
		Ordinateur ia = new Ordinateur(this.plateau, this.refresh);;
		while (!this.plateau.endGame()) {
			if ( this.white ) {
				this.deplacementMultiple(this.white);
			}
			this.refresh.setNbDeplacement(0);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			this.white = !this.white;
			this.black = !this.black;
			if ( this.coupIA < 3){
				ia.simulation(facile, moyen, difficile, this.coupIA);
				this.white = ! this.white;
				this.coupIA ++ ;
			}
			else if ( this.coupIA >= 3 && !this.plateau.endGame() ){
				ia.simulation(facile, moyen, difficile, 10);
				this.white = !this.white;
				this.black = !this.black;
			}
		}
		if (this.white) {
			this.refresh.joueurNoirGagne(true);
		} else {
			this.refresh.joueurBlancheGagne();
		}
	}

	/**
	 * Lance le mode joueur VS joueur
	 */
	public void jouer() {
		System.out.println(this.white);
		while (!this.plateau.endGame()) {
			this.deplacementMultiple(this.white);
			this.white = !this.white;
			this.black = !this.black;
		}
		if (this.white) {
			this.refresh.joueurNoirGagne(false);
		} else {
			this.refresh.joueurBlancheGagne();
		}
	}
	
	/**
	 * Methode qui sert a lancer le tour d'un joueur
	 * Cette fonction est grande mais verifie plusieurs cas
	 * @param boolean joueur, true si joueur blanc, false sinon
	 */
	public void deplacementMultiple(boolean joueur) { // si true J1
		ArrayList<Integer> coordInit = new ArrayList<Integer>();
		ArrayList<Integer> coorFinal = new ArrayList<Integer>();
		ArrayList<Integer> resultat = new ArrayList<Integer>();
		int iInit, jInit, iFinal, jFinal;
		Plateau copie = new Plateau();
		Plateau newCopie = new Plateau();
		copie.copiePlateau(this.plateau.plateau);
		Deplacement deplacementCopie = new Deplacement(copie);
		Deplacement deplacementNewCopie = new Deplacement(newCopie);
		int stock, pion;
		boolean stop = false;
		boolean cliqueSurSois = false;
		boolean deplacementPossible;
		this.nbDeplacement = 0;
		if (joueur) {
			pion = 1;
		} else {
			pion = 2;
		}
		if (!this.plateau.endGame()) {// double verif
			while (nbDeplacement < 3 && !stop) {
				deplacementPossible = true;
				this.refresh.setNbDeplacement(nbDeplacement);
				try {
					this.wait.acquire(1); //Attend une coord
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					
				}
				if (this.input[0] != -1) { //Si on passe le tour
					iInit = this.input[0];
					jInit = this.input[1];
					if ( (this.white && this.plateau.getPion(iInit, jInit) == 1) || (this.black && this.plateau.getPion(iInit, jInit) == 2))
						this.refresh.select(Coordonnee.getCoordonne(iInit, jInit));
					try {
						this.wait.acquire(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if ( iInit == this.input[0] && jInit == this.input[1]) {
						this.refresh.deSelect(Coordonnee.getCoordonne(iInit, jInit));
						cliqueSurSois = true;
						System.out.println("coucou");
					}
					else
					if (this.input[0] != -1) { //Si on passe le tour
						this.refresh.deSelect(Coordonnee.getCoordonne(iInit, jInit));
						iFinal = this.input[0];
						jFinal = this.input[1];
						if (pion == this.plateau.getPion(iInit, jInit)) { //Si on clique sur son pion
							coordInit.add(iInit);//ajoute la coor i du pion a deplacer
							coordInit.add(jInit);//ajoute la coor j du pion a deplacer
							resultat = this.deplacement.deplacementFacileAux(iInit,
									jInit, iFinal, jFinal); //met dans l'arraylist la simplification des coordonnees
							//System.out.println("Buffer avant" + resultat);
							if (resultat.size() != 0) { //if on deplace au moin 1 pion
								coorFinal.add(resultat.get(0)); //on ajoute la coord. i a l'arraylist final
								coorFinal.add(resultat.get(1));//on ajoute la coord. j a l'arraylist final
								if (resultat.size() >= 4) { //Si on deplace au moins deux pions d'un coup
									coordInit.add(resultat.get(0));
									coordInit.add(resultat.get(1));
									coorFinal.add(resultat.get(2));
									coorFinal.add(resultat.get(3));
								}
								if (resultat.size() == 6) {
									coordInit.add(resultat.get(2));
									coordInit.add(resultat.get(3));
									coorFinal.add(resultat.get(4));
									coorFinal.add(resultat.get(5));
								}
							} else {
								coordInit.remove(coordInit.size() - 1); //si le deplacement est impossible
								coordInit.remove(coordInit.size() - 1);
								deplacementPossible = false;
							}
							newCopie.copiePlateau(this.plateau.plateau);
							if (nbDeplacement != 0) {
								stock = deplacementNewCopie.deplacementLineaire(iInit,
										jInit, iFinal, jFinal, nbDeplacement); //execute le deplacement
								if (stock != 0
										&& deplacementCopie.sontVoisins(coordInit,
												coorFinal, newCopie)) { //le deplacement est un succes
									nbDeplacement += stock;
									this.deplacement.deplacementLineaire(iInit,
											jInit, iFinal, jFinal, nbDeplacement);//on fait de meme sur le vrai plateau
									this.refresh.draw();
									
								} else { //si le deplacement a echoue on rm le buffer
									int x = coordInit.size() - 1;
									if (resultat.size() == 4) {
										for (int i = x; i != x - 4; i--) {
											coordInit.remove(i);
											coorFinal.remove(i);
										}
									} else {
										for (int i = x; i != x - 2; i--) {
											coordInit.remove(i);
											coorFinal.remove(i);
										}
									}
								}
							} else if (nbDeplacement == 0 && deplacementPossible) { 
								this.refresh.deSelect(Coordonnee.getCoordonne(iInit, jInit));
								nbDeplacement += this.deplacement.deplacementLineaire(
										iInit, jInit, iFinal, jFinal,
										nbDeplacement);

								this.refresh.draw();
							}
							resultat.clear(); //on efface resultat
						}
						else if (nbDeplacement != 0) { //si on clique pas sur son pion
							nbDeplacement = 3;
						}
					}
					else if (nbDeplacement >= 1 && !cliqueSurSois) {
						stop = true;
					}
				} 
				else if (nbDeplacement >= 1) {
						stop = true;
						System.out.println(this.plateau.toString());
					}
				}
			}
		}
		
	/**GETTERS**/

	public Plateau getPlateau() {
		// TODO Auto-generated method stub
		return this.plateau;
	}
	
	public boolean isBlack() {
		return black;
	}
	
	/**SETTERS**/
	
	/**
	 * Relie les cases clique sur l'ecran au model, permet de deplacer les billes
	 * @param Coordonnee coor se charge de transcrire X,Y de l'ecran en i,j du tableau
	 * @param boolean stop, true si on veut passer le tour, false sinon
	 */
	public void setInput(Coordonnee coor, boolean stop) {
		if ( !stop){
			this.input[0] = coor.getI();
			this.input[1] = coor.getJ();
		}
		else {
			this.input[0] = -1;
			this.input[1] = -1;
		}
			this.wait.release();
	}
	
	public boolean isFacile(){
		return this.facile;
	}
	
	public boolean isMoyen(){
		return this.moyen;
	}
	
	public boolean isDifficile(){
		return this.difficile;
	}
	
	public int getNbDeplacement(){
		return this.nbDeplacement;
	}

	public void setBlack(boolean black) {
		this.black = black;
	}

	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean white) {
		this.white = white;
	}
	
	public void setRefresh(Refresh refresh) {
		this.deplacement = new Deplacement(plateau,refresh);
		this.refresh = refresh;
	}
}