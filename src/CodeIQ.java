import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.lang.StringBuffer;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

class CodeIQ{
    public static void main(String[] args) throws IOException{
    	//ASCII�R�[�h��32(0x20)�`126(0x7e)���g�p�\
    	/*
    	 �E����, �啶��, ������ �� �O�[, �`���L, �p�[ �̊֌W�B
		�E�󔒈ȊO�̋L���́A�����E�啶���E������ �̂�����ɂ�������B
		�E�󔒂� 9, Z, z �ɂ����͏����A����ȊO�̂��ׂĂ̕����ɕ�����B
		�E�����̎�ނ������ꍇ�͕����R�[�h���傫���ق��������B 
    	 */
    	//�L�����g���Ӗ��͂Ȃ��B�󔒂݂̂ɂ͏��Ă邪�������ȊO�̕������󔒂ɏ��Ă邩�炻�����̕�������
		int k = 0;
		Dna newFighter = new Dna();
		Date d = new Date();
		String fileName = "/Users/takuya/Dropbox/program/workspace/CodeIQ/src/" + "fightlog_" + toString(d) + ".txt";//�t�@�C�����͓������g��
		File file = new File(fileName);
		FileWriter filewriter = new FileWriter(file,true);

		
		for (k = 0; k < 100; k ++){
		    	int i = 0;
		    	int fighterNumber = 1001;
		    	int battleKaisu = 1;
		
		    	Fighter[] fighter = new Fighter[fighterNumber];
		
		    	fighter[0] = new Fighter();
		    	//fighter[0].InputData("ZzZzZzy998Y8Z9yZ9Yy8z 9Y8zYZ98 YzY9 Yz9zy8 z9 8YzY9ZzZzy9Y9Y Zy9z9ZyYzyZ9Yz9YzZ");
		    	if (k  == 0 ) {
		    		//fighter[0].inputData("ZzZyz9z88zy8Z9 zZy8 Zy8yZyZyYz9z88Y 8z8zZy8z89Y9Y9Y 9Z YY9Y 9z8Y8y9Y 9Z8ZyZz8Zz");
		    		fighter[0].inputData(fighter[i].makeRandomString());
		    		System.out.println(fighter[0].FighterString);
		    	}else {
		    		fighter[0].inputData(newFighter.getChildString());
		    	}
		    	//System.out.println("former champ = " + fighter[0].FighterString);
		    	for (i = 1; i < fighterNumber; i ++) {//�O�ԃt�@�C�^�[�̓`�����s�I���Ƃ��ĕʘg�B�G���̓����_���ō���Ă���B��ŉ��ǂ�����
		    		fighter[i] = new Fighter();
		    		fighter[i].inputData(fighter[i].makeRandomString());
		    		//System.out.println(fighter[i].FighterString);
		    	}
		    	//Fighter fighter[j] = new Fighter();
		    	//Fighter fighter[i] = new Fighter();
		    	int j = 0;
		    	for (j =0; j < fighterNumber ; j ++) {
			    	for (i = j+1; i < fighterNumber; i ++) {
				    	//System.out.println("j:" + j + " i:" + i);
				    	fighter[j].resetMyDamage();
				    	fighter[i].resetMyDamage();
				    	//fighter[j].ResetAllMyself();
				    	//fighter[i].ResetAllMyself();
				    	//fighter[j].InputData("ZzZzZzy998Y8Z9yZ9Yy8z 9Y8zYZ98 YzY9 Yz9zy8 z9 8YzY9ZzZzy9Y9Y Zy9z9ZyYzyZ9Yz9YzZ");
			    		//fighter[j].InputData(fighter[j].MakeRandomString());
				    	//fighter[i].InputData(fighter[i].MakeRandomString());
				    	
				    	//System.out.println("hello world!");
				    	Battle  battle = new  Battle();
					    	//�����̃��[�v
				    	while (fighter[j].damage< fighter[j].dataLength || fighter[i].damage < fighter[i].dataLength){
				    		/*
				    		System.out.println(fighter[j].damage + ":" + fighter[i].damage);
				        	System.out.println(fighter[j].FighterString.substring(fighter[j].damage, fighter[j].dataLength));
				        	System.out.println(fighter[i].FighterString.substring(fighter[i].damage, fighter[i].dataLength));
				        	System.out.println("");
				        	*/
				        	if (fighter[j].damage == fighter[j].dataLength || fighter[i].damage == fighter[i].dataLength) break;
					    	battle.fight(fighter[j].getPresentLetter(),	fighter[i].getPresentLetter());
					    	if (battle.hantei == 1) {
					    		fighter[i].damage ++;
					    	} else if (battle.hantei == 2) {
					    		fighter[j].damage ++;
					    	} else if (battle.hantei == 3) {
					    		fighter[j].damage ++;
					    		fighter[i].damage ++;
					    	} else {
					    		System.out.println("��O���������܂���");
					    	}
				    	}
					    	
				    	//���s�̕\��
				    	if (fighter[j].damage == fighter[i].damage) {
				    		//System.out.println("��������");
				    		fighter[j].draw ++;
				    		fighter[i].draw ++;
				    	} else if(fighter[j].damage < fighter[i].damage) {
				    		//System.out.println("�`�����s�I���̏���");
				    		fighter[j].win ++;
				    		fighter[i].lose ++;
				    	} else if (fighter[j].damage > fighter[i].damage) {
				    		//System.out.println("����҂̏���" + " : " + fighter[j].damage + "/" +  fighter[i].damage);
				    		//System.out.println(fighter[i].FighterString + "  " + fighter[i].damage);
				    		fighter[j].lose ++;
				    		fighter[i].win ++;
				    	} else {
				    		System.out.println("���s�̔���ɗ�O���������܂���");
				    	}
		
			    	}
			    	//battle.PrintKekka();
			    	//System.out.println((double)fighter[j].win/(double)(kaisu- fighter[j].draw) + "  :  " + fighter[j].draw + " draw");
			    	//System.out.println((double)fighter[j].win/(double)(battleKaisu- fighter[j].draw));
			    }
		    	double top = 0.0 ,second = 0.0;
		    	int topFighter = 0, secondFighter = 0;
		    	for(i = 0;i < fighterNumber; i++) {
		    		if ( i == 0) printIt(fileName,"number:" + i + " win:" + fighter[i].win + " lose:" + fighter[i].lose + " draw:" + fighter[i].draw + "\t"+ fighter[i].winningRate(fighterNumber, i));
		    		if (fighter[i].winningRate(fighterNumber, i) > top) {
		    			second = top;
		    			secondFighter = topFighter;
		    			top = fighter[i].winningRate(fighterNumber, i);
		    			topFighter = i;
		    		}
		    	}
		    	if(topFighter != 0) printIt(fileName,"----- New champion was born! -----");
		    	fighter[0].inputData(newFighter.makeChild(fighter[0].FighterString, fighter[topFighter].FighterString));
		    	//System.out.println("Number" + topFighter + "\t rate =" + top + ": " + "Number" + secondFighter + "\t rate =" + second);
		    	//System.out.println(fighter[topFighter].FighterString);
		//    	System.out.println("Number" + secondFighter + "\t rate =" + second);
		    	//System.out.println(fighter[secondFighter].FighterString);
		    	//System.out.println("child");
		    	//System.out.println(newFighter.makeChild(fighter[topFighter].FighterString,fighter[secondFighter].FighterString));
		    	//filewriter.write(newFighter.makeChild(fighter[topFighter].FighterString,fighter[secondFighter].FighterString));
		    	//printIt(fileName, newFighter.makeChild(fighter[topFighter].FighterString,fighter[secondFighter].FighterString));
		    	printIt(fileName, fighter[0].FighterString);
		    	
		    }
		filewriter.close();
    }
    
    public static String toString(Date d) {
    	  SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd_HHmm");
    	  return f.format(d);
    	}
    
    public static void printIt(String fileName, String str) throws IOException {
		File file = new File(fileName);
		FileWriter filewriter = new FileWriter(file,true);
    	System.out.println(str);
    	filewriter.write(str + "\n");
    	filewriter.close();
    }

}

class Battle {
	static char hantei = 0; //0�͗�O�A1��champion�̏����A2��champion�̕����A3�͈�������
    public void fight(char champion, char challenger) {

    	if (champion == 32) {//�`�����s�I�����X�y�[�X�������ꍇ
    		hantei = 2; //�Ƃ肠���������̃t���O
    		if(challenger == ' ') hantei = 3; //����҂��X�y�[�X�Ȃ��������
    		if(challenger == '9' || challenger == 'Z' || challenger == 'z') hantei = 1; //����҂�9,Z,z�Ȃ�`�����s�I���̏���
    		
//��������`�����s�I��������    		
    	} else if (champion >= '0' && champion <= '9') { //�`�����s�I����0~9�̐����������ꍇ
    		hantei = 0;//�Ƃ肠������O�̃t���O
    		if(challenger >= '0' && challenger <= '9') {//����҂�����
    			if(champion == challenger) {
    				hantei = 3;//���������Ȃ��������
    			}else if (champion > challenger ) {
    				hantei = 1;
    			}else if (champion < challenger) {
    				hantei = 2;
    			} else {
    				hantei = 0;
    			}
    		}else if(challenger >= 'A' && challenger <='Z') {//����҂��啶��
    			hantei = 1;
    		}else if (challenger >='a' && challenger <= 'z'){//����҂�������
    			hantei = 2;
    		}else if (challenger == ' ') {//����҂��X�y�[�X
    			if(champion == '9'){
    				hantei = 2;
    			}else {
    				hantei = 1;
    			}
    		}else {
    			hantei = 1;
    		}
//�����܂Ń`�����s�I��������
    		
    		
    		
//��������`�����s�I�����啶��    		
    	} else if (champion >= 'A' && champion <= 'Z') { //�`�����s�I�����啶���������ꍇ
    		hantei = 0;//�Ƃ肠������O�̃t���O
    		if(challenger >= 'A' && challenger <= 'Z') {//����҂��啶��
    			if(champion == challenger) {
    				hantei = 3;//�����啶���Ȃ��������
    			}else if (champion > challenger ) {
    				hantei = 1;
    			}else if (champion < challenger) {
    				hantei = 2;
    			} else {
    				hantei = 0;
    			}
    		}else if(challenger >= '0' && challenger <='9') {//����҂������͕���
    			hantei = 2;
    		}else if (challenger >='a' && challenger <= 'z'){//����҂��������͏���
    			hantei = 1;
    		}else if (challenger == ' ') {//����҂��X�y�[�X
    			if(champion == 'Z'){
    				hantei = 2;
    			}else {
    				hantei = 1;
    			}
    		}else {
    			hantei = 1;
    		}
//�����܂Ń`�����s�I�����啶��
    		
    		
//��������`�����s�I����������    		
    	} else if (champion >= 'a' && champion <= 'z') { //�`�����s�I�����������������ꍇ
    		hantei = 0;//�Ƃ肠������O�̃t���O
    		if(challenger >= 'a' && challenger <= 'z') {//����҂�������
    			if(champion == challenger) {
    				hantei = 3;//�����������Ȃ��������
    			}else if (champion > challenger ) {
    				hantei = 1;
    			}else if (champion < challenger) {
    				hantei = 2;
    			} else {
    				hantei = 0;
    			}
    		}else if(challenger >= '0' && challenger <='9') {//����҂������͏���
    			hantei = 1;
    		}else if (challenger >='A' && challenger <= 'Z'){//����҂��啶���͕���
    			hantei = 2;
    		}else if (challenger == ' ') {//����҂��X�y�[�X
    			if(champion == 'z'){
    				hantei = 2;
    			}else {
    				hantei = 1;
    			}
    		}else {
    			hantei = 1;
    		}
//�����܂Ń`�����s�I����������
    		
    		
    		
 //��������`�����s�I�����L��    		
    	} else if ((champion >= '!' && champion <= '/') || (champion >= ':' && champion <= '@') || (champion >= '[' && champion <= 96 ) || (champion >= '{' && champion <= '~')) { //�`�����s�I�����L���������ꍇ
    		hantei = 0;//�Ƃ肠������O�̃t���O
    		if((champion >= '!' && champion <= '/') || (champion >= ':' && champion <= '@') || (champion >= '[' && champion <= 96 ) || (champion >= '{' && champion <= '~')) {//����҂��L��
    			if(champion == challenger) {
    				hantei = 3;//�����L���Ȃ��������
    			}else if (champion > challenger ) {
    				hantei = 1;
    			}else if (champion < challenger) {
    				hantei = 2;
    			} else {
    				hantei = 0;
    			}
    		}else if (challenger == ' ') {//����҂��X�y�[�X�Ȃ珟��
    			hantei = 1;

    		}else {
    			hantei = 2; //���͑S�ĕ���
    		}
//�����܂Ń`�����s�I�����L��
    		
    	}
    }
    
    public static void PrintKekka() {
    	if (hantei == 1) {
    		System.out.println("�`�����s�I���̏���");
    	} else if (hantei == 2) {
    		System.out.println("�`�����s�I���̕���");
    	} else if (hantei == 3) {
    		System.out.println("��������");
    	} else {
    		System.out.println("��O���������܂���");
    	}
    	
    }
}

class Fighter{
	String FighterString = "";
	char[] FighterData;
	int dataLength = 0;
	int damage = 0;
	long win = 0;
	long lose = 0;
	long draw = 0;
	final static char[] CharList= new char[] {' ', '8', '9', 'Y', 'Z', 'y', 'z'};
	
	public void inputData(String inputdata){
		FighterString = inputdata;
		FighterData = inputdata.toCharArray();
		dataLength = inputdata.length();
	}
	public double winningRate(long fighterNumber , long dare){
		double syouritu = 0;
		syouritu = (double)win / (double)(fighterNumber - 1 - draw );
		return syouritu;
	}
	
	public void resetMyDamage(){
		damage = 0;
	}
	public void resetMyself(){
		FighterString = "";
		dataLength = 0;
		damage = 0;
	}
	public void resetAllMyself() {
		FighterString = "";
		dataLength = 0;
		damage = 0;
		win = 0;
		lose = 0;
		draw = 0;
	}
	public char getPresentLetter() {
		return FighterData[damage];
	}
	
	public String makeRandomString(){
    	Random rnd = new Random();
    	String s1 = "";
    	StringBuffer buf = new StringBuffer();
    	int i =0;
   	    for (i = 0 ; i < 79 ; i++){
    		buf.append(CharList[rnd.nextInt(CharList.length)]);
    	}
    	String s = buf.toString();
    	//System.out.println(s);
    	return s;
	}

}

class Dna {
	String child = "";
	public String makeChild(String father, String mother){
		child="";
		child = twoPointCrossing(father, mother);
		return child;
	}
	
	public String onePointCrossing(String father , String mother) {
		child = "";
		if (father.length() != mother.length()) System.out.println("���ƕ�̕���������v���Ă��܂���");
		Random rnd = new Random();
		int crossingPosition = rnd.nextInt(father.length());
    	StringBuffer buf = new StringBuffer();
    	
    	//�����_���ɕ��ƕ��DNA���ǂ�������ɂ��邩�����߂�
		if(rnd.nextBoolean()) {
	    	buf.append(father.substring(0, crossingPosition));
	    	buf.append(mother.substring(crossingPosition ,mother.length()));
		}else {
	    	buf.append(mother.substring(0, crossingPosition));
	    	buf.append(father.substring(crossingPosition ,father.length()));
		}
		child = buf.toString();
		return child;
	}
	
	public String twoPointCrossing(String father, String mother){
		child = "";
		if (father.length() != mother.length()) System.out.println("���ƕ�̕���������v���Ă��܂���");
		Random rnd = new Random();
		int crossingPositionStart=1  , crossingPositionEnd=2;//�����l�͓K��
		int random1=rnd.nextInt(father.length()); 
		int random2=rnd.nextInt(father.length());
		while (random1 == random2 && random1 - random2 < 2 && random2 - random1 < 2) {
			random1 = rnd.nextInt(father.length());
			random2 = rnd.nextInt(father.length());
		}
		//System.out.println("random:" + random1  + " " + random2);
		if (random1 < random2) {
			crossingPositionStart = random1;
			crossingPositionEnd = random2;
		} else if (random1 > random2){
			crossingPositionStart = random2;
			crossingPositionEnd = random1;
		} else {
			System.out.println("2�_�����@�ɂ����ė�O���������܂���");
		}
		
    	StringBuffer buf = new StringBuffer();
    	//�����_���ɕ��ƕ��DNA���ǂ�������ɂ��邩�����߂�
		if(rnd.nextBoolean()) {
			//�������
	    	buf.append(father.substring(0, crossingPositionStart ));
	    	buf.append(mother.substring(crossingPositionStart, crossingPositionEnd));
	    	buf.append(father.substring(crossingPositionEnd, father.length() ));
		}else {
			//�ꂩ���
	    	buf.append(mother.substring(0, crossingPositionStart));
	    	buf.append(father.substring(crossingPositionStart, crossingPositionEnd));
	    	buf.append(mother.substring(crossingPositionEnd, mother.length() ));
		}
		child = buf.toString();;
		return child;
	}
	
	public String getChildString(){
		return child;
	}
}
