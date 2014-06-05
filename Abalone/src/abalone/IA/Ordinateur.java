package abalone.IA;
import java.util.ArrayList;

/**
 * Intelligence artificielle du jeu, suit un algorithme alpha-beta
 * @author groupe projet
 * @mail kevin.margueritte@gmail.com
 *
 */

import abalone.controller.Coup;
import abalone.controller.Deplacement;
import abalone.controller.Refresh;
import abalone.controller.TypeBille;
import abalone.model.Plateau; //Coups predefinis pour le debut de partie


public class Ordinateur {
	private Plateau plateau;
	private Deplacement deplacement;
	private Refresh refresh;
	private ArrayList<Coup> debutPartie;

	public Ordinateur(Plateau plateau,Refresh refresh) {
		// TODO Auto-generated constructor stub
		this.plateau = plateau;
		this.refresh = refresh;
		this.deplacement = new Deplacement(plateau,refresh);
		this.debutPartie = new ArrayList<Coup>();
		this.debutPartie.add(new Coup(10,6,7,9));
		this.debutPartie.add(new Coup(10,8,7,11));
		this.debutPartie.add(new Coup(9,5,8,6));
		this.debutPartie.add(new Coup(10,14,8,16));
	}
	
	/**
	 * Fait jouer l'ordinateur
	 * 
	 * @param boolean facile
	 * @param boolean moyen
	 * @param boolean difficile
	 * @param int index
	 */
	public void simulation(boolean facile, boolean moyen, boolean difficile, int index ){
		if ( index < 3) {
			//this.simulerCoup(this.debutPartie.get(0), this.plateau);
			this.deplacement.deplacementLineaire(this.debutPartie.get(index).getiInit(),this.debutPartie.get(index).getjInit(), this.debutPartie.get(index).getiFin(), this.debutPartie.get(index).getjFin(), 0 );
			this.refresh.draw();
		}
		else
			this.jouer(TypeBille.BLACK, facile, moyen, difficile);	
	}
	
	/**
	 * Simule un coup sur une copie du plateau de depart
	 * @param Coup a simuler
	 * @param Plateau
	 */
	public static void simulerCoup( Coup coup,Plateau copie ){
		Deplacement deplacementCopie;
		deplacementCopie = new Deplacement(copie);
		for ( int i = 0 ; i < coup.tailleCoup() ; i ++)
			deplacementCopie.deplacementLineaire(coup.getiInit(i), coup.getjInit(i), coup.getiFin(i), coup.getjFin(i), 0);//);
	}
	
	
	/**
	 * Recupere tous les coups possibles pour toutes les billes d'un joueur, les coups dependent du niveau de difficulte
	 * @param TypeBille couleur de la bille
	 * @param Plateau plateau
	 * @param boolean facile
	 * @param boolean moyen
	 * @param boolean difficile
	 * @return ArrayList<Coup> avec tous les coups possibles
	 */
	public static ArrayList<Coup> coupPossible(TypeBille typeBille, Plateau plateau, boolean facile, boolean moyen, boolean difficile){
		ArrayList<Coup> listeCoup = new ArrayList<Coup>();
		int nbBilleTrouvee = 0;
		int i = 0, j = 0;
		if( typeBille == TypeBille.BLACK){
			while(i < 13 && nbBilleTrouvee < plateau.getNbBillesNoirs()){
				while (j<21 && nbBilleTrouvee < plateau.getNbBillesNoirs()){
					if( plateau.getPion(i, j) == 2 ){
						listeCoup.addAll(Ordinateur.coupPossiblePion(i, j, plateau, facile, moyen, difficile));
						nbBilleTrouvee ++ ;
					}
					j++;
				}
				j = 0;
				i++;
			}
		}
		else{
			while(i < 13 && nbBilleTrouvee < plateau.getNbBillesBlanches()){
				while (j<21 && nbBilleTrouvee < plateau.getNbBillesBlanches()){
					if( plateau.getPion(i, j) == 1 ){
						listeCoup.addAll(Ordinateur.coupPossiblePion(i, j, plateau, facile, moyen, difficile));
						nbBilleTrouvee ++ ;
					}
					j++;
				}
				j = 0;
				i++;
			}
		}
		return listeCoup;
	}
	
	/**
	 * Recupere tous les coups possibles pour toutes les billes d'un joueur, les coups dependent du niveau de difficulte
	 * @param TypeBille couleur de la bille
	 * @param Plateau plateau
	 * @param boolean facile
	 * @param boolean moyen
	 * @param boolean difficile
	 * @return ArrayList<Coup> avec tous les coups possibles
	 */
	public static ArrayList<Coup> coupPossiblePion(int i , int j, Plateau plateau, boolean facile, boolean moyen, boolean difficile){
		ArrayList<Coup> listeCoup = new ArrayList<Coup>();
		Deplacement deplacement = new Deplacement(plateau);
			if ( deplacement.deplacementIsPossible(i, j, i, j + 4)) listeCoup.add(new Coup( new int[]{i},new int[]{j},new int[]{i},new int[]{j + 4 }));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile )&& deplacement.deplacementIsPossible(i, j, i, j + 6)) listeCoup.add(new Coup(new int[]{i},new int[]{j},new int[]{i},new int[]{j + 6}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i, j + 8)) listeCoup.add(new Coup(new int[]{i},new int[]{j},new int[]{i},new int[]{j + 8}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i, j + 10)) listeCoup.add(new Coup(new int[]{i},new int[]{j},new int[]{i},new int[]{j + 10}));
			if ( deplacement.deplacementIsPossible(i, j, i - 2, j + 2)) listeCoup.add(new Coup(new int[]{i},new int[]{j},new int[]{i - 2},new int[]{j + 2}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i - 3, j + 3)) listeCoup.add(new Coup(new int[]{i},new int[]{j},new int[]{i - 3},new int[]{j + 3}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i - 4, j + 4)) listeCoup.add(new Coup(new int[]{i},new int[]{j},new int[]{i - 4},new int[]{j + 4}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) &&  deplacement.deplacementIsPossible(i, j, i - 5, j + 5)) listeCoup.add(new Coup(new int[]{i},new int[]{j},new int[]{i - 5},new int[]{j + 5}));
			if ( deplacement.deplacementIsPossible(i, j, i + 2, j + 2)) listeCoup.add(new Coup(new int[] {i},new int[] {j}, new int[] {i + 2},new int[] {j + 2}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i + 3, j + 3)) listeCoup.add(new Coup(new int[] {i},new int[] {j}, new int[] {i + 3},new int[] {j + 3}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i + 4, j + 4)) listeCoup.add(new Coup(new int[] {i},new int[] {j}, new int[] {i + 4},new int[] {j + 4}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i + 5, j + 5)) listeCoup.add(new Coup(new int[] {i},new int[] {j}, new int[] {i + 5},new int[] {j + 5}));
			if ( deplacement.deplacementIsPossible(i, j, i, j - 4)) listeCoup.add(new Coup(new int[] {i},new int[] {j}, new int[] {i} ,new int[] {j - 4}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i, j - 6)) listeCoup.add(new Coup(new int[] {i},new int[] {j}, new int[] {i} ,new int[] {j - 6}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) &&  deplacement.deplacementIsPossible(i, j, i, j - 8)) listeCoup.add(new Coup(new int[] {i},new int[] {j}, new int[] {i} ,new int[] {j - 8}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i, j - 10)) listeCoup.add(new Coup(new int[] {i},new int[] {j}, new int[] {i} ,new int[] {j - 10}));
			if ( deplacement.deplacementIsPossible(i, j, i - 2, j - 2)) listeCoup.add(new Coup(new int[] {i},new int[] {j}, new int[] {i - 2} ,new int[] {j - 2}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i - 3, j - 3)) listeCoup.add(new Coup(new int[] {i},new int[] {j}, new int[] {i - 3} ,new int[] {j - 3}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i - 4, j - 4)) listeCoup.add(new Coup(new int[] {i},new int[] {j}, new int[] {i - 4} ,new int[] {j - 4}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i - 5, j - 5)) listeCoup.add(new Coup(new int[] {i},new int[] {j}, new int[] {i - 5} ,new int[] {j - 5}));
			if ( deplacement.deplacementIsPossible(i, j, i + 2, j - 2)) listeCoup.add(new Coup(new int[] {i}, new int[] {j}, new int[] {i + 2}, new int[] {j - 2}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i + 3, j - 3)) listeCoup.add(new Coup(new int[] {i}, new int[] {j}, new int[] {i + 3}, new int[] {j - 3}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i + 4, j - 4)) listeCoup.add(new Coup(new int[] {i}, new int[] {j}, new int[] {i + 4}, new int[] {j - 4}));
			if ( (( facile && Math.random() > 0.4 ) || moyen || difficile ) && deplacement.deplacementIsPossible(i, j, i + 5, j - 5)) listeCoup.add(new Coup(new int[] {i}, new int[] {j}, new int[] {i + 5}, new int[] {j - 5}));
		return listeCoup;
	}
	
	/**
	 * Recupere tous les coups possibles pour une bille, les coups dependent du niveau de difficulte
	 * @param coordonnee i
	 * @param coordonnee j
	 * @param Pleateu plateau
	 * @param boolean mode facile
	 * @param boolean mode moyen
	 * @param boolean mode difficile
	 * @return ArrayList<Coup> de tous les coups possible pour une bille
	 */
	public static int evalDeplacement(Coup coup, Plateau plateau){
		int unPion = 1000;
		int deuxPions = 2000;
		int troisPions = 3000;
		int deuxVSun = 4000;
		int troisVSun = 10000;
		int troisVSdeux = 20000;
		int ejectDeuxVSun = Integer.MAX_VALUE - 100000;
		int ejectTroisVSun = Integer.MAX_VALUE - 200000;
		int ejectTroisVSdeux = Integer.MAX_VALUE;
		int eval ;
		int iInit = coup.getiInit(0); int jInit = coup.getjInit(0); int iFin = coup.getiFin(0); int jFin = coup.getjFin(0);
		 if(iInit == iFin && jInit<jFin){ //deplace vers la droite
			if (jFin - jInit == 2) eval = unPion; //on deplace sans pousser, un pion
			else if (jFin - jInit == 4 && plateau.verifCaseVide(iFin, jFin)) eval = deuxPions;//on deplace sans pousser, 2 pions
			else if (jFin - jInit == 6){ //on deplace 3 pions
				if( plateau.memePion(iInit, jInit, iFin, jFin - 2) && plateau.verifCaseVide(iFin, jFin)) eval = troisPions; //on deplace sans pousser, 3 pions
				else { //on pousse 1billes
					if ( plateau.isOut(iFin, jFin + 2) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectDeuxVSun; //on eject un pion 2 VS 1
					else eval = deuxVSun; //sinon on pousse juste 2 VS 1
				}
			}
			else if (jFin - jInit == 8){ //on va faire 3 VS 1
				if ( plateau.isOut(iFin, jFin + 2) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectTroisVSun; //Eject un pion
				else eval = troisVSun; //on pousse un pion advairse
			}
			else { //on fait 3 VS 2
				if ( plateau.isOut(iFin, jFin + 2) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectTroisVSdeux; //Eject deux pions
				else eval = troisVSdeux; //on pousse deux pions advairse
			}
		}
		else if(iInit == iFin && jInit>jFin){ //deplace vers la gauche
			if (jFin - jInit == -2) eval = unPion; //on deplace sans pousser, un pion
			else if (jFin - jInit == -4 && plateau.verifCaseVide(iFin, jFin)) eval = deuxPions;//on deplace sans pousser, 2 pions
			else if (jFin - jInit == -6){ //on deplace 3 pions
				if( plateau.memePion(iInit, jInit, iFin, jFin + 2) && plateau.verifCaseVide(iFin, jFin)) eval = troisPions; //on deplace sans pousser, 3 pions
				else { //on pousse 1billes
					if ( plateau.isOut(iFin, jFin - 2) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectDeuxVSun; //on eject un pion 2 VS 1
					else eval = deuxVSun; //sinon on pousse juste 2 VS 1
				}
			}
			else if (jFin - jInit == -8){ //on va faire 3 VS 1
				if ( plateau.isOut(iFin, jFin - 2) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectTroisVSun; //Eject un pion
				else eval = troisVSun; //on pousse un pion advairse
			}
			else { //on fait 3 VS 2
				if ( plateau.isOut(iFin, jFin - 2) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectTroisVSdeux; //Eject deux pions
				else eval = troisVSdeux; //on pousse deux pions advairse
			}
		}
		else if(iInit > iFin && jInit < jFin){ //deplace en diagonal haut/droite
			if (jFin - jInit == 1) eval = unPion; //on deplace sans pousser, un pion
			else if (jFin - jInit == 2 && plateau.verifCaseVide(iFin, jFin)) eval = deuxPions;//on deplace sans pousser, 2 pions
			else if (jFin - jInit == 3){ //on deplace 3 pions
				if( plateau.memePion(iInit, jInit, iFin+1, jFin - 1) && plateau.verifCaseVide(iFin, jFin) ) eval = troisPions; //on deplace sans pousser, 3 pions
				else { //on pousse 1billes
					if ( plateau.isOut(iFin - 1, jFin + 1) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectDeuxVSun; //on eject un pion 2 VS 1
					else eval = deuxVSun; //sinon on pousse juste 2 VS 1
				}
			}
			else if (jFin - jInit == 4){ //on va faire 3 VS 1
				if ( plateau.isOut(iFin -1, jFin + 1) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectTroisVSun; //Eject un pion
				else eval = troisVSun; //on pousse un pion advairse
			}
			else { //on fait 3 VS 2
				if ( plateau.isOut(iFin - 1, jFin + 1) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectTroisVSdeux; //Eject deux pions
				else eval = troisVSdeux; //on pousse deux pions advairse
			}
		}
		else if(iInit < iFin && jInit < jFin){ //deplace en diagonal bas/droite
			if (jFin - jInit == 1) eval = unPion; //on deplace sans pousser, un pion
			else if (jFin - jInit == 2 && plateau.verifCaseVide(iFin, jFin)) eval = deuxPions;//on deplace sans pousser, 2 pions
			else if (jFin - jInit == 3){ //on deplace 3 pions
				if( plateau.memePion(iInit, jInit, iFin-1, jFin - 1) && plateau.verifCaseVide(iFin, jFin)) eval = troisPions; //on deplace sans pousser, 3 pions
				else { //on pousse 1billes
					if ( plateau.isOut(iFin + 1, jFin + 1) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectDeuxVSun; //on eject un pion 2 VS 1
					else eval = deuxVSun; //sinon on pousse juste 2 VS 1
				}
			}
			else if (jFin - jInit == 4){ //on va faire 3 VS 1
				if ( plateau.isOut(iFin + 1, jFin + 1) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectTroisVSun; //Eject un pion
				else eval = troisVSun; //on pousse un pion advairse
			}
			else { //on fait 3 VS 2
				if ( plateau.isOut(iFin + 1, jFin + 1) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectTroisVSdeux; //Eject deux pions
				else eval = troisVSdeux; //on pousse deux pions advairse
			}
		}
		else if(iInit < iFin && jInit > jFin){ //deplace en diagonal bas/gauche
			if (jFin - jInit == -1) eval = unPion; //on deplace sans pousser, un pion
			else if (jFin - jInit == -2 && plateau.verifCaseVide(iFin, jFin)) eval = deuxPions;//on deplace sans pousser, 2 pions
			else if (jFin - jInit == -3){ //on deplace 3 pions
				if( plateau.memePion(iInit, jInit, iFin-1, jFin + 1) && plateau.verifCaseVide(iFin, jFin)) eval = troisPions; //on deplace sans pousser, 3 pions
				else { //on pousse 1billes
					if ( plateau.isOut(iFin + 1, jFin - 1) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectDeuxVSun; //on eject un pion 2 VS 1
					else eval = deuxVSun; //sinon on pousse juste 2 VS 1
				}
			}
			else if (jFin - jInit == -4){ //on va faire 3 VS 1
				if ( plateau.isOut(iFin + 1, jFin - 1) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectTroisVSun; //Eject un pion
				else eval = troisVSun; //on pousse un pion advairse
			}
			else { //on fait 3 VS 2
				if ( plateau.isOut(iFin + 1, jFin - 1) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectTroisVSdeux; //Eject deux pions
				else eval = troisVSdeux; //on pousse deux pions advairse
			}
		}
		else { //deplace en diagonal haut/gauche
			if (jFin - jInit == -1) eval = unPion; //on deplace sans pousser, un pion
			else if (jFin - jInit == -2 && plateau.verifCaseVide(iFin, jFin)) eval = deuxPions;//on deplace sans pousser, 2 pions
			else if (jFin - jInit == -3){ //on deplace 3 pions
				if( plateau.memePion(iInit, jInit, iFin+1, jFin + 1) && plateau.verifCaseVide(iFin, jFin)) eval = troisPions; //on deplace sans pousser, 3 pions
				else { //on pousse 1billes
					if ( plateau.isOut(iFin - 1, jFin - 1) && !plateau.verifCaseVide(iFin, jFin) ) eval = ejectDeuxVSun; //on eject un pion 2 VS 1
					else eval = deuxVSun; //sinon on pousse juste 2 VS 1
				}
			}
			else if (jFin - jInit == -4){ //on va faire 3 VS 1
				if ( plateau.isOut(iFin - 1, jFin - 1) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectTroisVSun; //Eject un pion
				else eval = troisVSun; //on pousse un pion advairse
			}
			else { //on fait 3 VS 2
				if ( plateau.isOut(iFin - 1, jFin - 1) && !plateau.verifCaseVide(iFin, jFin)) eval = ejectTroisVSdeux; //Eject deux pions
				else eval = troisVSdeux; //on pousse deux pions advairse
			}
		}
		return eval;
	}
	
	/**
	 * Evalue un coup sur un plateau
	 * @param Coup coup
	 * @param Plateau plateau
	 * @return int l'evaluation d'un coup
	 */
	public static int eval(Coup coup, Plateau plateau) { //(P2 - P1)*| evaldeplacement() |
		Deplacement deplacement = new Deplacement(plateau);
		int cpt = 0;
		for (int i = 0 ; i < 13 ; i++){
			for (int j= 0; j < 21 ; j++){
				for ( int x = 0 ; x < coup.tailleCoup() ; x ++ )
				if ( plateau.memePion(i, j, coup.getiInit(x), coup.getjInit(x)))
					cpt += deplacement.nbVoisins(i, j);
			}
		}
		return Ordinateur.evalDeplacement(coup, plateau) * (cpt / 6  );
	}
	
	/**
	 * Lance l'algorithme alpha-beta
	 * @param typeBille couleur de la bille de l'ordinateur
	 * @param facile true si mode facile false sinon
	 * @param moyen true si mode moyen false sinon
	 * @param difficile true si mode difficile false sinon
	 */
	public void jouer( TypeBille typeBille , boolean facile, boolean moyen, boolean difficile){
		int max_val = Integer.MIN_VALUE; //Initialise � -infini
		ArrayList<Coup> listeCoup = new ArrayList<Coup>();
		Plateau copie = this.plateau.copie(); //Fait une sauvegarde du plateau pour manipuler manipuler la copie
		Coup coup_actuel,meilleur_coup = new Coup();
		listeCoup = Ordinateur.coupPossible( typeBille, copie, facile,moyen,difficile ); //Met dans une ArrayList tous les coups possibles 
		int val;
		int profondeur;
		if ( difficile )
			profondeur = 4;
		else
			profondeur = 2;
		for( int i = 0; i < listeCoup.size() ; i++){ //Parcours la totalit� des coups possibles � travers l'ArrayList
			coup_actuel = listeCoup.get(i); 
			simulerCoup(coup_actuel,copie); //Simule un coup
			val = min(copie, this.plateau, profondeur, coup_actuel, typeBille, Integer.MIN_VALUE, Integer.MAX_VALUE,facile ,moyen, difficile); //efectue un Min
			if ( val >  max_val){
				max_val = val;
				meilleur_coup = coup_actuel; //Sauvegarde le meilleur coup de la plus grande valeur
			}
			copie = this.plateau.copie(); //Annule le coup
		}
		for ( int  i = 0 ;  i < meilleur_coup.tailleCoup() ; i ++) {
			System.out.println(this.plateau.toString());
			System.out.println(meilleur_coup.toString());
			this.deplacement.deplacementLineaire(meilleur_coup.getiInit(i), meilleur_coup.getjInit(i), meilleur_coup.getiFin(i), meilleur_coup.getjFin(i), 0); //Joue le meilleur coup
			System.out.println(this.plateau.toString());
		}
			refresh.draw(); //Met � jour la vue
	}
	
	/**
	 * Fonction Min retourne le minimum des sous branches d'un Max
	 * @param plateau Plateau apres avoir subit un le coup_actuel
	 * @param ancienPlateau Plateau avant d'avoir subit le coup_actuel
	 * @param profondeur de l'arbre de recherche
	 * @param coup_actuel Coup effectu� sur plateau avant l'appelle de la fonction
	 * @param typeBille couleur de la bille de l'adversaire 
	 * @param alpha 
	 * @param beta
	 * @param facile true si mode facile false sinon
	 * @param moyen true si mode moyen false sinon
	 * @param difficile true si mode difficile false sinon
	 * @return l'�valuation de la sous-branche
	 */
	@SuppressWarnings("static-access")
	public int min(Plateau plateau ,Plateau ancienPlateau, int profondeur , Coup coup_actuel, TypeBille typeBille, int alpha, int beta, boolean facile, boolean moyen, boolean difficile ){
		if ( profondeur == 0 || Plateau.endGame(plateau)){ // Si la profondeur et de 0 ou que la partie est termin�
			return Ordinateur.eval(coup_actuel, ancienPlateau) ; //Evalue le coup � effectu� 
		}
		else {
		int v = Integer.MAX_VALUE; // Initialise � infini
		if ( typeBille.equals(typeBille.BLACK)) typeBille = typeBille.WHITE;
		else typeBille = typeBille.BLACK;
		ArrayList<Coup> listeCoup = new ArrayList<Coup>();
		Plateau copie = plateau.copie();
		listeCoup = Ordinateur.coupPossible( typeBille, copie, facile, moyen, difficile );
		for ( int i = 0; i < listeCoup.size() ; i++ ){
			coup_actuel = listeCoup.get(i);
			this.simulerCoup(coup_actuel,copie);
			v = Math.min(v,max(copie, plateau, profondeur - 1, coup_actuel, typeBille, alpha , beta, facile,moyen,difficile )); //Recupere la valeur minimal entre v et la fonction max
			if ( v <= alpha ){
				return v; //Retourne v si v est plus petit ou �gale � alpha ( elagage )
			}
			beta = Math.min(beta,v); // Met � jour beta en affectant la valeur la plus petite entre beta et v
			copie = plateau.copie(); 
			}
		return v ;
		}
	}
	
	/**
	 * Fonction Max retourne le maximum des sous branches d'un Min
	 * @param plateau Plateau apres avoir subit un le coup_actuel
	 * @param ancienPlateau Plateau avant d'avoir subit le coup_actuel
	 * @param profondeur de l'arbre de recherche
	 * @param coup_actuel Coup effectu� sur le plateau avant l'appelle de la fonction
	 * @param typeBille couleur de la bille de l'adversaire 
	 * @param alpha 
	 * @param beta
	 * @param facile true si mode facile false sinon
	 * @param moyen true si mode moyen false sinon
	 * @param difficile true si mode difficile false sinon
	 * @return l'�valuation de la sous-branche
	 */
	@SuppressWarnings("static-access")
	public int max(Plateau plateau ,Plateau ancienPlateau,  int profondeur , Coup coup_actuel, TypeBille typeBille, int alpha, int beta, boolean facile, boolean moyen, boolean difficile ){
		if ( profondeur == 0 || Plateau.endGame(plateau)){
			return Ordinateur.eval(coup_actuel, ancienPlateau) ;
		}
		else{
		if ( typeBille.equals(typeBille.BLACK)) typeBille = typeBille.WHITE;
		else typeBille = typeBille.BLACK;
		int v = Integer.MIN_VALUE; //initialise � -infini
		ArrayList<Coup> listeCoup = new ArrayList<Coup>();
		Plateau copie = plateau.copie();
		listeCoup = Ordinateur.coupPossible( typeBille, copie, facile, moyen, difficile );
		for ( int i = 0; i < listeCoup.size() ; i++ ){
			coup_actuel = listeCoup.get(i);
			this.simulerCoup(coup_actuel,copie);
			v = Math.max(v,min(copie,plateau,profondeur - 1, coup_actuel, typeBille, alpha,beta, facile, moyen, difficile)); //Recupere la valeur maximal entre v et la fonction min
			if ( v >=  beta ){
				return v; // Retourne v si v est plus grand ou �gale � beta ( �lagage )
			}
			alpha = Math.max(alpha,v); //Met � jour alpha en affectant la valeur la plus grande entre alpha et v
			copie = plateau.copie(); 
			}
		return v;
		}
	}

}
