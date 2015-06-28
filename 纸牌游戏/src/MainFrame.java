import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

//没有写分牌函数，没有写发牌函数,删除发牌区的问题,好像没问题了
public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int discounter = -1;
	Card[] card = new Card[53];
	DisplayArea displayarea = new DisplayArea();
	PlayArea playarea = new PlayArea();
	LeftArea leftarea = new LeftArea();
	JPanel pane = new JPanel();
	JLabel backlabel[] = new JLabel[23];
	int back = 1;
	int[] temp = new int[13];// 将要移动的量暂时放到temp数组中区

	MainFrame() {

		pane.setBorder(BorderFactory.createLineBorder(Color.red, 3));
		this.add(pane);
		pane.setLayout(null);
		pane.setBounds(0, 0, 1000, 800);
		setBackLabel();

		SetBack();
		setDispaly();
		IntialCard();
		AddPicturetoBoard();
		setListener();
	}

	private void setDispaly() {
		JLabel displaylabel = new JLabel();
		Icon icon = new ImageIcon("images/back.jpg");
		displaylabel.setIcon(icon);
		pane.add(displaylabel);
		displaylabel.setBounds(50, 50, 80, 123);
		displaylabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				displayarea.counter = (displayarea.counter + 1) % 25;
				if (displayarea.counter == 0) {
					for (int i = 0; i < 24; i++)
						if (displayarea.dis[i] != 0) {
							card[displayarea.dis[i]]
									.setBounds(1000, 50, 10, 23);

						}
				} else {

					discounter = displayarea.getCard();

					if (discounter != -1) {
						pane.add(card[displayarea.dis[discounter]], 0);
						card[displayarea.dis[discounter]].setBounds(180, 50,
								80, 123);
					}
					

				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});

	}

	private void setBackLabel() {
		JLabel[] left = new JLabel[4];
		Icon icon = new ImageIcon("images/left.jpg");
		for (int i = 0; i < left.length; i++) {
			left[i] = new JLabel();
			left[i].setIcon(icon);
			pane.add(left[i], -1);
			left[i].setBounds(440 + 130 * i, 50, 80, 123);
		}

	}

	private void SetBack() {
		// TODO Auto-generated method stub
		Icon icon = new ImageIcon("images/back.jpg");
		for (int i = 0; i < backlabel.length; i++) {
			backlabel[i] = new JLabel();
			backlabel[i].setIcon(icon);
			pane.add(backlabel[i], 0);

		}
	}

	private void IntialCard() {
		// 初始化纸牌将0-----23放到DisplayArea
		AddValue();
		displayarea.addCard(1, 24);
		SetLocation();
		updateShow(playarea.getregister());
		AddPicturetoCard();
	}

	private void updateShow(int[][] register) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < i; j++) {
				card[register[i][j]].show = 0;

			}
	}

	private void setListener() {
		// 为每一张纸牌设置一个监听

		for (int i = 1; i < card.length; i++) {
			Listener mouse = new Listener(i);
			card[i].addMouseMotionListener(mouse);
			card[i].addMouseListener(mouse);
		}

	}

	private void AddPicturetoBoard() {

		for (int i = 25; i < card.length; i++) {
			pane.add(card[i], 0);

			if (card[i].show == 1)
				card[i].setBounds(card[i].left, card[i].up, 80, 123);
			else {
				// card[i].setBounds(card[i].left, card[i].up, 80, 123);
				backlabel[back++].setBounds(card[i].left, card[i].up, 80, 123);
			}

		}
	}

	private void AddPicturetoCard() {
		// TODO Auto-generated method stub
		String str;
		String str1 = "images/";
		String str2 = ".jpg";
		for (int i = 1; i < card.length; i++) {
			str = card[i].getString();
			card[i].setPicture(str1 + str + str2);
		}
	}

	private void SetLocation() {// 設置紙牌初始位置
		int a = 25;
		for (int i = 0, j = 0; i < 7; i++) {
			j = 0;
			while (j <= i) {
				card[a].setLocation(playarea.getlocation(i, j));
				card[a].lie = i;
				card[a].hang = j;
				playarea.addCard(i, j, a++);
				j++;
			}
		}
		// System.out.println(a);
	}

	private void AddValue() {

		for (int i = 1; i < card.length; i++) {
			card[i] = new Card();
			card[i].bianhao = i;
			card[i].setProperty(((i - 1) % 4) + 1, ((i - 1) % 13) + 1);

		}
		MakeRandom();
	}

	private void MakeRandom() {
		Random rd = new Random();
		int count[] = new int[52];
		int num = 0;// 用于跟踪count数组的存储位置
		while (num != 52) {
			int xx = rd.nextInt(53);
			if (!Cunzai(xx, count)) {
				count[num++] = xx;
			}
		}

		for (int i = 0; i < 52; i += 2)
			swap(card[count[i]], card[count[i + 1]]);
		playarea.PrintCard(card);
		for (int i = 0; i < 52; i++)
			System.out.print(count[i] + "	");

	}

	private void swap(Card card2, Card card3) {
		// 交换card2与card3
		Card tempcard = new Card();
	
		Copy(tempcard,card2);
		Copy(card2,card3);
		Copy(card3,tempcard);
		
	

	}

	private void Copy(Card tempcard, Card card2) {
		// TODO Auto-generated method stub
		
		tempcard.daxiao=card2.daxiao;
		tempcard.huase=card2.huase;
		//tempcard.daxiao=card2.daxiao;
		
	}

	private boolean Cunzai(int xx, int[] count) {
		// TODO Auto-generated method stub

		for (int i = 0; i < 52; i++)
			if (count[i] == xx)
				return true;
		return false;
	}

	class Listener implements MouseListener, MouseMotionListener {
		int cardnum = 0;

		Listener(int i) {
			cardnum = i;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (playarea.MovedfromPlayArea(cardnum)) {

				Maketemp(cardnum);
				// System.out.println("temp");

				/*
				 * for (int t = 0; t < temp.length; t++) System.out.print("	" +
				 * temp[t]); System.out.println();
				 */
			} else {
				temp[0] = card[cardnum].bianhao;
				/*
				 * for (int t = 0; t < temp.length; t++) System.out.println("0"
				 * + temp[t]);
				 */
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {

			int dragX = card[cardnum].getX() + e.getX();
			int dragY = card[cardnum].getY() + e.getY() - 1;

			if (playarea.legallPosition(dragX, dragY)// 放的位置是否合理
					|| leftarea.legalPosition(dragX, dragY)) {
				if (playarea.legallPosition(dragX, dragY)) {
					SituationOne(dragX, dragY, cardnum);
				}
				if (leftarea.legalPosition(dragX, dragY)) {
					SituationTwo(dragX, dragY, cardnum);
				}
			} else
				// if (card[cardnum].left != 0)

				IllegalMove();

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

			int dragX = card[cardnum].getX() + e.getX();
			int dragY = card[cardnum].getY() + e.getY();
			setReleasedAndDragged(dragX, dragY, 0, 0, 0);

		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
		}

	}

	private void changePlayArea(int lie, int hang, int cardnum) {
		// 第一个参数应该为要添加到的列号
		int a = 0;
		while (temp[a] != 0) {
			if (card[cardnum].lie != -1)// 不是来自发牌区
				playarea.removeCard(card[temp[a]].lie, card[temp[a]].hang);
			else
				displayarea.setZero(cardnum);
				//displayarea.dis[discounter] = 0;
			playarea.addCard(lie, hang + a, card[temp[a]].bianhao);
			a++;
		}
	}

	private void changeLocation(int left, int up, int lie, int hang) {
		// TODO Auto-generated method stub
		int a = 0;
		while (a <= 12 && temp[a] != 0) {

			card[temp[a]].left = left;
			card[temp[a]].up = up + 25 * a;

			card[temp[a]].lie = lie;
			card[temp[a]].hang = hang + a;
			// System.out.println(a + "	" + temp[a] + "	" + card[temp[a]].left
			// + "	" + card[temp[a]].up);
			a++;
		}
	}

	private void setReleasedAndDragged(int left, int up, int lie, int hang,
			int num) {
		// 0代表处于拖动的状态，改变地址函数无效
		int a = 0;
		while (a <= 12 && temp[a] != 0) {
			pane.add(card[temp[a]], 0);
			card[temp[a]].setBounds(left, up + 25 * a, 80, 123);

			a++;
		}
		if (num == 1)
			changeLocation(left, up, lie, hang);// 改变Card中记录的自己的坐标位置以及从那一行开始进行改变
	}

	private boolean CheckRule(int check, int cardnum, int i) {// 当下面有纸牌，有两种情况，加入到不同区域
		// 遊戲區有牌情況
		if (i == 1) {
			if (card[cardnum].daxiao == card[check].daxiao - 1)
				if (card[cardnum].huase % 2 == 0) {
					if (card[check].huase % 2 == 1)
						return true;
				} else if (card[cardnum].huase % 2 == 1) {
					if (card[check].huase % 2 == 0)
						return true;
				}
		}
		if (i == 2) {
			if ((card[cardnum].daxiao == card[check].daxiao + 1)
					&& (card[cardnum].huase == card[check].huase))
				return true;
		}
		return false;
	}

	public boolean CheckBehind(Card c) {
		// 检查后面是否有排跟随移动
		System.out.println(c.hang);
		if (c.hang < 12 && playarea.findlocation(c.lie, c.hang + 1))
			return true;
		return false;
	}

	public void SituationOne(int dragX, int dragY, int cardnum) {
		// 移动到游戏区中
		if (isCardunder(dragX, dragY)) {
			int check = CheckCard(pane.getComponentAt(dragX, dragY));// 获得当前坐标位置下的牌的编号
			// System.out.println("物品们");
			if (CheckRule(check, cardnum, 1)) {
				CheckShow(cardnum);
				changePlayArea(card[check].lie, card[check].hang + 1, cardnum);
				setReleasedAndDragged(card[check].left, card[check].up + 25,
						card[check].lie, card[check].hang + 1, 1);

				ClearTemp();

			} else
				IllegalMove();
		} else if (card[cardnum].daxiao == 13) {// 如果所在列的没有牌
			if (isEmptyLie(dragX, dragY)) {
				CheckShow(cardnum);
				changePlayArea(playarea.getLie(dragX), 0, cardnum);

				setReleasedAndDragged(playarea.getLie(dragX) * 130 + 50, 273,
						playarea.getLie(dragX), 0, 1);

				ClearTemp();

			} else
				IllegalMove();

		} else
			IllegalMove();

	}

	private boolean isEmptyLie(int dragX, int dragY) {
		// 坐标所在列为空
		int x = playarea.getLie(dragX);

		if (playarea.register[x][0] != 0)
			return false;

		return true;
	}

	private int CheckCard(Component component) {
		// TODO Auto-generated method stub
		for (int x = 0; x < card.length; x++) {
			if (component == card[x]) {
				return x;
			}
		}
		return 0;
	}

	public void SituationTwo(int dragX, int dragY, int cardnum) {
		// 向右上角加入纸牌的一系列方法
		int num = 0;
		while (temp[num] != 0)
			num++;
		if (num != 1) {// 往右上角加的牌的数量不能超过一张

			IllegalMove();

		} else {// 不超过一张
			if (isCardunder(dragX, dragY)) {// 下面有牌
				int check = CheckCard(pane.getComponentAt(dragX, dragY));// 获得当前坐标位置下的牌的编号

				if (CheckRule(check, cardnum, 2)) {// 来自leftarea的话

					if (card[cardnum].lie > 6
							&& cardnum != playarea.register[card[check].lie][card[check].hang + 1])
						IllegalMove();
					else {

						CheckShow(cardnum);
						pane.add(card[cardnum], 0);

						playarea.addCard(leftarea.getLie(dragX),
								card[cardnum].bianhao);

						if (card[cardnum].lie != -1)// 不是来自发牌区
							playarea.removeCard(card[cardnum].lie,
									card[cardnum].hang);
						else{
							displayarea.setZero(cardnum);
							
							//displayarea.dis[discounter] = 0;
							}

						changeCardSelf(leftarea.getLie(dragX), playarea
								.getHang(leftarea.getLie(dragX)), cardnum);

						card[cardnum].left = (leftarea.getLie(dragX) - 7) * 130 + 440;
						card[cardnum].up = 50;
						card[cardnum]
								.setBounds(card[cardnum].left, 50, 80, 123);

						ClearTemp();

					}
				} else
					IllegalMove();

				// 加入

			} else// 没有纸牌
			{
				if (card[cardnum].daxiao == 1
						&& cardnum != playarea.register[leftarea.getLie(dragX)][0]) {
					System.out.println("dasdasdasd");
					CheckShow(cardnum);
					pane.add(card[cardnum], 0);

					playarea.addCard(leftarea.getLie(dragX),
							card[cardnum].bianhao);

					if (card[cardnum].lie != -1)// 不是来自发牌区
						playarea.removeCard(card[cardnum].lie,
								card[cardnum].hang);
					else
						displayarea.setZero(cardnum);
						//displayarea.dis[discounter] = 0;

					changeCardSelf(leftarea.getLie(dragX), playarea
							.getHang(leftarea.getLie(dragX)), cardnum);

					card[cardnum].left = (leftarea.getLie(dragX) - 7) * 130 + 440;
					card[cardnum].up = 50;
					card[cardnum].setBounds(card[cardnum].left, 50, 80, 123);

					ClearTemp();

				}// 加入
				else
					IllegalMove();

			}

		}

	}

	private void changeCardSelf(int lie, int hang, int cardnum) {
		// cardnum加入到当前列，当前行
		card[cardnum].lie = lie;
		card[cardnum].hang = hang;

	}

	private boolean isCardunder(int dragX, int dragY) {
		// 当前坐标下面是否有牌

		if (pane.getComponentAt(dragX, dragY) instanceof Card)
			return true;

		return false;
	}

	private void IllegalMove() {
		// 非法移动的话将数组中存放的card放回原处
		// System.out.println("illegal");
		int a = 0;
		while (a <= 12 && temp[a] != 0) {

			pane.add(card[temp[a]], 0);
			if (card[temp[a]].left != 0)
				card[temp[a]].setBounds(card[temp[a]].left, card[temp[a++]].up,
						80, 123);
			else {

				card[temp[a++]].setBounds(180, 50, 80, 123);
			}
		}
		ClearTemp();

	}

	public void Maketemp(int next) {

		int tempcounter = 0;

		int link = card[next].hang;

		temp[tempcounter++] = card[next].bianhao;

		if (card[next].hang != -1) {
			for (; link <= 12; link++) {

				if ((next = playarea.getCard(card[next].lie,
						card[next].hang + 1)) != -1) {
					if (next == 0)
						link = 13;
					temp[tempcounter++] = next;

				}
			}
		}

	}

	public void ClearTemp() {
		// TODO Auto-generated method stub
		for (int i = 0; i < temp.length; i++) {
			// System.out.println("	temp	"+temp[i]);

			temp[i] = 0;
		}
	}

	public void CheckShow(int cardnum) {
		// TODO Auto-generated method stub
		playarea.PrintRegister();

		if (playarea.checkVictory())
			System.out.println("胜利");

		if (card[cardnum].lie < 7 && card[cardnum].lie >= 0
				&& card[cardnum].hang != 0) {
			int beyond = playarea.register[card[cardnum].lie][card[cardnum].hang - 1];
			if (card[beyond].show != 1) {
				card[beyond].show = 1;
				int i = card[beyond].lie;
				YinCang(i);
				pane.add(card[beyond], 0);
				card[beyond].setBounds(card[beyond].left, card[beyond].up, 80,
						123);
			}

		}

	}

	int one = 1, two = 3, three = 6, four = 10, five = 15, six = 21;

	private void YinCang(int i) {
		// TODO Auto-generated method stub
		Icon icon = new ImageIcon("");
		switch (i) {
		case 1:
			backlabel[1].setIcon(icon);
			break;
		case 2:
			backlabel[two--].setIcon(icon);
			break;
		case 3:
			backlabel[three--].setIcon(icon);
			break;
		case 4:
			backlabel[four--].setIcon(icon);
			break;
		case 5:
			backlabel[five--].setIcon(icon);
			break;
		case 6:
			backlabel[six--].setIcon(icon);
			break;
		}
	}

}
