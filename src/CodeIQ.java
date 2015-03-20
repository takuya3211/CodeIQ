import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.lang.StringBuffer;

class CodeIQ{
    public static void main(String[] args){
    	//ASCIIコードで32(0x20)～126(0x7e)が使用可能
    	/*
    	 ・数字, 大文字, 小文字 が グー, チョキ, パー の関係。
		・空白以外の記号は、数字・大文字・小文字 のいずれにも負ける。
		・空白は 9, Z, z にだけは勝つが、それ以外のすべての文字に負ける。
		・文字の種類が同じ場合は文字コードが大きいほうが勝ち。 
    	 */
    	//記号を使う意味はない。空白のみには勝てるが末文字以外の文字も空白に勝てるからそっちの方が強い
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
		    	//闘いのループ
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
		    		System.out.println("例外が発生しました");
		    	}
	    	}
		    	
	    	//勝敗の表示
	    	if(fighter1.damage < fighter2.damage) {
	    		//System.out.println("チャンピオンの勝ち");
	    		fighter1.win ++;
	    		fighter2.lose ++;
	    	} else if (fighter1.damage > fighter2.damage) {
	    		//System.out.println("挑戦者の勝ち" + " : " + fighter1.damage + "/" +  fighter2.damage);
	    		//System.out.println(fighter2.FighterString);
	    		fighter1.lose ++;
	    		fighter2.win ++;
	    	} else {
	    		//System.out.println("引き分け");
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
	static char hantei = 0; //0は例外、1はchampionの勝ち、2はchampionの負け、3は引き分け
    public void Fight(char champion, char challenger) {

    	if (champion == 32) {//チャンピオンがスペースだった場合
    		hantei = 2; //とりあえず負けのフラグ
    		if(challenger == ' ') hantei = 3; //挑戦者もスペースなら引き分け
    		if(challenger == '9' || challenger == 'Z' || challenger == 'z') hantei = 1; //挑戦者が9,Z,zならチャンピオンの勝ち
    		
//ここからチャンピオンが数字    		
    	} else if (champion >= '0' && champion <= '9') { //チャンピオンが0~9の数字だった場合
    		hantei = 0;//とりあえず例外のフラグ
    		if(challenger >= '0' && challenger <= '9') {//挑戦者が数字
    			if(champion == challenger) {
    				hantei = 3;//同じ数字なら引き分け
    			}else if (champion > challenger ) {
    				hantei = 1;
    			}else if (champion < challenger) {
    				hantei = 2;
    			} else {
    				hantei = 0;
    			}
    		}else if(challenger >= 'A' && challenger <='Z') {//挑戦者が大文字
    			hantei = 1;
    		}else if (challenger >='a' && challenger <= 'z'){//挑戦者が小文字
    			hantei = 2;
    		}else if (challenger == ' ') {//挑戦者がスペース
    			if(champion == '9'){
    				hantei = 2;
    			}else {
    				hantei = 1;
    			}
    		}else {
    			hantei = 1;
    		}
//ここまでチャンピオンが数字
    		
    		
    		
//ここからチャンピオンが大文字    		
    	} else if (champion >= 'A' && champion <= 'Z') { //チャンピオンが大文字だった場合
    		hantei = 0;//とりあえず例外のフラグ
    		if(challenger >= 'A' && challenger <= 'Z') {//挑戦者が大文字
    			if(champion == challenger) {
    				hantei = 3;//同じ大文字なら引き分け
    			}else if (champion > challenger ) {
    				hantei = 1;
    			}else if (champion < challenger) {
    				hantei = 2;
    			} else {
    				hantei = 0;
    			}
    		}else if(challenger >= '0' && challenger <='9') {//挑戦者が数字は負け
    			hantei = 2;
    		}else if (challenger >='a' && challenger <= 'z'){//挑戦者が小文字は勝ち
    			hantei = 1;
    		}else if (challenger == ' ') {//挑戦者がスペース
    			if(champion == 'Z'){
    				hantei = 2;
    			}else {
    				hantei = 1;
    			}
    		}else {
    			hantei = 1;
    		}
//ここまでチャンピオンが大文字
    		
    		
//ここからチャンピオンが小文字    		
    	} else if (champion >= 'a' && champion <= 'z') { //チャンピオンが小文字だった場合
    		hantei = 0;//とりあえず例外のフラグ
    		if(challenger >= 'a' && challenger <= 'z') {//挑戦者が小文字
    			if(champion == challenger) {
    				hantei = 3;//同じ小文字なら引き分け
    			}else if (champion > challenger ) {
    				hantei = 1;
    			}else if (champion < challenger) {
    				hantei = 2;
    			} else {
    				hantei = 0;
    			}
    		}else if(challenger >= '0' && challenger <='9') {//挑戦者が数字は勝ち
    			hantei = 1;
    		}else if (challenger >='A' && challenger <= 'Z'){//挑戦者が大文字は負け
    			hantei = 2;
    		}else if (challenger == ' ') {//挑戦者がスペース
    			if(champion == 'z'){
    				hantei = 2;
    			}else {
    				hantei = 1;
    			}
    		}else {
    			hantei = 1;
    		}
//ここまでチャンピオンが小文字
    		
    		
    		
 //ここからチャンピオンが記号    		
    	} else if ((champion >= '!' && champion <= '/') || (champion >= ':' && champion <= '@') || (champion >= '[' && champion <= 96 ) || (champion >= '{' && champion <= '~')) { //チャンピオンが記号だった場合
    		hantei = 0;//とりあえず例外のフラグ
    		if((champion >= '!' && champion <= '/') || (champion >= ':' && champion <= '@') || (champion >= '[' && champion <= 96 ) || (champion >= '{' && champion <= '~')) {//挑戦者が記号
    			if(champion == challenger) {
    				hantei = 3;//同じ記号なら引き分け
    			}else if (champion > challenger ) {
    				hantei = 1;
    			}else if (champion < challenger) {
    				hantei = 2;
    			} else {
    				hantei = 0;
    			}
    		}else if (challenger == ' ') {//挑戦者がスペースなら勝ち
    			hantei = 1;

    		}else {
    			hantei = 2; //他は全て負け
    		}
//ここまでチャンピオンが記号
    		
    	}
    }
    
    public static void PrintKekka() {
    	if (hantei == 1) {
    		System.out.println("チャンピオンの勝ち");
    	} else if (hantei == 2) {
    		System.out.println("チャンピオンの負け");
    	} else if (hantei == 3) {
    		System.out.println("引き分け");
    	} else {
    		System.out.println("例外が発生しました");
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

