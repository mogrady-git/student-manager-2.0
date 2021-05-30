package ie.gmit.dip;

public class Runner {

	public static void main(String[] args) {
		Menu m = new Menu();
//		Amortisized Time Expansion
//		100 	-> 6		v 7
//		1000 	-> 26		v 26
//		10000 	-> 356		v 95
//		100000 	->  19511	v 1665
		m.loadFromFile("./100000-students.stuff");
		m.start();
	}
}