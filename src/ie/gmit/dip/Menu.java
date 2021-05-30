package ie.gmit.dip;

import java.util.*;

public class Menu {
	private StudentManager sm = new StudentManager();
	private Scanner s;
	private boolean keepRunning = true;
	
	public Menu() {
		s = new Scanner(System.in);
	}

	public void start() {		
		while (keepRunning) {
			showOptions();
			int choice = Integer.parseInt(s.next()); //Blocks...
			switch (choice) {
			case 1 -> add();
			case 2 -> delete();
			case 3 -> searchByID();
			case 4 -> searchByFirstName();
			case 5 -> total();
			case 6 -> save();
			case 7 -> load();
			case 8 -> {
				System.out.println("[INFO] Shutting Down...please wait...");
				keepRunning = false;
			}
			default -> System.out.println("[ERROR] Invalid Input.");
			}
		} 
	}
	
	private void save() {
		System.out.println("[INFO] Save Student Manager Database");
		System.out.println("Enter File Name>");
		String file = s.next(); //Student ID. Block and wait....		
		try {
			DatabaseUtils.save(sm, file);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void load() {
		System.out.println("[INFO] Save Student Manager Database");
		System.out.println("Enter File Name>");
		String file = s.next(); //Student ID. Block and wait.... 
	try {
		sm = DatabaseUtils.load(file);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	}
	
	public void loadFromFile(String fileName) {
		long start = System.currentTimeMillis();
		StudentFileParser sfp = new StudentFileParser();
		sfp.parse(fileName, sm);
		System.out.println("[INFO] Load time (ms): " + (System.currentTimeMillis() - start));
	}
	
	private void add() {
		System.out.println("[INFO] Add a Student");
		System.out.println("Enter Student ID>");
		String id = s.next(); //Student ID. Block and wait....
		
		System.out.println("Enter Student First Name>");
		String fname = s.next(); //
		
		System.out.println("Enter Student Surname>");
		String surname = s.next(); //
		s.nextLine();
		
		Date d = new Date();
		
		Student s1 = new Student(id, fname, surname, d);

		System.out.println("Enter Address>");
		s1.setAddress(new Address(s.nextLine()));
		
		System.out.println("Enter Course Name>");
		s1.setCourse(new Course(s.nextLine()));

		boolean added = sm.add(s1); //Add the student to the array in StudentManager 
		if (added) {
			System.out.println("[INFO] Student added okay. There are now " + sm.size() + " records in the database.");
		}else {
			System.out.println("[INFO] Could not add student to database.");
		}
	}

	private void delete() {
		System.out.println("[INFO] Delete a Student");
		
		System.out.println("Enter Student ID>");
		String id = s.next(); //Student ID. Block and wait....
		
		boolean deleted = sm.delete(id);
		if (deleted) {
			System.out.println("[INFO] Student deleted okay. There are now " + sm.size() + " records in the database.");
		}else {
			System.out.println("[INFO] No student found with that ID.");
		}
	}

	private void searchByID() {
		System.out.println("[INFO] Search for a Student by ID");
		System.out.println("Enter Student ID>");
		String id = s.next(); //Student ID. Block and wait....
		
		Student s1 = sm.getStudentById(id);
		
		if (s1 != null) {
			System.out.println(s1);
		}else {
			System.out.println("[INFO] No student found with that ID.");
		}
	}
	
	private void searchByFirstName() {
		System.out.println("[INFO] Search for Students by First Name");
		System.out.println("Enter Student First Name>");
		String name = s.next(); //Student Name. Block and wait....
		
		Student[] students = sm.getStudentsByFirstName(name);
		for (int i = 0; i < students.length; i++) {
			System.out.println(students[i]);
		}
	}
	
	private void total() {
		System.out.println("[INFO] Get Total Number of Students");
		int total = sm.size();
		System.out.println("[INFO] " + total + " students found.");
	}
	
	private void showOptions() {
		System.out.println("###################################");
		System.out.println("#        Student manager 1.0      #");
		System.out.println("###################################");
		System.out.println("(1) Add Student");
		System.out.println("(2) Delete Student");
		System.out.println("(3) Search for Student by ID");
		System.out.println("(4) Search for Students By First Name");
		System.out.println("(5) Get Total Number of Students");
		System.out.println("(6) Save Database");
		System.out.println("(7) Load Database");		
		System.out.println("(8) Quit");
		System.out.println("Select an option [1-5]>");
	}
}