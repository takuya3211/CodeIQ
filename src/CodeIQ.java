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
    	Fighter fighter1 = new Fighter();
    	Fighter fighter2 = new Fighter();
    	int kaisu = 10000;
    	for (i = 0; i < kaisu; i ++) {
	    	
	    	fighter1.InputData(" ZzZy9Z98zY8Z9yZ9Yy8z 9Y8zYZ98 YzY9 Yz9zy8 z9 8YzY9ZzZzy9Y9Y Zy9z9ZyYzyZ9Yz9Yz;");
	    	fighter2.InputData(" ZzZy9Z98zY8Z9yZ9Yy8z 9Y8zYZ98 YzY9 Yz9zy8 z9 8YzY9ZzZzy9Y9Y Zy9z9ZyYzyZ9Yz9Yz;");
	    	
	    	//fighter2.InputData(fighter2.MakeRandomString());
	    	
	    	//System.out.println("hello world!");
	    	Battle  battle = new  Battle();
		    	//�����̃��[�v
	    	while (fighter1.damage< fighter1.dataLength || fighter2.damage < fighter2.dataLength){
	        	//System.out.println(fighter1.damage + ":" + fighter2.damage);
	        	//System.out.println(fighter1.FighterString.substring(fighter1.damage, fighter1.dataLength));
	        	//System.out.println(fighter2.FighterString.substring(fighter2.damage, fighter2.dataLength));
	        	//System.out.println("");
	        	if (fighter1.damage == fighter1.dataLength) break;
	        	if (fighter2.damage == fighter2.dataLength) break;
		    	battle.Fight(fighter1.GetPresentLetter(),	fighter2.GetPresentLetter());
		    	if (battle.hantei == 1) {
		    		fighter2.damage ++;
		    	} else if (battle.hantei == 2) {
		    		fighter1.damage ++;
		    	} else if (battle.hantei == 3) {
		    		fighter1.damage ++;
		    		fighter2.damage ++;
		    	} else {
		    		System.out.println("��O���������܂���");
		    	}
	    	}
		    	
	    	//���s�̕\��
	    	if(fighter1.damage < fighter2.damage) {
	    		//System.out.println("�`�����s�I���̏���");
	    		fighter1.win ++;
	    		fighter2.lose ++;
	    	} else if (fighter1.damage > fighter2.damage) {
	    		//System.out.println("����҂̏���" + " : " + fighter1.damage + "/" +  fighter2.damage);
	    		//System.out.println(fighter2.FighterString);
	    		fighter1.lose ++;
	    		fighter2.win ++;
	    	} else {
	    		//System.out.println("��������");
	    		fighter1.draw ++;
	    		fighter2.draw ++;
	    	}
	    	fighter1.ResetMyself();
	    	fighter2.ResetMyself();
    	}
    	//battle.PrintKekka();
    	System.out.println((double)fighter1.win/(double)kaisu + "  :  " + fighter1.draw + " draw");
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
	
	public void ResetMyself(){
		FighterString = "";
		dataLength = 0;
		damage = 0;
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

