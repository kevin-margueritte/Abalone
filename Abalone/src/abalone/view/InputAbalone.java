package abalone.view;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import abalone.controller.Coordonnee;
import abalone.controller.Jeu;
import abalone.controller.Lanceur;
import com.badlogic.gdx.InputProcessor;

/**
 * Classe qui est a l'ecoute du clavier et de la souris pour l'ecran principal du jeu
 * @author groupe projet
 * @mail kevin.margueritte@gmail.com
 */

public class InputAbalone implements InputProcessor{

	private float scrX;//taille X de l'ecran
	private float scrY;//taille Y de l'ecran
	private Jeu game;
	private Lanceur lanceur;
	private Screen screen;
	private boolean finPartie;

	public InputAbalone(Jeu game, Lanceur lanceur, Screen screen) {
		this.game = game;
		this.lanceur = lanceur;
		this.screen = screen;
		this.finPartie = false;
	}
	
	public void setFinPartie( boolean set ){
		this.finPartie = set;
	}

	@Override
	public boolean keyDown(int codeCle) {
		return false;
	}

	@Override
	public boolean keyTyped(char caractere) {
		return false;
	}

	@Override
	public boolean keyUp(int codeCle) {
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		return false;
	}

	/**
	 * Methode appelee quand il y a un clique de souris
	 */
	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		if ( this.finPartie){
			this.screen.dessinePlateau(false);
			this.lanceur.refresh();
			this.finPartie = false;
			this.lanceur.stopLaGagne();
			ExecutorService es = Executors.newFixedThreadPool(1);
			es.execute(this.lanceur);
		}	
		else if (arg0 > this.setX(216) && arg0 < this.setX(267)
				&& arg1 > this.setY(103) && arg1 < this.setY(154)) {
			this.game.setInput(Coordonnee.COOR_26,false);
		} else if (arg0 > this.setX(273) && arg0 < this.setX(322)
				&& arg1 > this.setY(103) && arg1 < this.setY(153)) {
			this.game.setInput(Coordonnee.COOR_28,false);
		} else if (arg0 > this.setX(327) && arg0 < this.setX(378)
				&& arg1 > this.setY(103) && arg1 < this.setY(153)) {
			this.game.setInput(Coordonnee.COOR_210,false);
		} else if (arg0 > this.setX(383) && arg0 < this.setX(432)
				&& arg1 > this.setY(103) && arg1 < this.setY(153)) {
			this.game.setInput(Coordonnee.COOR_212,false);
		} else if (arg0 > this.setX(439) && arg0 < this.setX(488)
				&& arg1 > this.setY(103) && arg1 < this.setY(153)) {
			this.game.setInput(Coordonnee.COOR_214,false);
		} else if (arg0 > this.setX(190) && arg0 < this.setX(239)
				&& arg1 > this.setY(154) && arg1 < this.setY(202)) {
			this.game.setInput(Coordonnee.COOR_35,false);
		} else if (arg0 > this.setX(246) && arg0 < this.setX(293)
				&& arg1 > this.setY(154) && arg1 < this.setY(202)) {
			this.game.setInput(Coordonnee.COOR_37,false);
		} else if (arg0 > this.setX(301) && arg0 < this.setX(349)
				&& arg1 > this.setY(154) && arg1 < this.setY(202)) {
			this.game.setInput(Coordonnee.COOR_39,false);
		} else if (arg0 > this.setX(357) && arg0 < this.setX(405)
				&& arg1 > this.setY(154) && arg1 < this.setY(202)) {
			this.game.setInput(Coordonnee.COOR_311,false);
		} else if (arg0 > this.setX(411) && arg0 < this.setX(459)
				&& arg1 > this.setY(154) && arg1 < this.setY(202)) {
			this.game.setInput(Coordonnee.COOR_313,false);
		} else if (arg0 > this.setX(467) && arg0 < this.setX(516)
				&& arg1 > this.setY(154) && arg1 < this.setY(202)) {
			this.game.setInput(Coordonnee.COOR_315,false);
		} else if (arg0 > this.setX(163) && arg0 < this.setX(212)
				&& arg1 > this.setY(204) && arg1 < this.setY(251)) {
			this.game.setInput(Coordonnee.COOR_44,false);
		} else if (arg0 > this.setX(219) && arg0 < this.setX(268)
				&& arg1 > this.setY(204) && arg1 < this.setY(251)) {
			this.game.setInput(Coordonnee.COOR_46,false);
		} else if (arg0 > this.setX(275) && arg0 < this.setX(323)
				&& arg1 > this.setY(204) && arg1 < this.setY(251)) {
			this.game.setInput(Coordonnee.COOR_48,false);
		} else if (arg0 > this.setX(330) && arg0 < this.setX(377)
				&& arg1 > this.setY(204) && arg1 < this.setY(251)) {
			this.game.setInput(Coordonnee.COOR_410,false);
		} else if (arg0 > this.setX(384) && arg0 < this.setX(433)
				&& arg1 > this.setY(204) && arg1 < this.setY(251)) {
			this.game.setInput(Coordonnee.COOR_412,false);
		} else if (arg0 > this.setX(439) && arg0 < this.setX(486)
				&& arg1 > this.setY(204) && arg1 < this.setY(251)) {
			this.game.setInput(Coordonnee.COOR_414,false);
		} else if (arg0 > this.setX(497) && arg0 < this.setX(542)
				&& arg1 > this.setY(204) && arg1 < this.setY(251)) {
			this.game.setInput(Coordonnee.COOR_416,false);
		} else if (arg0 > this.setX(137) && arg0 < this.setX(185)
				&& arg1 > this.setY(251) && arg1 < this.setY(298)) {
			this.game.setInput(Coordonnee.COOR_53,false);
		} else if (arg0 > this.setX(191) && arg0 < this.setX(239)
				&& arg1 > this.setY(251) && arg1 < this.setY(298)) {
			this.game.setInput(Coordonnee.COOR_55,false);
		} else if (arg0 > this.setX(246) && arg0 < this.setX(293)
				&& arg1 > this.setY(251) && arg1 < this.setY(298)) {
			this.game.setInput(Coordonnee.COOR_57,false);
		} else if (arg0 > this.setX(302) && arg0 < this.setX(350)
				&& arg1 > this.setY(251) && arg1 < this.setY(298)) {
			this.game.setInput(Coordonnee.COOR_59,false);
		} else if (arg0 > this.setX(355) && arg0 < this.setX(404)
				&& arg1 > this.setY(251) && arg1 < this.setY(298)) {
			this.game.setInput(Coordonnee.COOR_511,false);
		} else if (arg0 > this.setX(411) && arg0 < this.setX(460)
				&& arg1 > this.setY(251) && arg1 < this.setY(298)) {
			this.game.setInput(Coordonnee.COOR_513,false);
		} else if (arg0 > this.setX(467) && arg0 < this.setX(514)
				&& arg1 > this.setY(251) && arg1 < this.setY(298)) {
			this.game.setInput(Coordonnee.COOR_515,false);
		} else if (arg0 > this.setX(522) && arg0 < this.setX(570)
				&& arg1 > this.setY(251) && arg1 < this.setY(298)) {
			this.game.setInput(Coordonnee.COOR_517,false);
		} else if (arg0 > this.setX(109) && arg0 < this.setX(159)
				&& arg1 > this.setY(295) && arg1 < this.setY(343)) {
			this.game.setInput(Coordonnee.COOR_62,false);
		} else if (arg0 > this.setX(165) && arg0 < this.setX(215)
				&& arg1 > this.setY(295) && arg1 < this.setY(343)) {
			this.game.setInput(Coordonnee.COOR_64,false);
		} else if (arg0 > this.setX(220) && arg0 < this.setX(268)
				&& arg1 > this.setY(295) && arg1 < this.setY(343)) {
			this.game.setInput(Coordonnee.COOR_66,false);
		} else if (arg0 > this.setX(274) && arg0 < this.setX(323)
				&& arg1 > this.setY(295) && arg1 < this.setY(343)) {
			this.game.setInput(Coordonnee.COOR_68,false);
		} else if (arg0 > this.setX(329) && arg0 < this.setX(377)
				&& arg1 > this.setY(295) && arg1 < this.setY(343)) {
			this.game.setInput(Coordonnee.COOR_610,false);
		} else if (arg0 > this.setX(386) && arg0 < this.setX(433)
				&& arg1 > this.setY(295) && arg1 < this.setY(343)) {
			this.game.setInput(Coordonnee.COOR_612,false);
		} else if (arg0 > this.setX(441) && arg0 < this.setX(488)
				&& arg1 > this.setY(295) && arg1 < this.setY(343)) {
			this.game.setInput(Coordonnee.COOR_614,false);
		} else if (arg0 > this.setX(495) && arg0 < this.setX(543)
				&& arg1 > this.setY(295) && arg1 < this.setY(343)) {
			this.game.setInput(Coordonnee.COOR_616,false);
		} else if (arg0 > this.setX(551) && arg0 < this.setX(598)
				&& arg1 > this.setY(295) && arg1 < this.setY(343)) {
			this.game.setInput(Coordonnee.COOR_618,false);
		} else if (arg0 > this.setX(136) && arg0 < this.setX(183)
				&& arg1 > this.setY(344) && arg1 < this.setY(390)) {
			this.game.setInput(Coordonnee.COOR_73,false);
		} else if (arg0 > this.setX(193) && arg0 < this.setX(240)
				&& arg1 > this.setY(344) && arg1 < this.setY(390)) {
			this.game.setInput(Coordonnee.COOR_75,false);
		} else if (arg0 > this.setX(247) && arg0 < this.setX(294)
				&& arg1 > this.setY(344) && arg1 < this.setY(390)) {
			this.game.setInput(Coordonnee.COOR_77,false);
		} else if (arg0 > this.setX(304) && arg0 < this.setX(349)
				&& arg1 > this.setY(344) && arg1 < this.setY(390)) {
			this.game.setInput(Coordonnee.COOR_79,false);
		} else if (arg0 > this.setX(359) && arg0 < this.setX(406)
				&& arg1 > this.setY(344) && arg1 < this.setY(390)) {
			this.game.setInput(Coordonnee.COOR_711,false);
		} else if (arg0 > this.setX(414) && arg0 < this.setX(462)
				&& arg1 > this.setY(344) && arg1 < this.setY(390)) {
			this.game.setInput(Coordonnee.COOR_713,false);
		} else if (arg0 > this.setX(471) && arg0 < this.setX(517)
				&& arg1 > this.setY(344) && arg1 < this.setY(390)) {
			this.game.setInput(Coordonnee.COOR_715,false);
		} else if (arg0 > this.setX(525) && arg0 < this.setX(571)
				&& arg1 > this.setY(344) && arg1 < this.setY(390)) {
			this.game.setInput(Coordonnee.COOR_717,false);
		} else if (arg0 > this.setX(163) && arg0 < this.setX(213)
				&& arg1 > this.setY(391) && arg1 < this.setY(439)) {
			this.game.setInput(Coordonnee.COOR_84,false);
		} else if (arg0 > this.setX(220) && arg0 < this.setX(268)
				&& arg1 > this.setY(391) && arg1 < this.setY(439)) {
			this.game.setInput(Coordonnee.COOR_86,false);
		} else if (arg0 > this.setX(276) && arg0 < this.setX(323)
				&& arg1 > this.setY(391) && arg1 < this.setY(439)) {
			this.game.setInput(Coordonnee.COOR_88,false);
		} else if (arg0 > this.setX(330) && arg0 < this.setX(379)
				&& arg1 > this.setY(391) && arg1 < this.setY(439)) {
			this.game.setInput(Coordonnee.COOR_810,false);
		} else if (arg0 > this.setX(387) && arg0 < this.setX(434)
				&& arg1 > this.setY(391) && arg1 < this.setY(439)) {
			this.game.setInput(Coordonnee.COOR_812,false);
		} else if (arg0 > this.setX(442) && arg0 < this.setX(489)
				&& arg1 > this.setY(391) && arg1 < this.setY(439)) {
			this.game.setInput(Coordonnee.COOR_814,false);
		} else if (arg0 > this.setX(498) && arg0 < this.setX(544)
				&& arg1 > this.setY(391) && arg1 < this.setY(439)) {
			this.game.setInput(Coordonnee.COOR_816,false);
		} else if (arg0 > this.setX(193) && arg0 < this.setX(241)
				&& arg1 > this.setY(439) && arg1 < this.setY(486)) {
			this.game.setInput(Coordonnee.COOR_95,false);
		} else if (arg0 > this.setX(248) && arg0 < this.setX(295)
				&& arg1 > this.setY(439) && arg1 < this.setY(486)) {
			this.game.setInput(Coordonnee.COOR_97,false);
		} else if (arg0 > this.setX(303) && arg0 < this.setX(351)
				&& arg1 > this.setY(439) && arg1 < this.setY(486)) {
			this.game.setInput(Coordonnee.COOR_99,false);
		} else if (arg0 > this.setX(359) && arg0 < this.setX(407)
				&& arg1 > this.setY(439) && arg1 < this.setY(486)) {
			this.game.setInput(Coordonnee.COOR_911,false);
		} else if (arg0 > this.setX(414) && arg0 < this.setX(461)
				&& arg1 > this.setY(439) && arg1 < this.setY(486)) {
			this.game.setInput(Coordonnee.COOR_913,false);
		} else if (arg0 > this.setX(468) && arg0 < this.setX(518)
				&& arg1 > this.setY(439) && arg1 < this.setY(486)) {
			this.game.setInput(Coordonnee.COOR_915,false);
		} else if (arg0 > this.setX(219) && arg0 < this.setX(268)
				&& arg1 > this.setY(485) && arg1 < this.setY(532)) {
			this.game.setInput(Coordonnee.COOR_106,false);
		} else if (arg0 > this.setX(276) && arg0 < this.setX(324)
				&& arg1 > this.setY(485) && arg1 < this.setY(532)) {
			this.game.setInput(Coordonnee.COOR_108,false);
		} else if (arg0 > this.setX(331) && arg0 < this.setX(380)
				&& arg1 > this.setY(485) && arg1 < this.setY(532)) {
			this.game.setInput(Coordonnee.COOR_1010,false);
		} else if (arg0 > this.setX(386) && arg0 < this.setX(435)
				&& arg1 > this.setY(485) && arg1 < this.setY(532)) {
			this.game.setInput(Coordonnee.COOR_1012,false);
		} else if (arg0 > this.setX(442) && arg0 < this.setX(490)
				&& arg1 > this.setY(485) && arg1 < this.setY(532)) {
			this.game.setInput(Coordonnee.COOR_1014,false);
		}
		else if (arg0 > this.setX(604) && arg0 < this.setX(691)
				&& arg1 > this.setY(597) && arg1 < this.setY(662)) {
			this.game.setInput(Coordonnee.COOR_1010,true); //passe le tour
		}
		else if ( arg0 > this.setX(669) && arg0 < this.setX(696) && 
				arg1 > this.setY(7) && arg1 < this.setY(30)){
			this.screen.dessinePlateau(false); //quitter le jeu
			this.lanceur.refresh();
		}
		else if ( arg0 > this.setX(574) && arg0 < this.setX(620) && 
				arg1 > this.setY(6) && arg1 < this.setY(32)){
			if ( this.lanceur.getSonActif() ) //reglage du son
				this.lanceur.coupeSon();
			else
				this.lanceur.activeSon();
		}
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
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

