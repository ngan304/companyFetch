package webdriver;

public class Topic_07_Variable {
	////a
	String address;
	static String cityName="Nguyen Kim Ngan";
	
	public static void main(String[] args) {
		Topic_07_Variable tp7_01= new Topic_07_Variable();//tp_01 đại diện cho Topic_07_Variable
		tp7_01.address= "Ho Chi MInh";
		System.out.println(tp7_01.address);
		
		Topic_07_Variable tp7_02= new Topic_07_Variable();
		tp7_02.address="Ha Noi";
		System.out.println(tp7_02.address);
	}

}
