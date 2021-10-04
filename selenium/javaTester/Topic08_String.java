package javaTester;

import org.openqa.selenium.By;

public class Topic08_String {
	public static void main(String[] args) {
		String actualText ="                 Second Option             ";
		System.out.println(actualText);
		System.out.println(actualText.trim());
		
		String user ="admin";
		String pass="admin";
		String href = "http://the-internet.herokuapp.com/basic_auth";
		
		String [] hrefValue=href.split("//");  //Giá trị này sẽ trả về mảng strint
		href= hrefValue[0] + "//" + user +":" + pass +"@" + hrefValue[1];
		System.out.println(href);
		
		
	}
	
   

}
