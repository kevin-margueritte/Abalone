package abalone.view;

import abalone.controller.Lanceur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Classe permettant d'afficher le menu du jeu
 * 
 * @author groupe du projet
 * @mail kevin.margueritte@gmail.com
 *
 * Remarque : Des ecouteurs sont places sur chaque fonction de la classe
 */
public class EcranMenu implements Screen{
	private Sprite boutonJouer; //image du bouton jouer
	private OrthographicCamera camera; //camera
	private SpriteBatch batch; //ecran ou sera dessine les images
	private Lanceur lanceur;
	private Sprite spriteFond; //image de fond
	private Sprite boutonJouerIA; //image jouer vs ia
	private Sprite boutonFacile; //image du mode facile
	private Sprite boutonMoyen;//image du mode moyen
	private Sprite boutonDifficile;//image du mode difficile
	private boolean boutonsIA; //true pour afficher le menu IA avec le choix des differents mode de difficulte
	private Sprite titre; //image du titre du jeu
	private Sprite spriteSon; //image du son
	private Sprite sonCoupe; //true si le son est acrtif, false sinon
	
	public EcranMenu ( Lanceur lanceur ){
		this.lanceur = lanceur;
		this.boutonsIA =  false;
	}
	
	public EcranMenu(){
		this.boutonsIA = false;
	}
	
	/**
	 * Rafraichit le GPU, la methode est appelee en boucle
	 */
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_RED_BITS);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		camera.update();
		spriteFond.draw(batch);
		titre.draw(batch);
		if ( !this.boutonsIA ) {
			boutonJouer.draw(batch);
			boutonJouerIA.draw(batch);
		}
		else {
			boutonFacile.draw(batch);
			boutonMoyen.draw(batch);
			boutonDifficile.draw(batch);
		}
		if ( this.lanceur.getSonActif() )
			spriteSon.draw(batch);
		else
			sonCoupe.draw(batch);
		batch.end();
	}

	/**
	 * Methode appelee quand la fenetre est redimensionne
	 * les images suivent une equation de redimensionnement
	 */
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		camera.setToOrtho(false, width, height);
		camera.update();
		spriteFond.setScale(width / 1000f, height / 1000f);
		if (height < width) {
			this.boutonJouer.setScale(height / 700f);
			this.boutonJouer.setPosition(((width - 700) / 2) + 235
					* (height / 700f), ((height - 700) / 2) + 80
					* (height / 700f));
				this.boutonJouerIA.setScale(height / 700f);
				this.boutonJouerIA.setPosition(((width - 700) / 2) + 235
						* (height / 700f), ((height - 700) / 2) + 250
						* (height / 700f));
				this.boutonDifficile.setScale(height / 700f);
				this.boutonDifficile.setPosition(((width - 700) / 2) + 235
						* (height / 700f), ((height - 700) / 2) + 10
						* (height / 700f));
				this.boutonMoyen.setScale(height / 700f);
				this.boutonMoyen.setPosition(((width - 700) / 2) + 235
						* (height / 700f), ((height - 700) / 2) + 130
						* (height / 700f));
				this.boutonFacile.setScale(height / 700f);
				this.boutonFacile.setPosition(((width - 700) / 2) + 235
						* (height / 700f), ((height - 700) / 2) + 250
						* (height / 700f));
				this.titre.setScale(height / 700f);
				this.titre.setPosition(((width - 700) / 2) + 130
						* (height / 700f), ((height - 700) / 2) + 470
						* (height / 700f));
				this.spriteSon.setScale(height / 700f);
				this.spriteSon.setPosition(((width - 700) / 2) + 575
						* (height / 700f), ((height - 700) / 2) + 670
						* (height / 700f));
				this.sonCoupe.setScale(height / 700f);
				this.sonCoupe.setPosition(((width - 700) / 2) + 575
						* (height / 700f), ((height - 700) / 2) + 670
						* (height / 700f));
			}
		else {
			this.boutonJouer.setScale(width / 700f);
			this.boutonJouer.setPosition(((width - 700) / 2) + 235
					* (width / 700f), ((height - 700) / 2) + 80
					* (width / 700f));
				this.boutonJouerIA.setScale(width / 700f);
				this.boutonJouerIA.setPosition(((width - 700) / 2) + 235
						* (width / 700f), ((height - 700) / 2) + 250
						* (width / 700f));
				this.boutonDifficile.setScale(width / 700f);
				this.boutonDifficile.setPosition(((width - 700) / 2) + 235
						* (width / 700f), ((height - 700) / 2) + 10
						* (width / 700f));
				this.boutonMoyen.setScale(width / 700f);
				this.boutonMoyen.setPosition(((width - 700) / 2) + 235
						* (width / 700f), ((height - 700) / 2) + 130
						* (width / 700f));
				this.boutonFacile.setScale(width / 700f);
				this.boutonFacile.setPosition(((width - 700) / 2) + 235
						* (width / 700f), ((height - 700) / 2) + 250
						* (width / 700f));
				this.titre.setScale(width / 700f);
				this.titre.setPosition(((width - 700) / 2) + 130
						* (width / 700f), ((height - 700) / 2) + 470
						* (width / 700f));
				this.spriteSon.setScale(width / 700f);
				this.spriteSon.setPosition(((width - 700) / 2) + 575
						* (width / 700f), ((height - 700) / 2) +  670
						* (width / 700f));
				this.sonCoupe.setScale(width / 700f);
				this.sonCoupe.setPosition(((width - 700) / 2) + 575
						* (width / 700f), ((height - 700) / 2) +  670
						* (width / 700f));
		}
	}

	
	/**
	 * Methode appelee quand on demande un affichage de l'ecran
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub
		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.boutonJouer = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/JcIA.gif")), 0, 0, 454, 340));
		this.boutonJouer.setSize(227, 170);
		this.boutonJouer.setOrigin(704 / 2, 704 / 2);
		this.spriteFond = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/parquet.png")), 0, 0, 1000, 1000));
		this.spriteFond.setSize(1000, 1000);
		this.spriteFond.setOrigin(0, 0);
		this.boutonJouerIA = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/JcJ.gif")), 0, 0, 454, 340));
		this.boutonJouerIA.setSize(227, 170);
		this.boutonJouerIA.setOrigin(704 / 2, 704 / 2);
		this.boutonFacile = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/Facil.gif")), 0, 0, 454, 340));
		this.boutonFacile.setSize(227, 170);
		this.boutonFacile.setOrigin(704 / 2, 704 / 2);
		this.boutonMoyen = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/Moyen.gif")), 0, 0, 454, 340));
		this.boutonMoyen.setSize(227, 170);
		this.boutonMoyen.setOrigin(704 / 2, 704 / 2);
		this.boutonDifficile = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/Difficile.gif")), 0, 0, 454, 340));
		this.boutonDifficile.setSize(227, 170);
		this.boutonDifficile.setOrigin(704 / 2, 704 / 2);
		this.titre = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/TitreAba.gif")), 0, 0, 1500, 700));
		this.titre.setSize(428, 200);
		this.titre.setOrigin(704 / 2, 704 / 2);
		this.spriteSon = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/son.png")), 0, 0, 598, 480));
		this.spriteSon.setSize(50, 28);
		this.spriteSon.setOrigin(704/2, 704/2);
		this.sonCoupe = new Sprite(new TextureRegion(new Texture(
				Gdx.files.internal("data/sonCoupe.png")), 0, 0, 598, 480));
		this.sonCoupe.setSize(50, 28);
		this.sonCoupe.setOrigin(704/2, 704/2);
	}
	
	public void setBoutonsIA(boolean set){
		this.boutonsIA =  set;
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
	

}
