import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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
    	
    	Fighter fighter1 = new Fighter();
    	fighter1.InputData("print \"hello, world!\"#y8y8yzYZyYyY8y8Y8Y8y8YyYZz8yY8yYyYy8YyzY8Y8Yy8yY8Y8y8Y8y8ZYyzYy9y8Y8y8yYy8Y8Y");
    	Fighter fighter2 = new Fighter();
    	fighter2.InputData("begin;writeln(\"hello, world!\");end.(* 8Y 9 Z Z 9z y 8 9 Z Z Z z Z z Y yZ z8 Y z 9 Y Y Y9 Y yz 8Zz*)");
    	
    	System.out.println("hello world!");
    	Battle  battle = new  Battle();
    	int i = 0;
    	System.out.println(fighter1.GetPresentLetter());
    	while (fighter1.damage< fighter1.dataLength || fighter2.damage < fighter2.dataLength){
        	System.out.println(fighter1.damage + ":" + fighter2.damage);
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

    	//battle.PrintKekka();
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
	public void InputData(String inputdata){
		FighterString = inputdata;
		FighterData = inputdata.toCharArray();
		dataLength = inputdata.length();
	}
	
	public char GetPresentLetter() {
		return FighterData[damage];
	}
	

}

