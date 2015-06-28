public class PlayArea {

	int[][][] location = new int[7][13][4];// 左右上下
	int[][] register = new int[11][13];//
	int[][] legalposition = new int[7][2];
	int gen0 = 0;
	int gen1 = 0;
	int gen2 = 0;
	int gen3 = 0;

	PlayArea() {
		for (int x = 0; x < 7; x++)
			for (int y = 0; y < 13; y++)
				for (int z = 0; z < 4; z++)
					location[x][y][z] = 0;

		for (int x = 0; x < 11; x++)
			for (int y = 0; y < 13; y++)

				register[x][y] = 0;

		for (int x = 0; x < 7; x++)
			for (int y = 0; y < 2; y++)
				legalposition[x][y] = 0;

		setlocation();

	}

	private void setlocation() {
		for (int x = 0; x < 7; x++)
			for (int y = 0; y < 13; y++) {

				location[x][y][0] = 50 + 130 * x;
				location[x][y][1] = 50 + 80 + 130 * x;
				location[x][y][2] = 273 + y * 25;
				location[x][y][3] = location[x][y][2] + 123;

			}

	}

	public int[] getlocation(int i, int j) {
		// i+1行j列,返回值為座標
		return location[i][j];
	}

	protected void addCard(int lie, int hang, int a) {// playarea中
		register[lie][hang] = a;

	}

	protected void addCard(int locationX, int bianhao) {// 加入到leftarea中
		switch (locationX) {
		case 7:
			register[locationX][gen0++] = bianhao;
			break;
		case 8:
			register[locationX][gen1++] = bianhao;
			break;
		case 9:
			register[locationX][gen2++] = bianhao;
			break;
		case 10:
			register[locationX][gen3++] = bianhao;
			break;

		}
	}

	protected int getHang(int locationX) {
		switch (locationX) {
		case 7:

			return gen0 - 1;
		case 8:
			return gen1 - 1;
		case 9:
			return gen2 - 1;
		case 10:
			return gen3 - 1;

		}
		return -1;
	}

	protected int[][] getregister() {
		// TODO Auto-generated method stub
		return register;
	}

	protected void removeCard(int lie, int hang) {
		register[lie][hang] = 0;

	}

	protected int getLie(int x) {
		// 返回值為列號
		System.out.println("dasdsa" + x);
		for (int i = 0; i < 7; i++)
			if (location[i][0][0] < x)
				if (location[i][0][1] > x) {

					return i;
				}
		return -1;

	}

	protected boolean MovedfromPlayArea(int cardnum) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 13; j++)
				if (register[i][j] == cardnum)
					return true;

		return false;
	}

	protected boolean MovedfromLeftArea(int cardnum) {
		// TODO Auto-generated method stub
		for (int i = 7; i < 11; i++)
			for (int j = 0; j < 13; j++)
				if (register[i][j] == cardnum)
					return true;

		return false;
	}

	protected boolean findlocation(int lie, int hang) {
		// TODO Auto-generated method stub
		System.out.println("sddadaasdsa	" + lie + "	" + hang);
		if (register[lie][hang] != 0)
			return true;
		else
			return false;
	}

	protected int getCard(int lie, int hang) {
		// TODO Auto-generated method stub
		if (lie > 6 || hang > 12)
			return -10;
		return register[lie][hang];
	}

	protected void PrintRegister() {
		// TODO Auto-generated method stub
		for (int j = 0; j < 13; j++) {
			for (int i = 0; i < 11; i++)
				System.out.print(register[i][j] + "	");
			System.out.println();
		}

	}

	protected boolean legallPosition(int dragX, int dragY) {
		setLegallPosition();

		for (int i = 0; i < 7; i++)
			if (location[legalposition[i][0]][legalposition[i][1]][0] < dragX
					&& (location[legalposition[i][0]][legalposition[i][1]][0] + 80) > dragX
					&& location[legalposition[i][0]][legalposition[i][1]][2] - 25 < dragY
					&& (location[legalposition[i][0]][legalposition[i][1]][2] + 123 - 25) > dragY) {

				return true;
			}
		return false;
	}

	protected boolean checkVictory() {
		for (int i = 0; i < 4; i++)
			
				while (register[i][12] == 0)
					return false;
		return true;
	

	}

	private void setLegallPosition() {
		// TODO Auto-generated method stub
		int counter = 0;
		for (int i = 0; i < 7; i++)
			if (register[i][0] == 0) {

				legalposition[counter][0] = i;
				legalposition[counter++][1] = 0;
			}

		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 12; j++) {
				if ((register[i][j] != 0 && register[i][j + 1] == 0)) {

					legalposition[counter][0] = i;
					legalposition[counter++][1] = j + 1;

					 System.out.println("legal	" + legalposition[counter -
					 1][0]
					 + "	" + legalposition[counter - 1][1]);
				}

			}
	}

	protected void PrintCard(Card[] card) {
		// TODO Auto-generated method stub
		for(int i=1;i<card.length;i++)
			System.out.println(i+"	"+card[i].bianhao+"	"+card[i].huase+"	"+card[i].daxiao);
		
		
		
	}
}
