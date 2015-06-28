public class LeftArea {
	//int[][] register = new int[4][13];
	

	LeftArea() {	

	}

	protected int getLie(int x) {
		// TODO Auto-generated method stub
		if (x > 440 && x < 520) {
			return 7;
		}
		if (x > 570 && x < 650) {
			return 8;
		}
		if (x > 700 && x < 780) {
			return 9;
		}
		if (x > 830 && x < 910) {
			return 10;
		}
		return -1;
	}

	

	public boolean legalPosition(int dragX, int dragY) {
		// TODO Auto-generated method stub

		if (dragX > 440 && dragX < 520 && dragY > 50 && dragY < 173)
			return true;
		if (dragX > 570 && dragX < 650 && dragY > 50 && dragY < 173)
			return true;
		if (dragX > 700 && dragX < 780 && dragY > 50 && dragY < 173)
			return true;
		if (dragX > 830 && dragX < 910 && dragY > 50 && dragY < 173)
			return true;

		return false;
	}

}
