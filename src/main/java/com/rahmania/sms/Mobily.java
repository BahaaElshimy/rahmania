/**********************************************************************************************/
/**********************************************************************************************/
/************                               OperationNumber                        ************/
/************                               ---------------                        ************/
/************         1.Send SMS                                                   ************/
/************		  2.Send Status	                   							   ************/
/************ 		  3.ChangePassword											   ************/
/************		  4.ForgetPassword											   ************/
/************ 		  5.Balance                                                    ************/
/************ 		  6.Active Sender                                              ************/
/************ 		  7.Check Sender                                               ************/
/************ 		  8.Add Sender                                                 ************/
/**********************************************************************************************/
/**********************************************************************************************/

package com.rahmania.sms;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
@Component
@Profile("!dev")
public class Mobily implements SmsService {
	private String msg="";
	private String balance="";
	static char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

//Send SMS Method
//userName : ��� �������� �� �������
//password : ��������  �� �������

//sender : ��� ������ ���� ����� ��� ����� ������� ���� ������ ���  ���� ������ ��� ��� ������� (urlencode)

//message : 							SendSMS ����� ������� ����� ��������� ��� ����� ������� ���� ������� 
/*										������� �������  70 ���
										������� ���������� 160 ���
										�� ��� ����� ���� �� ����� ����� ��� ������� ������� ���� 67
										�������� ��������� 158
*/

//numbers : 							��� ����� ����� ������� ������� ��� 96650555555 ���� ������� ��� ���� �� ��� ��� ��� ������� (,) ��� ���� ��� ��� ����� ��� �� ����� 
/*										�� ���� ��� ���� �� ������� ���� ����� ������� ��� �� ��� �� ������� �� ���� ����� fsockpoen  �� ����� CURL�
										���� �� ��� �� ������� �� ���� ����� fOpen � ���� ����� ������� ��� 120 ��� ��� �� �� ����� �����
*/
// ������� ���  ����� ������� � ���� ������ ��� ��� ������ ������� �� �������
	@Override
	public void sendMessage(String userName,String password,String sender,String message,String numbers) throws UnsupportedEncodingException{
        String message1 = new String(message.getBytes("cp1256"), "cp1256");
        System.out.println(message1);
		    String para ="mobile=" + userName + "&password=" + password + "&numbers=" + numbers+ "&sender=" + sender + "&msg=" + convertUnicode(message1) + "&applicationType=64";
	        sendURL("http://www.mobily.ws/api/msgSend.php",para,1);
	        System.out.println(getMessage());
	}


//Check send Status method
// ������� ���  ����� ������� � ���� ������ ��� ��� ������ ������� �� �������
	public void sendStatus(){
		sendURL("http://www.mobily.ws/api/sendStatus.php","",2);
	}

//Change Password method
//userName : ��� �������� ������ �� ���� �������
//password : ���� ������ ������� ������ �� ���� �������
//newPassword : ���� ������ ������ ������ �� ���� �������
// ������� ���  ����� ������� � ���� ������ ��� ��� ������ ������� �� �������
	public void changePassword(String userName,String password,String newPassword){
		String para="mobile="+userName+"&password="+password+"&newPassword="+newPassword;
		sendURL("http://www.mobily.ws/api/changePassword.php",para,3);
	}


//Forget Password Method 
//userName : ��� �������� ������ �� ���� �������
//type : ���� ����� ���� ����� ���� ������
//1: ����� ���� ������ ��� ������
//2: ����� ���� ������ ��� ������� ���� ���� ������� ������ ������ ��� ��������� ������� �� ������
// ������� ���  ����� ������� � ���� ������ ��� ��� ������ ������� �� �������
	public void forgetPasswrd(String userName,String type){
		String para="mobile="+userName+"&type="+type;
		sendURL("http://www.mobily.ws/api/forgetPassword.php",para,4);
	}


//check balance method
//userName : ��� �������� ������ �� ���� �������
//password : ���� ������ ������ �� ���� �������
// ������� ���  ����� ������� � ���� ������ ��� ��� ������ ������� �� �������
	public String checkBalance(String userName,String password){
		String para="mobile="+userName+"&password="+password;
		sendURL("http://www.mobily.ws/api/balance.php",para,5);
		return balance;
	}


//Active Sender Method
//userName : ��� �������� �� �������
//Password : ��������  �� �������
//senderID : ������ ������� ������� �� ����� ��� ����� ��� ������ ���� ���� ����� ����� (#)� ������ ��� ����� #110 ��� ������ ��� ����� 110
//activeKey : ��� ������� ���� �� ������� ��� ������
// ������� ���  ����� ������� � ���� ������ ��� ��� ������ ������� �� �������
	public void ActiveSender(String userName,String Password,String senderID,String activeKey){
		String para="mobile="+userName+"&password="+Password+"&senderId="+senderID+"&activeKey="+activeKey;
		sendURL("http://www.mobily.ws/api/activeSender.php",para,6);
	}

//Check Sender Method
//userName : ��� �������� �� �������
//password : ��������  �� �������
//senderID : ������ ������� ������� �� ����� ��� ����� ��� ������ ���� ���� ����� ����� (#)� ������ ��� ����� #110 ��� ������ ��� ����� 110
// ������� ���  ����� ������� � ���� ������ ��� ��� ������ ������� �� �������
	public void checkSender(String userName,String password,String senderID){
		String para="mobile="+userName+"&password="+password+"&senderId="+senderID;
		sendURL("http://www.mobily.ws/api/checkSender.php",para,7);
	}
	public String getMessage(){
		return msg;
	}
	//**********************************************************************************************//
	//*********************************							************************************//
	//*********************************conver to unicode Methods************************************//
	//********************************* 						************************************//
	//**********************************************************************************************//
	
	public static String convertUnicode(String str) {
        char[] chars = str.toCharArray();
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            strBuffer.append(forDigits( Integer.toHexString((int) chars[i])));
        }
        return strBuffer.toString();
    }
        public static String bytesToHex(byte[] b, int off, int len) {
		StringBuffer buf = new StringBuffer();
		for (int j=0; j<len; j++)
			buf.append(byteToHex(b[off+j]));
			return buf.toString();
	}
	public static String byteToHex(byte b) {
		char[] a = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
		return forDigits(new String(a));
	}
	public static String forDigits(String val){
		switch (val.length() ){
			case 1:return "000"+val;
			case 2:return "00"+val;
			case 3:return "0"+val;
			case 4:return ""+val;
			default:return val;
		}
	}
	//**********************************************************************************************//
	//**********************************************************************************************//
	//**********************************************************************************************//
	public void selectedMessage(int value,int operationNumber){
		switch(operationNumber){
			case 1:switch(value){
						case 1:msg= "��� ������� �����";break;
						case 2:msg="�� ����� ��� ������� �� ����� ��� ��� �� �� �����. (��� ������� �� ���� ����� �� ������� ��� �������. ���� ����� ���� ������� ��� ������";break;
						case 3:msg="�� ����� ������ �� ���� ������ ����� �������. (��� ������� �� ���� ����� �� ������� ��� �������. ���� ����� ���� ������� ��� ������";break;
						case 4:msg="�� ��� �������� ���� �������� ������ ��� ���� ������� ��� ���� (���� �� �� ��� �������� ���� �������� �� ���� ���� ������� ��� ����� ��� ���� �������).";break;
						case 5:msg="���� ��� �� ���� ������ (���� �� �� ���� ������ ���� �� ��������� �� ����� ���� �������� ��� ����� ���� �������,��� ���� ���� ������ ���� ��� ���� ���� ���� ������ ����� ����� ��� ����� ���� ������ ����� ��";break;
						case 6:msg="�� ���� ������� ������ �� ����� ������ (�� ���� ���� ��� ���� ��� ������ �� ���� ���� ������ ��� ���� ��� ���� �� ����� �� ����� ����� ��� ����� �����";break;
						case 12:msg="�� ����� ����� ��� ����� ���� ������ ����� �����";break;
						case 13:msg="�� ��� ������ ���� �������� �� ��� ������� �� ��� �����. (���� ����� ������� ���� ���� ��� �� ����� ��� ������ ��� �������";break;
						case 14:msg="�� ��� ������ ���� �������� ��� ���� ��� �������. (����� ����� ��� ������ �� ���� ���� ����� ��� ����";break;
						case 15:msg="���� ��� ���� ���� �� ������� ���� ��� �������� ���. (���� �� ��� ������� ���� ���� ������� ��� ����� ������� �������";break;
						case 16:msg="������� ���� ��� �������� �� ����� ��� ��� ����. (���� ��� ���� ��� ������ �������";break;
						case 17:msg="� ��� ����� �� �������. ������ ������ �� ����� �� ������� ������� �� ����� ������� ��� ���� ��� (������ ������ �� ������� ������";break;
						case -1:msg="�� ��� ������� �� ���� (Server) ������� ������� �����. (�� ���� ���� ������� ����� ����� ��� ��� , �� �� ���� ���� ��� ���� ��� ��� ������ ��� ������ ������� ���� ������� �� ����� �����)";break;
						case -2:msg="�� ��� ����� �� ����� �������� (Database) ���� ����� ��� ����� �������� ��� �������. (�� ���� ���� ������� ����� ����� ��� ��� , �� �� ���� ���� ��� ���� ��� ��� ������ ��� ������ ������� ���� ������� �� ����� �����)";break;
						default:msg="";break;
				   }break;
			case 2:switch(value){
						case 1:msg= "����� ������� ����.";break;
						default:msg="������� ����� ����.";break;
				   }break;
			case 3:switch(value){
						case 1:msg= "��� ������ ��� �����.";break;
						case 2:msg="���� ������ ������� �����.";break;
						case 3:msg="����� ����� ���� ������ ��� �����.";break;
						default:msg="";break;
				   }break;
			case 4:switch(value){
						case 1:msg= "��� ������ ��� �����..";break;
						case 2:msg="������� ����� ������� ��� �����.";break;
						case 3:msg="�� ����� ���� ������ ��� ������ �����.";break;
						case 4:msg="����� ��� ���� ������ ����� �������.";break;
						case 5:msg="�� ����� ���� ������ ��� ������� �����.";break;
						case 6:msg="������� ����� ������� ��� ����.";break;
						case 7:msg="��� ������ �������� ��� ����.";break;
						default:msg="";break;
				   }break;
			case 5:switch(value){
						case 1:msg= "��� ������ ��� �����.";break;
						case 2:msg= "���� ������ �����.";break;
						default:msg=balance;break;
				   }break;
			
			case 7:switch(value){
						case 0:msg= "��� ������ ��� ����.";break;
						case 1:msg= "��� ������ ����.";break;
						case 2:msg="��� ������ ����� (�� ����� ��� ������� ���� ������ 3 ���� ���)";break;
						case 3:msg="��� ������ ��� �����.";break;
						case 4:msg="���� ������ �����.";break;
						default:msg="";break;
				   }break;
			}
	}
	public void sendURL(String URL,String parameters,int operationNumber){
		try {
	        URL url;
	        URLConnection urlConnection;
	        DataOutputStream outStream;
	        // Create connection
	        url = new URL(URL);
	        urlConnection = url.openConnection();
	        ((HttpURLConnection)urlConnection).setRequestMethod("POST");
	        urlConnection.setDoInput(true);
	        urlConnection.setDoOutput(true);
	        urlConnection.setUseCaches(false);
	        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        urlConnection.setRequestProperty("Content-Length", ""+ parameters.length());
	        urlConnection.setRequestProperty("User-agent","Mozilla/4.0");
	        // Create I/O streams
	        outStream = new DataOutputStream(urlConnection.getOutputStream());
	        // Send request
	        outStream.writeBytes(parameters);
	        outStream.flush();
	        outStream.close();
	        // Get Response
	        BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	        // - For debugging purposes only!
	        String buffer;
	        while((buffer = rd.readLine()) != null) {
	        	try{
	        		selectedMessage(Integer.parseInt(buffer),operationNumber);
	        	}catch(Exception ex){
	        		balance=buffer;
	        	}
	        }
	        // Close I/O streams
	        rd.close();
	        outStream.close();
	    }
	    catch(Exception ex) {
	        System.out.println("Exception cought:\n"+ ex.toString());
	    }
	}
	
	
}