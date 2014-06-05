package abalone.controller;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.badlogic.gdx.graphics.Color;

import abalone.model.Plateau;
import abalone.view.Bille;
import abalone.view.EcranPlateau;
import abalone.view.Musique;

/**
 * Classe permettant de relier la classe jeu avec la classe EcranPlateau, permet de relier un jeu avec l'ecran
 * @author kevine2710
 *
 */

public class Refresh implements Runnable{
	
	public ArrayList<Bille> listeBille;
	public ArrayList<Bille> cache; //toute les billes qui doivent etre deplace sont places en memoire pour que toute les billes se deplace en memes temps
	public EcranPlateau ecranPlateau;
	protected Musique musiqueJeu;
	private Jeu jeu;
	private Plateau plateau;
	private boolean finPartie; //true si c'estla fin de partie, false sinon

	public Refresh( ArrayList<Bille> billes, EcranPlateau ecranPlateau, Musique musique, Jeu jeu, Plateau plateau ){
		this.ecranPlateau = ecranPlateau;
		this.listeBille = billes;
		this.cache = new ArrayList<Bille>();
		this.musiqueJeu = musique;
		this.jeu = jeu;
		this.plateau = plateau;
		this.finPartie = false;
	}
	
	public void joueurNoirGagne( boolean ia ){
		this.finPartie = true;
		this.ecranPlateau.setNoirGagne(true);
		this.musiqueJeu.stopMusiqueFond();
		if ( ia ){
			this.musiqueJeu.playGagneIA();
		}
		else
			this.musiqueJeu.playGagneJoueur();
	}
	
	public void joueurBlancheGagne(){
		this.finPartie = true;
		this.musiqueJeu.stopMusiqueFond();
		this.ecranPlateau.setBlancheGagne(true);
		this.musiqueJeu.playGagneJoueur();
	}
	
	
	/**
	 * Ajoute dans le cache toutes les billes en attente d'etre dessine
	 * @param iInit
	 * @param jInit
	 * @param iFin
	 * @param jFin
	 */
	public void add(int iInit, int jInit, int iFin, int jFin){
		Bille stock;
		boolean trouve = false;
		int i = 0;
		while(!trouve && i < this.listeBille.size()){
			stock = this.listeBille.get(i);
			if ( stock.getIinit() == iInit && stock.getJinit() == jInit && stock.getDraw()){
				trouve = true;
				this.cache.add(stock);
				stock.setCoordonneeFinal(Coordonnee.getCoordonne(iFin, jFin));
			}
			i++;
		}
	}
	
	/**
	 * Trace les billes qui sont dans le cache
	 */
	public void draw(){
		ExecutorService es = null;
		boolean exception = false;
		while ( !exception){
			try{
				es = Executors.newFixedThreadPool(this.cache.size()); //pour faire glisser de facon synchrone
				exception = true;
			}
			/* Le bloc est protege par un try/catch, car l'execution de 0 thread n'est pas dangereux pour le programme
			 * on peut alors lever l'exception
			 */
			catch (java.lang.IllegalArgumentException e){ }
		}
			for ( int i = 0; i < this.cache.size(); i++){
				es.execute(this.cache.get(i));
			}
			this.cache.clear();
			try {
				this.musiqueJeu.playDeplacement();
			}
			catch( java.lang.NullPointerException e) {}
	}
	
	/**
	 * Cherche la bille selectionee et la colorie en vert
	 * @param Coordonnee de la bille
	 */
	public void select( Coordonnee coor ){
		Bille stock;
		boolean trouve = false;
		int i = 0;
		while(!trouve && i < this.listeBille.size()){
			stock = this.listeBille.get(i);
			if ( stock.getIinit() == coor.getI() && stock.getJinit() == coor.getJ() && stock.getDraw()){
				trouve = true;
				stock.select();
			}
			i++;
		}
	}
	
	/**
	 * Cherche la bille selectionee et la colorie avec la couleur passe en paramtre
	 * @param Coordonnee de la bille
	 * @param Couleur de la bille qui va etre selectionne
	 */
	public void select( Coordonnee coor, Color couleur ){
		Bille stock;
		boolean trouve = false;
		int i = 0;
		while(!trouve && i < this.listeBille.size()){
			stock = this.listeBille.get(i);
			if ( stock.getIinit() == coor.getI() && stock.getJinit() == coor.getJ() && stock.getDraw()){
				trouve = true;
				stock.select(couleur);
			}
			i++;
		}
	}
	
	/**
	 * Deselectionne une bille
	 * @param Coordonnee de la bille
	 */
	public void deSelect( Coordonnee coor ){
		Bille stock;
		boolean trouve = false;
		int i = 0;
		while(!trouve && i < this.listeBille.size()){
			stock = this.listeBille.get(i);
			if ( stock.getIinit() == coor.getI() && stock.getJinit() == coor.getJ() && stock.getDraw()){
				trouve = true;
				stock.deSelectionne();
			}
			i++;
		}
	}
	
	/**
	 * Detruit une bille
	 */
	public void detruit( Coordonnee coor, TypeBille typeBille ){
		Bille stock;
		boolean trouve = false;
		int i = 0;
		while(!trouve && i < this.listeBille.size()){
			stock = this.listeBille.get(i);
			if ( stock.getIinit() == coor.getI() && stock.getJinit() == coor.getJ() && typeBille.equals(stock.getTypeBille())){
				trouve = true;
				stock.setDraw(false);
			}
			i++;
		}
	}
	
	/**
	 * Change le nombre de tours sur la vue
	 * @param nbDeplcement
	 */
	public void setNbDeplacement( int nbDeplcement ){
		this.ecranPlateau.setNbDeplacement(String.valueOf(nbDeplcement));
	}
	
	/**
	 * Remet le compteur de tours à 0
	 */
	
	public void clearNbDeplacement(){
		this.ecranPlateau.setNbDeplacement(String.valueOf(0));
	}
	
	public void setFinPartie( boolean set ){
		this.finPartie = set;
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return Retourne une bille en fonction de ses coordonnees
	 */
	public Bille getBilleByCoor( int i , int j){
		boolean trouve = false;
		int index = 0;
		Bille stock = null;
		while ( !trouve && index < this.listeBille.size() ){
			stock = this.listeBille.get(index);
			if ( stock.getIinit() == i && stock.getJinit() == j)
				trouve = true ;
			index ++ ;
		}
		return stock;
	}

	/**
	 * Toute les 10sec colorie les billes pendant un cours instant du jouer qui doit jouer
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while ( true ){
			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 21; j++) {
					if ( this.finPartie == false ) {
						if ( this.jeu.isBlack() ){
							if ( this.plateau.getPion(i, j) == 2 && this.getBilleByCoor(i, j).getSelect() == false){
								this.select(Coordonnee.getCoordonne(i, j), Color.DARK_GRAY);
							}
						}
						else {
							if ( this.plateau.getPion(i, j) == 1 && this.getBilleByCoor(i, j).getSelect() == false){
								this.select(Coordonnee.getCoordonne(i, j), Color.DARK_GRAY);
							}
						}
					}
				}
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {} //on leve une eventuelle exception*/
			for ( int i = 0; i < this.listeBille.size(); i++){
				if ( this.finPartie == false ) {
					if ( this.listeBille.get(i).getCouleur().equals(Color.DARK_GRAY) ) //une bille selectionne en vert nedoit pas etre selectionnee
						this.listeBille.get(i).deSelectionne();
				}
			}
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {}
		}
	}
	

}
