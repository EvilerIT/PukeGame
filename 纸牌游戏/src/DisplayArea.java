public class DisplayArea {
	int[] dis = new int[25];
	// int[] doubledis = new int[24];
	int displaynum = 0;
	int counter = 0;

	DisplayArea() {
		for (int i = 0; i < dis.length; i++) {
			dis[i] = 0;
			// doubledis[i] = 0;
		}
	}

	public void addCard(int i, int j) {
		// TODO Auto-generated method stub

		while (i <= j) {

			dis[i - 1] = i;
			i++;
		}
	}

	protected int getCard() {
		// TODO Auto-generated method stub
		int num = 0;
		while (dis[num] != 0) {
			System.out.println(dis[num++] + "	dis[displaynum]");

		}

		int a = 0;
		while (a != 24) {
			while (dis[displaynum] != 0) {
				displaynum = (displaynum + 1) % 24;
				if (displaynum == 0)
					return 23;
				else
					return displaynum - 1;
			}
			counter = (counter + 1) % 25;
			displaynum = (displaynum + 1) % 24;
			a++;
		}
		return -1;
	}

	protected int getCounter() {

		return 0;// counter;
	}

	protected void setZero(int cardnum) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<dis.length;i++)
			if(dis[i]==cardnum)
				dis[i]=0;
		
		
	}
}
