package abalone.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import abalone.model.Plateau;
import abalone.view.Bille;
import abalone.view.EcranMenu;
import abalone.view.EcranPlateau;
import abalone.view.Musique;
import abalone.view.Screen;

import com.badlogic.gdx.ApplicationListener;

/**
 * Classe qui reunit toutes les classes et les mets à jour
 * @author groupe projet
 * @mail kevin.margueritte@gmail.com
 *
 * definition : un render est une methode qui met a jour la carte graphique, si on appelle de la musique ou encore une image
 * et que la fonction render utilise par libGDX n'est pas lancé une erreur suvient. C'est pour cela qu'on utilise des semaphore.
 */

@SuppressWarnings("serial")
public class Lanceur implements Serializable, Runnable, ApplicationListener {
	protected Jeu game;
	private Plateau plateau;
	private Semaphore sem; //atteind tant qu'un render n'est pas lance
	private transient EcranPlateau ecranPlateau;
	private boolean jouerVSia;//true si le mode joueur VS IA a ete lance
	protected transient Screen screen;
	private int ia;
	private transient EcranMenu menu;
	private transient Musique musiqueJeu;
	public transient static boolean musiqueCharge; //true si la musique est charge,permet de na pas instancier plusieurs fois la musique
	private transient Refresh refresh;
	private Semaphore semSon;
	private transient boolean sonActif;//true si le son est actif
	
	@SuppressWarnings("static-access")
	public Lanceur(){
		ExecutorService es = Executors.newFixedThreadPool(1);
		this.sem = new Semaphore(0,true);
		this.semSon = new Semaphore(0,true);
		es.execute(this);
		this.plateau= new Plateau();
		this.sonActif = true;
		this.ecranPlateau = new EcranPlateau(this);
		this.game = new Jeu(this.plateau, ecranPlateau);
		this.menu = new EcranMenu(this);
		this.musiqueCharge = false;
		this.screen = new Screen(ecranPlateau,game,this, this.menu, this.semSon);

		this.jouerVSia = false;
		this.ia = 1;
	}
	
	public Screen getScreen(){
		return this.screen;
	}
	
	/**
	 * Relance une partie vierge sur un nouveau plateau
	 */
	public void refresh(){
		this.plateau.reset();
		this.game.refreshGame(plateau);
	}
	
	/**
	 * Lance le mode joueur VS IA
	 * @param boolean mode facile
	 * @param boolean mode moyen
	 * @param boolean mode difficile
	 */
	public void jouerVSia( boolean facile, boolean moyen, boolean difficile){
		if ( this.ia == 1) {
			this.refresh();
		}
		this.jouerVSia = true;
		this.ia = -1;
		this.refreshEcran();
		this.game.jouerIA(facile, moyen, difficile);
	}
	
	public void lanceMenu(){
		this.screen.dessinePlateau(false);
	}
	
	/**
	 * Rafraichit l'ecran requiert une methode render deja execute et l'instance des billes dans l'ecran
	 */
	public void refreshEcran(){
		try {
			this.sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExecutorService es = Executors.newFixedThreadPool(1);
		this.refresh = new Refresh(this.ecranPlateau.getBilles(), this.ecranPlateau, this.musiqueJeu, this.game, this.plateau);
		es.submit(this.refresh);
		game.setRefresh(this.refresh);
	}
	
	/**
	 * Lance le mode joueur VS joueur
	 */
	public void joueurVSjoueur(){
		if ( this.ia == -1 ) {
			this.refresh();
		}
		this.jouerVSia = false;
		this.ia = 1;
		this.refreshEcran();
		this.game.jouer();
	}
	
	/**
	 * previent quand les billes sont chargees
	 */
	public void billeCharge(){
		this.sem.release();
	}
	
	public ArrayList<Bille> instanceBilles(){
		ArrayList<Bille> billes = new ArrayList<Bille>();
		int grille[][] = this.plateau.getPlateau();
		for (int i = 0; i < 13 ; i++){
			for ( int j = 0; j < 21 ; j++){
				if ( grille[i][j] == 1 )
					billes.add(new Bille(Coordonnee.getCoordonne(i, j),TypeBille.WHITE));
				else if ( grille[i][j] == 2 )
					billes.add(new Bille(Coordonnee.getCoordonne(i, j),TypeBille.BLACK));
			}
		}
		return billes;
	}
	
	/**
	 * Sauvegarde une partie, il faut avoir finit son tour pour sauvegarder sinon un bip d'erreur surviendra
	 */
	public void saveGame() {
		try {
			Arborescence arbo = new Arborescence();
			FileOutputStream fichier = new FileOutputStream(arbo.savFile());
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(this);
			oos.flush();
			oos.close();
			}
		catch (java.io.IOException e) {}
		catch ( java.lang.NullPointerException e){}
	}
	
	/**
	 * Charge une partie
	 * Remarque : La serialisation est utilise mais si un jour nous voudrions repredre le projet une méthode de sauvegarde par
	 * JSon ou encore XML serais bien plus interessante car la serialisation depend de la version de la JVM et de la version du projet.
	 */
	public void loadGame () {
		try {
			Arborescence arbo = new Arborescence();
			FileInputStream fichier = new FileInputStream (arbo.openFile());
			ObjectInputStream ois = new ObjectInputStream (fichier);
			Lanceur lanceur = (Lanceur) ois.readObject();
			this.plateau = lanceur.plateau;
			this.game.copieJeu(lanceur.game);
			ois.close();
			if ( this.jouerVSia )
				this.loadJoueurVSia();
			else
				this.loadJoueurVSJoueur();
		}
		catch (java.io.IOException e) {e.printStackTrace();} 
		catch (ClassNotFoundException e) {e.printStackTrace();}
		catch ( java.lang.NullPointerException e){e.printStackTrace();}
	}
	
	/**
	 * Methode appelee par loadGame(), lance dans un thread une partie joueur VS joueur
	 */
	public void loadJoueurVSJoueur(){
		Thread t = new Thread(new Runnable() {
		       
		       public void run() { 
		    	   joueurVSjoueur();
		       }
		       
		});
		 t.start();
		 System.out.println("coucou");
		this.screen.dessinePlateau(true);
	}
	
	/**
	 * Methode appelee par loadGame(), lance dans un thread une partie joueur VS IA
	 */
	public void loadJoueurVSia(){
		Thread t = new Thread(new Runnable() {
		       
		       public void run() { 
		    	   jouerVSia( game.isFacile(), game.isMoyen(), game.isDifficile());
		       }
		});
		 t.start();
		this.screen.dessinePlateau(true);
	}
	
	/**
	 * Lance la musique
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if ( ! abalone.controller.Lanceur.musiqueCharge ) {
			try {
				this.semSon.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		musiqueJeu = new Musique();
    		this.semSon.release();
    		abalone.controller.Lanceur.musiqueCharge= true;
    	}
		musiqueJeu.playMusiqueFond();
	}
	
	/**
	 * Methode appelee quand on veut quitter une partie
	 */
	public void stopRefresh(){
		this.refresh.setFinPartie(true);
	}

	/**
	 * Quand il y a un gagnant cette fonction est appelee
	 */
	public void stopLaGagne() {
		// TODO Auto-generated method stub
		this.musiqueJeu.stopGagneIA();
		this.musiqueJeu.stopGagneJoueur();
		this.ecranPlateau.setGagnant(false);
	}
	
	public void coupeSon(){
		this.sonActif = false;
		this.musiqueJeu.stop();
	}
	
	public void activeSon(){
		this.sonActif = true;
		this.musiqueJeu.setSonActif(true);
		ExecutorService es = Executors.newFixedThreadPool(1);
		es.submit(this);
		this.semSon.release();
		//f.cancel(true);
	}
	
	public boolean getSonActif(){
		return this.sonActif;
	}
	
	public void setSonActif( boolean set ){
		this.sonActif = set;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
