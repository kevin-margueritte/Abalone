package abalone.main;


import java.awt.Cursor;
import java.awt.Dimension;
import java.util.Scanner;

import abalone.controller.Jeu;
import abalone.controller.Lanceur;
import abalone.controller.Refresh;
import abalone.controller.TypeBille;
import abalone.model.Plateau;
import abalone.view.EcranPlateau;
import abalone.view.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;


public class Main {


	public static void main(String[] args) {
		/*LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		//cfg.fullscreen = true;
		//cfg.setFromDisplayMode (DisplayMode(480, 320, 10, 8) );
		//
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		cfg.title = "GdxTest";
		//cfg.fullscreen = true;
		cfg.fullscreen = false;
		// vSync
		//cfg.vSyncEnabled = true;
		//cfg.resizable = false;
		//cfg.width = (int)tailleEcran.getWidth();
		//cfg.height = (int)tailleEcran.getHeight();
		cfg.useGL20 = true;
		cfg.width = 704;
		cfg.height = 704;
		/*Jeu game = new Jeu("keke","boy",new Plateau());
		EcranPlateau screen = new EcranPlateau(game.getGrille());
		game.setImage(screen);*/
		/*EcranPlateau ecranPlateau = new EcranPlateau();
		Jeu game = new Jeu("J1", "J2", new Plateau());
		//game.jouer();
		new LwjglApplication(new Screen(ecranPlateau,game), cfg);	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.setRefresh(new Refresh(ecranPlateau.getBilles()));
		game.jouer();
		/*****IA*******/
		/*Scanner sc =  new Scanner(System.in);
		if ( sc.nextInt() == 0 ){*/
		Lanceur lanc = new Lanceur();
		
		//}
		//else
		
		//IA();
	}
	
	/*public static void graphe(){
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "GdxTest";
		cfg.useGL20 = true;
		cfg.width = 704;
		cfg.height = 704;
		EcranPlateau ecranPlateau = new EcranPlateau();
		Jeu game = new Jeu("J1", "J2", new Plateau());
		Screen screen = new Screen(ecranPlateau,game);
		LwjglApplication monApp = new LwjglApplication(screen, cfg);	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//LwjglCanvas test = new LwjglCanvas(screen, true);
		//test.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cfg.fullscreen = true;
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		game.setRefresh(new Refresh(ecranPlateau.getBilles(), ecranPlateau));
		game.jouer();
	}*/
	
	/*public static void IA(){
		MinMax test = new MinMax(new Plateau());
		test.ordiVSordi();
	}*/
}
