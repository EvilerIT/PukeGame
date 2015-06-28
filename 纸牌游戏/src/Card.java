import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Card extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int show;//  «∑Ò’π æ
	int left;
	int right;
	int up;
	int down;
	int daxiao;
	int huase;
	int lie;
	int hang;
	int bianhao = 100;

	Card() {
		this.show = 1;
		this.left = 0;
		this.right = 0;
		this.up = 0;
		this.down = 0;
		this.daxiao = 0;
		this.huase = 0;
		this.hang = -1;
		this.lie = -1;
	}

	public void setProperty(int i, int j) {// huase,daxiao
		this.huase = i;
		this.daxiao = j;

	}

	public void setLocation(int[] getlocation) {
		// TODO Auto-generated method stub
		this.left = getlocation[0];
		this.right = getlocation[1];
		this.up = getlocation[2];
		this.down = getlocation[3];
	}

	public String getString() {
		// TODO Auto-generated method stub
		String str = Convert(huase) + Convert(daxiao);
		return str;
	}

	private String Convert(int num) {
		// TODO Auto-generated method stub
		switch (num) {
		case 1:
			return "1";
		case 2:
			return "2";
		case 3:
			return "3";
		case 4:
			return "4";
		case 5:
			return "5";
		case 6:
			return "6";
		case 7:
			return "7";
		case 8:
			return "8";
		case 9:
			return "9";
		case 10:
			return "10";
		case 11:
			return "11";
		case 12:
			return "12";
		case 13:
			return "13";

		default:
			return null;

		}
	}

	protected void setPicture(String string) {
		// TODO Auto-generated method stub
		Icon icon = new ImageIcon(string);
		this.setIcon(icon);
	}
}
