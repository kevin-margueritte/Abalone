package abalone.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Classe contenant toutes les musiques du jeu
 * @author groupe projet
 * @mail kevin.margueritte@gmail.com
 *
 */

public class Musique {
	private Music deplacement;//son du deplacment des billes
	private Music musiqueJeu;// musique de fon
	private Music gagneIA; //musique quand gagne l'IA
	private Music gagne;//musique quand gagne un joueur
	private Music erreur;//son d'erreur
	private boolean sonActif;//true si le son doit etre actif, false sinon
	
	public Musique(){
		this.sonActif = true;
		this.deplacement = Gdx.audio.newMusic(Gdx.files.internal("data/break.mp3"));
		this.musiqueJeu = Gdx.audio.newMusic(Gdx.files.internal("data/musiqueFond.mp3"));
		this.gagneIA = Gdx.audio.newMusic(Gdx.files.internal("data/sonIA.mp3"));
		this.gagne = Gdx.audio.newMusic(Gdx.files.internal("data/gagneJoueur.mp3"));
		this.erreur = Gdx.audio.newMusic(Gdx.files.internal("data/erreur.mp3"));
	}
	
	public void playDeplacement(){
		if ( this.sonActif )
			this.deplacement.play();
	}
	
	public void playErreur(){
		if ( this.sonActif )
			this.erreur.play();
	}
	
	public void playMusiqueFond(){
		if ( this.sonActif ){
			this.musiqueJeu.setLooping(true);
			this.musiqueJeu.play();
		}
	}
	
	public void playGagneIA(){
		if ( this.sonActif ) {
			this.gagneIA.play();
			this.gagneIA.setLooping(true);
		}
	}
	
	public void stopGagneIA(){
		this.gagneIA.stop();
	}
	
	public void stopGagneJoueur(){
		this.gagne.pause();
	}
	
	public void stopMusiqueFond(){
		this.musiqueJeu.pause();
	}
	
	public void playGagneJoueur(){
		if ( this.sonActif ){
			this.gagne.setLooping(true);
			this.gagne.play();
		}
	}
	
	public void stop(){
		this.sonActif = false;
		this.deplacement.pause();
		this.musiqueJeu.pause();
		this.gagneIA.pause();
		this.gagne.pause();
	}

	public void setSonActif(boolean set) {
		// TODO Auto-generated method stub
		this.sonActif = set;
	}
}
