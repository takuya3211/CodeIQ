import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.lang.StringBuffer;

class CodeIQ{
    public static void main(String[] args){
    	//ASCII�R�[�h��32(0x20)�`126(0x7e)���g�p�\
    	/*
    	 �E����, �啶��, ������ �� �O�[, �`���L, �p�[ �̊֌W�B
		�E�󔒈ȊO�̋L���́A�����E�啶���E������ �̂�����ɂ�������B
		�E�󔒂� 9, Z, z �ɂ����͏����A����ȊO�̂��ׂĂ̕����ɕ�����B
		�E�����̎�ނ������ꍇ�͕����R�[�h���傫���ق��������B 
    	 */
    	//�L�����g���Ӗ��͂Ȃ��B�󔒂݂̂ɂ͏��Ă邪�������ȊO�̕������󔒂ɏ��Ă邩�炻�����̕�������
    	int i = 0;
    	int fighterNumber = 1001;
    	int battleKaisu = 1;
    	Fighter[] fighter = new Fighter[fighterNumber];
    	fighter[0] = new Fighter();
    	//fighter[0].InputData("ZzZzZzy998Y8Z9yZ9Yy8z 9Y8zYZ98 YzY9 Yz9zy8 z9 8YzY9ZzZzy9Y9Y Zy9z9ZyYzyZ9Yz9YzZ");
    	fighter[0].InputData("y YYY98YzZ  z9yzZ yZ 9Y 99z9ZZ9Y ZzZZZ8ZY8y 9ZY8989 z8 yY 8y 8Yzz8ZzYYz9Yy8Y88y");
    	for (i = 1; i < fighterNumber; i ++) {//�O�ԃt�@�C�^�[�̓`�����s�I���Ƃ��ĕʘg�B�G���̓����_���ō���Ă���B��ŉ��ǂ�����
    		fighter[i] = new Fighter();
    		fighter[i].InputData(fighter[i].MakeRandomString());
    		//System.out.println(fighter[i].FighterString);
    	}
    	//Fighter fighter[j] = new Fighter();
    	//Fighter fighter[i] = new Fighter();
    	int j = 0;
    	for (j =0; j < fighterNumber ; j ++) {
	    	for (i = j+1; i < fighterNumber; i ++) {
		    	//System.out.println("j:" + j + " i:" + i);
		    	fighter[j].ResetMyDamage();
		    	fighter[i].ResetMyDamage();
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
			    	battle.Fight(fighter[j].GetPresentLetter(),	fighter[i].GetPresentLetter());
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
    		System.out.println("number:" + i + " win:" + fighter[i].win + " lose:" + fighter[i].lose + " draw:" + fighter[i].draw + "\t"+ fighter[i].Syouritsu(fighterNumber, i));
    		if (fighter[i].Syouritsu(fighterNumber, i) > top) {
    			second = top;
    			secondFighter = topFighter;
    			top = fighter[i].Syouritsu(fighterNumber, i);
    			topFighter = i;
    		}
    	}
    	System.out.println("Number" + topFighter + "\t rate =" + top);
    	System.out.println(fighter[topFighter].FighterString);
    	System.out.println("Number" + secondFighter + "\t rate =" + second);
    	System.out.println(fighter[secondFighter].FighterString);
    	
    }
    

}

class Battle {
	static char hantei = 0; //0�͗�O�A1��champion�̏����A2��champion�̕����A3�͈�������
    public void Fight(char champion, char challenger) {

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
	public void InputData(String inputdata){
		FighterString = inputdata;
		FighterData = inputdata.toCharArray();
		dataLength = inputdata.length();
	}
	public double Syouritsu(long fighterNumber , long dare){
		double syouritu = 0;
		syouritu = (double)win / (double)(fighterNumber - 1 - draw );
		return syouritu;
	}
	
	public void ResetMyDamage(){
		damage = 0;
	}
	public void ResetMyself(){
		FighterString = "";
		dataLength = 0;
		damage = 0;
	}
	public void ResetAllMyself() {
		FighterString = "";
		dataLength = 0;
		damage = 0;
		win = 0;
		lose = 0;
		draw = 0;
	}
	public char GetPresentLetter() {
		return FighterData[damage];
	}
	
	public String MakeRandomString(){
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

