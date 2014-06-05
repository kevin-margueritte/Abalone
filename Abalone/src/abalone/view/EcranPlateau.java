package abalone.view;

import java.util.ArrayList;
import abalone.controller.Lanceur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Classe permettant d'afficher l'ecran principal du jeu
 * 
 * @author groupe du projet
 * @mail kevin.margueritte@gmail.com
 *
 * Remarque : Des ecouteurs sont places sur chaque fonction de la classe
 */

public class EcranPlateau implements Screen{
	private OrthographicCamera camera; //camera
	private SpriteBatch batch; //ecran ou sera dessine les images
	private Sprite spriteAbalone; //image plateau
	private Sprite spriteFond; //image de fond
	private Sprite spriteTour; //image de la fleche du tour 
	private ArrayList<Bille> listeBilles; //content toute les billes du jeu
	private BitmapFont fontNbTours; //affiche le nombre de deplacement
	private String nbDeplacement;
	private Lanceur lanceur; 
	private Sprite spriteQuitter; //image du bouton pour revenir a l'ecran principal
	private Sprite jbGagne; //image affiche quand le joueur noir gagne
	private Sprite jnGagne; //image affiche quand le joueur blanc gagne
	private boolean noirGagne; //true si le joueur noir gagne
	private boolean blancGagne;//true si le joueur blanc gagne
	private InputAbalone input; //
	private Sprite spriteSon; //image du haut-parleur, affiche quand le son est active
	private Sprite sonCoupe;//image du haut-parleur, affiche quand le son est desactive

	public EcranPlateau(Lanceur lanceur) {
		this.listeBilles = new ArrayList<Bille>();
		this.lanceur = lanceur;
		this.noirGagne =  false;
		this.blancGagne = false;
	};

	/**
	 * Rafraichit le GPU, la methode est appelee en boucle
	 */
	@Override
	public void render(float delta) {
		Bille stock;
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_RED_BITS);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		camera.update();
		spriteFond.draw(batch);
		spriteAbalone.draw(batch);
		spriteTour.draw(batch);
		spriteQuitter.draw(batch);
		if ( this.lanceur.getSonActif() )
			spriteSon.draw(batch);
		else
			sonCoupe.draw(batch);
		for (int i = 0; i < this.listeBilles.size(); i ++) {
			stock = this.listeBilles.get(i);
			if ( stock.getDraw()){
				stock.getBille().draw(batch);
			}
		}
		if ( this.blancGagne )
			this.jbGagne.draw(batch);
		else if ( this.noirGagne )
			this.jnGagne.draw(batch);
		this.fontNbTours.setScale(spriteTour.getScaleX(), spriteTour.getScaleY());
		fontNbTours.draw(batch, this.nbDeplacement, spriteTour.getBoundingRectangle().getX() + spriteTour.getBoundingRectangle().getWidth() / 2 ,
				spriteTour.getBoundingRectangle().getY() + spriteTour.getBoundingRectangle().getHeight() / 2 );
		batch.end();
	}

	/**
	 * Methode appelee quand la fenetre est redimensionne
	 * les images suivent une equation de redimensionnement
	 */
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		camera.update();
		for ( int index = 0 ; index < this.listeBilles.size(); index ++){
			this.listeBilles.get(index).resize();
		}
		if (height < width) {
			spriteAbalone.setScale(height / 700f);
			this.spriteTour.setScale(height / 700f);
			this.spriteTour.setPosition(((width - 700) / 2) + 600
					* (height / 700f), ((height - 700) / 2) + 30
					* (height / 700f));
			this.spriteQuitter.setScale(height / 700f);
			this.spriteQuitter.setPosition(((width - 700) / 2) + 660
					* (height / 700f), ((height - 700) / 2) + 665
					* (height / 700f));
			this.spriteSon.setScale(height / 700f);
			this.spriteSon.setPosition(((width - 700) / 2) + 575
					* (height / 700f), ((height - 700) / 2) + 670
					* (height / 700f));
			this.sonCoupe.setScale(height / 700f);
			this.sonCoupe.setPosition(((width - 700) / 2) + 575
					* (height / 700f), ((height - 700) / 2) + 670
					* (height / 700f));
			this.jbGagne.setScale(height / 700f);
			this.jbGagne.setPosition(((width - 700) / 2) + 1
					* (height / 700f), ((height - 700) / 2) + 350
					* (height / 700f));
			this.jnGagne.setScale(height / 700f);
			this.jnGagne.setPosition(((width - 700) / 2) + 20
					* (height / 700f), ((height - 700) / 2) + 350
					* (height / 700f));
		} else {
			spriteAbalone.setScale(width / 700f);
			this.spriteTour.setPosition(((width - 700) / 2) + 600
					* (width / 700f), ((height - 700) / 2) + 30
					* (width / 700f));
			this.spriteTour.setScale(width / 700f);
			this.spriteQuitter.setScale(width / 700f);
			this.spriteQuitter.setPosition(((width - 700) / 2) + 660
					* (width / 700f), ((height - 700) / 2) +  665
					* (width / 700f));
			this.spriteTour.setScale(width / 700f);
			this.spriteSon.setScale(width / 700f);
			this.spriteSon.setPosition(((width - 700) / 2) + 575
					* (width / 700f), ((height - 700) / 2) +  670
					* (width / 700f));
			this.sonCoupe.setScale(width / 700f);
			this.sonCoupe.setPosition(((width - 700) / 2) + 575
					* (width / 700f), ((height - 700) / 2) +  670
					* (width / 700f));
			this.jbGagne.setScale(width / 700f);
			this.jbGagne.setPosition(((width - 700) / 2) + 1
					* (width / 700f), ((height - 700) / 2) +  350
					* (width / 700f));
			this.jnGagne.setScale(width / 700f);
			this.jnGagne.setPosition(((width - 700) / 2) + 20
					* (width / 700f), ((height - 700) / 2) +  350
					* (width / 700f));
		}
		spriteFond.setScale(width / 1000f, height / 1000f);
		spriteAbalone.setPosition((width - 700) / 2, (height - 700) / 2);
	}

	/**
	 * Methode appelee quand on demande un affichage de l'ecran
	 */
	@Override
	public void show() {
		this.nbDeplacement = "0";
		this.fontNbTours = new BitmapFont();//new BitmapFont();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.batch = new SpriteBatch();
		this.spriteAbalone = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/AbaloneCS5.gif")), 0, 0, 704, 704));
		this.spriteTour = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/tour.png")), 0, 0, 400, 400));
		this.spriteTour.setSize(90, 90);
		this.spriteTour.setOrigin(704 / 2, 704 / 2);
		this.spriteAbalone.setSize(704, 704);
		this.spriteAbalone.setOrigin(704 / 2, 704 / 2);
		this.spriteFond = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/parquet.png")), 0, 0, 1000, 1000));
		this.spriteFond.setSize(1000, 1000);
		this.spriteFond.setOrigin(0, 0);
		this.listeBilles = lanceur.instanceBilles();
		this.chargerBilles();
		this.spriteQuitter = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/quitter.png")), 0, 0, 48, 48));
		this.spriteQuitter.setSize(35, 35);
		this.spriteQuitter.setOrigin(704/2, 704/2);
		this.spriteSon = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/son.png")), 0, 0, 598, 480));
		this.spriteSon.setSize(50, 28);
		this.spriteSon.setOrigin(704/2, 704/2);
		this.sonCoupe = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/sonCoupe.png")), 0, 0, 598, 480));
		this.sonCoupe.setSize(50, 28);
		this.sonCoupe.setOrigin(704/2, 704/2);
		this.jbGagne = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/jbg.png")), 0, 0, 1324, 129));
		this.jbGagne.setSize(700, 70);
		this.jbGagne.setOrigin(704/2, 704/2);
		this.jnGagne = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/jng.gif")), 0, 0, 1324, 129));
		this.jnGagne.setSize(700, 70);
		this.jnGagne.setOrigin(704/2, 704/2);
		/***/
	}
	/**Methode appelee quand les billes sont charges**/
	public void chargerBilles(){
		this.lanceur.billeCharge();
	}
	
	/**Methode appelee quand un joueur gagne**/
	public void quitteJeu(){
		this.jnGagne.setColor(Color.RED);
		this.input.setFinPartie(true);
		this.fontNbTours.setColor(Color.DARK_GRAY);
		this.spriteTour.setColor(Color.DARK_GRAY);
		this.spriteAbalone.setColor(Color.DARK_GRAY);
		this.spriteFond.setColor(Color.DARK_GRAY);
		this.spriteSon.setColor(Color.DARK_GRAY);
		this.spriteQuitter.setColor(Color.DARK_GRAY);
		for (int i = 0; i < this.listeBilles.size(); i ++) {
			if ( this.listeBilles.get(i).getDraw()){
				this.listeBilles.get(i).getBille().setColor(Color.DARK_GRAY);
			}
		}
	}

	@Override
	public void hide() {
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
	
	/**SETTERS**/
	
	public void setNbDeplacement(String nbTours){
		this.nbDeplacement = nbTours;
	}
	
	public void setGagnant( boolean set ) {
		this.blancGagne = set;
		this.noirGagne = set;
	}

	public void setNoirGagne(boolean noirGagne) {
		this.noirGagne = noirGagne;
		this.quitteJeu();
	}
	
	public void setInput(InputAbalone inputEcranJeu) {
		// TODO Auto-generated method stub
		this.input = inputEcranJeu;
	}

	public void setBlancheGagne(boolean blancGagne) {
		this.blancGagne = blancGagne;
		this.quitteJeu();
	}
	
	/**GETTERS**/
	
	public ArrayList<Bille> getBilles(){
		return this.listeBilles;
	}

	public boolean isNoirGagne() {
		return noirGagne;
	}

	public boolean isBlancGagne() {
		return blancGagne;
	}
}
