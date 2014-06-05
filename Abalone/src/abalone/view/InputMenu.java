package abalone.view;

import com.badlogic.gdx.InputProcessor;
import abalone.controller.Lanceur;

/**
 * Classe qui est a l'ecoute du clavier et de la souris pour le menu du jeu
 * @author groupe projet
 * @mail kevin.margueritte@gmail.com
 */

public class InputMenu implements InputProcessor{
	
	private float scrX; //taille x de l'ecran
	private float scrY;//taille y de l'ecran
	protected Lanceur lanceur;
	private EcranMenu menu;
	private Screen screen;
	private boolean menuIA; //true si le menu est dessine, false sinon

	public InputMenu ( Lanceur lanceur, EcranMenu menu, Screen screen) {
		this.lanceur = lanceur;
		this.menu = menu;
		this.screen = screen;
		this.menuIA = false;
	}
	
	public void setMenuIA( boolean set ){
		this.menuIA = set;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Methode appelee quand il y a un clique de souris
	 */
	@Override
	public boolean touchDown(int arg0, int arg1, int pointer, int button) {
		System.out.println(arg0);
		System.out.println(arg1);
		Thread t = null;
		if (arg0 > this.setX(237) && arg0 < this.setX(463)
				&& arg1 > this.setY(318) && arg1 < this.setY(392)) {
			if ( !this.menuIA ) {
				t = new Thread(new Runnable() {
				       
				       public void run() { 
				    	   lanceur.joueurVSjoueur(); 
				       }
				       
				});
				 t.start();
				this.screen.dessinePlateau(true);
			}
			else {
				t = new Thread(new Runnable() {
				       
				       public void run() { 
				    	   lanceur.jouerVSia(true, false, false);
				       }
				});
				 t.start();
				this.screen.dessinePlateau(true);
			}
		}
		else if (arg0 > this.setX(237) && arg0 < this.setX(463)
				&& arg1 > this.setY(443) && arg1 < this.setY(518)) {
			if ( !this.menuIA ){
				this.menu.setBoutonsIA(true);
				this.menuIA = true;
			}
			else {
				t = new Thread(new Runnable() {
				       
				       public void run() { 
				    	   lanceur.jouerVSia(false, true, false);
				       }
				});
				 t.start();
				this.screen.dessinePlateau(true);
			}
		}
		else if (arg0 > this.setX(237) && arg0 < this.setX(463)
				&& arg1 > this.setY(494) && arg1 < this.setY(566)) {
			if ( !this.menuIA ){
				this.menu.setBoutonsIA(true);
				this.menuIA = true;
			}
			else {
				t = new Thread(new Runnable() {
				       
				       public void run() { 
				    	   lanceur.jouerVSia(false, true, false);
				       }
				});
				 t.start();
				this.screen.dessinePlateau(true);
			}
		}
		else if (arg0 > this.setX(237) && arg0 < this.setX(463)
				&& arg1 > this.setY(562) && arg1 < this.setY(639)) {
			if ( !this.menuIA ){
				this.menu.setBoutonsIA(true);
				this.menuIA = true;
			}
			else{
				//this.lanceur.loadGame();
				t = new Thread(new Runnable() {
				       
				       public void run() { 
				    	   lanceur.jouerVSia(false, false, true);
				       }
				});
				 t.start();
				this.screen.dessinePlateau(true);
			}
		}
		else if ( arg0 > this.setX(574) && arg0 < this.setX(620) && 
				arg1 > this.setY(6) && arg1 < this.setY(32)){
			if ( this.lanceur.getSonActif() )
				this.lanceur.coupeSon();
			else
				this.lanceur.activeSon();
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Methode appelee quand il y a un redimensionnement de la fenetre et recupere la taille
	 * @param scrx
	 * @param scry
	 */
	public void resize(int scrx, int scry) {
		this.scrX = scrx;
		this.scrY = scry;
	}

	/**
	 * @param  int x
	 * @return float la position y en fonction de la fenetre
	 */
	public float setX(float x) {
		if (this.scrX < this.scrY) {
			return x * (this.scrX / 700);
		} else {
			return ((this.scrX - this.scrY) / 2) + x * (this.scrY / 700);
		}
	}

	/**
	 * @param  int y
	 * @return float la position y en fonction de la fenetre
	 */
	public float setY(float y) {
		if (this.scrX < this.scrY) {
			return ((this.scrY - this.scrX) / 2) + y * (this.scrX / 700);
		} else {
			return y * (this.scrY / 700);
		}
	}

}
