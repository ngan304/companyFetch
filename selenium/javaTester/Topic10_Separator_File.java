package javaTester;

import java.io.File;

public class Topic10_Separator_File {
	public static void main(String[] args) {
	
		String projectPath=System.getProperty("user.dir");
		
		String dellName="Dell.jpg";
		
		
		String dellFilePath=projectPath + File.separator +"uploadFiles" + File.separator + dellName;
		System.out.println(dellFilePath);
	
	}

}
