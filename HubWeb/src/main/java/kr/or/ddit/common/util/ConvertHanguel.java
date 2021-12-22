package kr.or.ddit.common.util;

public class ConvertHanguel {
	public static String convert(String amt) {
		
		String amt_msg = "";
        String[] arrayNum = {"", "일","이","삼","사","오","육","칠","팔","구"};
        String[] arrayUnit = {"","십","백","천","만","십만","백만","천만","억","십억","백업","천억","조","십조","백조","천조","경","십경","백경","천경","해","십해","백해","천해"};
   
        if(amt.length() > 0){
            int len = amt.length();
            
            String[] arrayStr = new String[len];
            String hanStr = "";
            String tmpUnit = "";
            for(int i = 0; i < len; i++){
                arrayStr[i] = amt.substring(i, i+1);
            }
            int code = len;
            for(int i = 0; i < len; i++){
                code--;
                tmpUnit = "";
                if(arrayNum[Integer.parseInt(arrayStr[i])] != ""){
                    tmpUnit = arrayUnit[code];
                    if(code > 4){
                        if((Math.floor(code/4) == Math.floor((code-1)/4) 
                        && arrayNum[Integer.parseInt(arrayStr[i+1])] != "") ||
                        (Math.floor(code/4) == Math.floor((code-2)/4)
                        && arrayNum[Integer.parseInt(arrayStr[i+2])] != "")) {
                            tmpUnit = arrayUnit[code].substring(0,1);
                        }
                    }
                }
                hanStr += arrayNum[Integer.parseInt(arrayStr[i])]+tmpUnit;
            }
            amt_msg = hanStr;
        }else{
            amt_msg = amt;
        }
     
        return amt_msg;
	}
	
}
