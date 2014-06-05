package abalone.view;


import abalone.controller.Coordonnee;
import abalone.controller.TypeBille;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bille implements Runnable{
	private Sprite bille;
	private Coordonnee coorInit;
	private TypeBille typeBille;
	private Coordonnee coorFin;
	private Color couleur;
	private boolean select;
	private boolean draw; //droit d'etre dessine
	
	public Bille(Coordonnee coor, TypeBille typeBille){
		this.select = false;
		this.draw = true;
		this.coorInit = coor;
		this.typeBille = typeBille;
		if( typeBille == TypeBille.BLACK){
			this.bille = new Sprite(new TextureRegion(new Texture(
					Gdx.files.internal("data/billenoir.gif")), 0, 0, 200, 200));
		}
		else {
			this.bille = new Sprite(new TextureRegion(new Texture(
					Gdx.files.internal("data/billeblanche.gif")), 0, 0, 200, 200));
		}
		this.bille.setSize(75, 75);
		this.bille.setOrigin(704 / 2, 704 / 2);
		this.resize();
		this.couleur = Color.WHITE;
	}
	
	public void setSelect( boolean set ){
		this.select = set;
	}
	
	public Bille(){}
	
	public void setCoordonneeFinal(Coordonnee coor){
		this.coorFin = coor;
	}
	
	/**
	 * Methode appele pour redimensionner les billes en fonction de la fenetre
	 */
	public void resize() {
		int height = Gdx.graphics.getHeight();
		int width = Gdx.graphics.getWidth() ;
		if (height < width) {
			this.bille.setScale(height / 700f);
			this.bille.setPosition(((width - 700) / 2) + this.coorInit.getX()
					* (height / 700f), ((height - 700) / 2) + this.coorInit.getY()
					* (height / 700f));
		} else {
			this.bille.setScale(width / 700f);
			this.bille.setPosition(((width - 700) / 2) + this.coorInit.getX()
					* (width / 700f), ((height - 700) / 2) + this.coorInit.getY()
					* (width / 700f));
		}
	}

	public Sprite getBille() {
		return bille;
	}
	
	public boolean getDraw(){
		return this.draw;
	}
	
	public void setDraw(boolean accept){
		this.draw = accept;
		if ( !accept ) {
			this.coorInit = Coordonnee.COOR_TEST;
			this.coorFin = Coordonnee.COOR_TEST;
		}	
	}

	public void setBille(Sprite bille) {
		this.bille = bille;
	}

	public Coordonnee getCoor() {
		return coorInit;
	}

	public void setCoor(Coordonnee coor) {
		this.coorInit = coor;
		this.resize();
	}

	public TypeBille getTypeBille() {
		return typeBille;
	}

	public void setTypeBille(TypeBille typeBille) {
		this.typeBille = typeBille;
	}	
	
	public int getIinit(){
		return this.coorInit.getI();
	}
	
	public int getJinit(){
		return this.coorInit.getJ();
	}
	
	public int getIfin(){
		return this.coorFin.getI();
	}
	
	public int getJfin(){
		return this.coorFin.getJ();
	}
	
	public int getXinit(){
		return this.coorInit.getX();
	}
	
	public int getYinit(){
		return this.coorInit.getY();
	}
	
	public int getXfin(){
		return this.coorFin.getX();
	}
	
	public int getYfin(){
		return this.coorFin.getY();
	}

	/**
	 * Appele des que l'on veut deplacer une bille donne un effet de glissade quand une bille est deplacé
	 * Cette methode utilise l'eqation du droite du type y = mx + p
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int yb = this.getYfin();
		int ya = this.getYinit();
		int xb = this.getXfin();
		int xa = this.getXinit();
		Coordonnee.COOR_TEST.setX(xa);
		Coordonnee.COOR_TEST.setY(ya);
		this.setCoor(Coordonnee.COOR_TEST);
		int m = (yb-ya)/(xb-xa);
		int p = ya - m*xa;
		if ( xa > xb ) {
			for ( int i = xa; i >= xb; i--){
			this.coorInit.setX(i);
			this.coorInit.setY(m*i +p);
				this.setCoor(Coordonnee.COOR_TEST);
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					System.out.println("exception levé");
				}
			}
		}
		else {
			for ( int i = xa; i <= xb; i++){
				this.coorInit.setX(i);
				this.coorInit.setY(m*i +p);
				this.setCoor(Coordonnee.COOR_TEST);
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					System.out.println("exception levé");
				}
			}
			
		}
		this.setCoor(this.coorFin);
	}
	
	/**
	 * Colorie la bille selectionnee
	 */
	public void select(){
		this.select = true;
		this.couleur = Color.GREEN;
		this.bille.setColor(Color.GREEN);
	}
	
	/**
	 * Colorie la bille selectionnee avec la couleur passé en paramètre
	 */
	public void select(Color couleur){
		this.select = true;
		this.couleur = couleur;
		this.bille.setColor(couleur);
	}
	
	/**
	 * Retablie la couleur d'origine
	 */
	public void deSelectionne(){
		this.select = false;
		this.bille.setColor(new Color(1,1,1,1));
	}

	public boolean getSelect() {
		// TODO Auto-generated method stub
		return this.select;
	}
	
	public Color getCouleur(){
		return this.couleur;
	}
	
}
