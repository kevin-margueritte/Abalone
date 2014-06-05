package abalone.view;



import java.util.concurrent.Semaphore;

import abalone.controller.Jeu;
import abalone.controller.Lanceur;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * Classe qui gere toute les vues
 * @author groupe projet
 * @mail kevin.margueritte@gmail.com
 */

public class Screen extends Game implements ApplicationListener{
	private EcranPlateau screenPlateau;
	private InputAbalone inputEcranJeu;
	@SuppressWarnings("unused")
	private Jeu game;
	private EcranMenu ecranMenu;
	private InputMenu inputMenu;
	@SuppressWarnings("unused")
	private boolean dessineMenu;
	private Lanceur lanceur;
	private Semaphore semSon;
	

	/**
	 * Methode appelee quand la fenetre s'ouvre, initialise l'ecran du menu et relie l'ecran avec la gestion clavier/souris du menu
	 */
	public Screen(EcranPlateau ecranPlateau, Jeu game, Lanceur lanceur, EcranMenu menu, Semaphore semSon){
		this.inputEcranJeu = new InputAbalone(game, lanceur, this);
		this.game = game;
		this.screenPlateau = ecranPlateau;
		this.screenPlateau.setInput(this.inputEcranJeu);
		this.ecranMenu = menu;
		this.inputMenu = new InputMenu(lanceur, menu, this);
		this.dessineMenu = true;
		this.lanceur = lanceur;
		this.semSon = semSon;
	}
	
	public void setGame( Jeu game, EcranPlateau ecranPlateau, EcranMenu menu ){
		this.inputEcranJeu = new InputAbalone(game, lanceur, this);
		this.game = game;
		this.screenPlateau = ecranPlateau;
		this.ecranMenu = menu;
		this.inputMenu = new InputMenu(lanceur, menu, this);
		this.dessineMenu = true;
		this.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public Screen(){
		this.ecranMenu = new EcranMenu();
	}
	
	public void setMenuIA( boolean set ){
		System.out.println("hello");
		this.setMenuIA(set);
	}
	

	/**
	 * Methode appelee quand la fenetre s'ouvre, initialise l'ecran du menu et relie l'ecran avec la gestion clavier/souris du menu
	 */
	@Override
	public void create() {
		this.semSon.release();
		try {
			this.semSon.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setScreen(this.ecranMenu);
		Gdx.input.setInputProcessor(inputMenu);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	/**
	 * Methode appelee automatiquement quand la fenetre est redimensionnee et met à jour
	 * les vues
	 */
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		inputMenu.resize(width, height);
		inputEcranJeu.resize(width, height);
	}
	
	/**
	 * Methode permettant de dessiner le plateau
	 * @param boolean set true si on affiche l'ecran principal du jeu, false pour le menu
	 */
	public void dessinePlateau( boolean set ){
		if ( set ) {
			super.setScreen(this.screenPlateau);
			Gdx.input.setInputProcessor(inputEcranJeu);
		}
		else {
			super.setScreen(this.ecranMenu);
			Gdx.input.setInputProcessor(inputMenu);
			this.inputMenu.setMenuIA(false);
			this.ecranMenu.setBoutonsIA(false);
		}
	}

	/**
	 * Methode pour Android quand on appuie sur le bouton home
	 */
	@Override
	public void pause() {
		this.inputMenu.setMenuIA(false);
		super.pause();
	}

	/**
	 * Methode pour Android qui reprend le jeu sans changer son etat quand on reprend l'application
	 */
	@Override
	public void resume() {
		this.inputMenu.setMenuIA(false);
		super.resume();
	}

	
}
		
		