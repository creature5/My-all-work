package all;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class StudentAndCursesExample {
	public static void main(String[] args) throws FileNotFoundException {
		// Read the file and build the hash-table of courses
		HashMap<String, ArrayList<Student>> courses = new HashMap<String, ArrayList<Student>>();
		Scanner input = new Scanner(new File("Students.txt"), "windows-1251");
		try {
			while (input.hasNext()) {
				String line = input.nextLine();
				String[] studentEntry = line.split("\\s*\\|\\s*");
				String firstName = studentEntry[0];
				String lastName = studentEntry[1];
				String course = studentEntry[2];
				ArrayList<Student> students = courses.get(course);
				if (students == null) {
					// New course -> create a list of students for it
					students = new ArrayList<Student>();
					courses.put(course, students);
				}
				Student student = new Student(firstName, lastName);
				students.add(student);
			}
		} finally {
			input.close();
		}
		
		// Print the courses and their students
		Set<String> coursesNames = courses.keySet();
		for (String course : coursesNames) {
			System.out.println("Course " + course + ":");
			ArrayList<Student> students = courses.get(course);
			Student[] studentsArr =	students.toArray(new Student[students.size()]);
			Arrays.sort(studentsArr);
			for (Student student : studentsArr) {
				System.out.printf("\t%s\n", student);
			}
		}
	}		
}
