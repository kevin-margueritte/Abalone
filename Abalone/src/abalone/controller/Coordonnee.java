package abalone.controller;

/**
 * Type enumere permettant de transformer les coordonnees de la matrice du model en position sur l'ecran (x,y)
 * @author groupe projet
 * @mail kevin.margueritte@gmail.com
 */

public enum Coordonnee {
	COOR_TEST(0,0,0,0),
	COOR_26(2,6,202,536),
	COOR_28(2,8,257,536),
	COOR_210(2,10,312,536),
	COOR_212(2,12,367,536),
	COOR_214(2,14,422,536),
	COOR_35(3,5,174,487),
	COOR_37(3,7,229,487),
	COOR_39(3,9,284,487),
	COOR_311(3,11,339,487),
	COOR_313(3,13,394,487),
	COOR_315(3,15,449,487),
	COOR_44(4,4,148,439),
	COOR_46(4,6,203,439),
	COOR_48(4,8,258,439),
	COOR_410(4,10,312,439),
	COOR_412(4,12,367,439),
	COOR_414(4,14,421,439),
	COOR_416(4,16,476,439),
	COOR_53(5,3,121,392),
	COOR_55(5,5,175,392),
	COOR_57(5,7,230,392),
	COOR_59(5,9,285,392),
	COOR_511(5,11,339,392),
	COOR_513(5,13,394,392),
	COOR_515(5,15,448,392),
	COOR_517(5,17,503,392),
	COOR_62(6,2,94,346),
	COOR_64(6,4,149,346),
	COOR_66(6,6,204,346),
	COOR_68(6,8,258,346),
	COOR_610(6,10,312,346),
	COOR_612(6,12,367,346),
	COOR_614(6,14,422,346),
	COOR_616(6,16,477,346),
	COOR_618(6,18,532,346),
	COOR_73(7,3,121,299),
	COOR_75(7,5,176,299),
	COOR_77(7,7,231,299),
	COOR_79(7,9,286,299),
	COOR_711(7,11,341,299),
	COOR_713(7,13,396,299),
	COOR_715(7,15,451,299),
	COOR_717(7,17,506,299),
	COOR_84(8,4,149,252),
	COOR_86(8,6,204,252),
	COOR_88(8,8,259,252),
	COOR_810(8,10,314,252),
	COOR_812(8,12,369,252),
	COOR_814(8,14,424,252),
	COOR_816(8,16,479,252),
	COOR_95(9,5,176,205),
	COOR_97(9,7,231,205),
	COOR_99(9,9,286,205),
	COOR_911(9,11,341,205),
	COOR_913(9,13,396,205),
	COOR_915(9,15,451,205),
	COOR_106(10,6,204,157),
	COOR_108(10,8,259,157),
	COOR_1010(10,10,314,157),
	COOR_1012(10,12,369,157),
	COOR_1014(10,14,424,157);
	
	 private int i;
	 private int j;
	 private int x;
	 private int y;
	 
	private Coordonnee(int i, int j, int x, int y){
		 this.i = i;
		 this.j = j;
		 this.x = x;
		 this.y = y;
	 }
	 
	 public static Coordonnee getCoordonne( int i, int j)
		{
			if( i == 2 && j== 6) return COOR_26;
			else if( i == 2 && j == 8 ) return COOR_28;
			else if( i == 2 && j == 10 ) return COOR_210;
			else if( i == 2 && j == 12 ) return COOR_212;
			else if( i == 2 && j == 14 ) return COOR_214;
			else if( i == 3 && j == 5 ) return COOR_35;
			else if( i == 3 && j == 7 ) return COOR_37;
			else if( i == 3 && j == 9 ) return COOR_39;
			else if( i == 3 && j == 11 ) return COOR_311;
			else if( i == 3 && j == 13 ) return COOR_313;
			else if( i == 3 && j == 15 ) return COOR_315;
			else if( i == 4 && j == 4 ) return COOR_44;
			else if( i == 4 && j == 6 ) return COOR_46;
			else if( i == 4 && j == 8 ) return COOR_48;
			else if( i == 4 && j == 10 ) return COOR_410;
			else if( i == 4 && j == 12 ) return COOR_412;
			else if( i == 4 && j == 14 ) return COOR_414;
			else if( i == 4 && j == 16 ) return COOR_416;
			else if( i == 5 && j == 3 ) return COOR_53;
			else if( i == 5 && j == 5 ) return COOR_55;
			else if( i == 5 && j == 7 ) return COOR_57;
			else if( i == 5 && j == 9 ) return COOR_59;
			else if( i == 5 && j == 11 ) return COOR_511;
			else if( i == 5 && j == 13 ) return COOR_513;
			else if( i == 5 && j == 15 ) return COOR_515;
			else if( i == 5 && j == 17 ) return COOR_517;
			else if( i == 6 && j == 2 ) return COOR_62;
			else if( i == 6 && j == 4 ) return COOR_64;
			else if( i == 6 && j == 6 ) return COOR_66;
			else if( i == 6 && j == 8 ) return COOR_68;
			else if( i == 6 && j == 10 ) return COOR_610;
			else if( i == 6 && j == 12 ) return COOR_612;
			else if( i == 6 && j == 14 ) return COOR_614;
			else if( i == 6 && j == 16 ) return COOR_616;
			else if( i == 6 && j == 18 ) return COOR_618;
			else if( i == 7 && j == 3 ) return COOR_73;
			else if( i == 7 && j == 5 ) return COOR_75;
			else if( i == 7 && j == 7 ) return COOR_77;
			else if( i == 7 && j == 9 ) return COOR_79;
			else if( i == 7 && j == 11 ) return COOR_711;
			else if( i == 7 && j == 13 ) return COOR_713;
			else if( i == 7 && j == 15 ) return COOR_715;
			else if( i == 7 && j == 17 ) return COOR_717;
			else if( i == 8 && j == 4 ) return COOR_84;
			else if( i == 8 && j == 6 ) return COOR_86;
			else if( i == 8 && j == 8 ) return COOR_88;
			else if( i == 8 && j == 10 ) return COOR_810;
			else if( i == 8 && j == 12 ) return COOR_812;
			else if( i == 8 && j == 14 ) return COOR_814;
			else if( i == 8 && j == 16 ) return COOR_816;
			else if( i == 9 && j == 5 ) return COOR_95;
			else if( i == 9 && j == 7 ) return COOR_97;
			else if( i == 9 && j == 9 ) return COOR_99;
			else if( i == 9 && j == 11 ) return COOR_911;
			else if( i == 9 && j == 13 ) return COOR_913;
			else if( i == 9 && j == 15 ) return COOR_915;
			else if( i == 10 && j == 6 ) return COOR_106;
			else if( i == 10 && j == 8 ) return COOR_108;
			else if( i == 10 && j == 10 ) return COOR_1010;
			else if( i == 10 && j == 12 ) return COOR_1012;
			else if( i == 10 && j == 14 ) return COOR_1014;
			else return null;
		}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
