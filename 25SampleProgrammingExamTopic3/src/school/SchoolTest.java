package school;

public class SchoolTest {
	public static void addObjectsToSchool(School school) {
		Teacher teacherGeorgiev = new Teacher("Димитър", "Георгиев");
		Teacher teacherNikolova = new Teacher("Христина", "Николова");
		school.addTeacher(teacherGeorgiev);
		school.addTeacher(teacherNikolova);
		// Add the English group
		Group groupEnglish = new Group("английски език");
		groupEnglish.addStudent(new Student("Иван", "Петров"));
		groupEnglish.addStudent(new Student("Васил", "Тодоров"));
		groupEnglish.addStudent(new Student("Елена", "Михайлова"));
		groupEnglish.addStudent(new Student("Радослав", "Георгиев"));
		groupEnglish.addStudent(new Student("Милена", "Стефанова"));
		groupEnglish.addStudent(new Student("Иван", "Петров"));
		school.addGroup(groupEnglish);
		teacherNikolova.addGroup(groupEnglish);
		// Add the French group
		Group groupFrench = new Group("френски език");
		groupFrench.addStudent(new Student("Петър", "Петров"));
		groupFrench.addStudent(new Student("Васил", "Василев"));
		school.addGroup(groupFrench);
		teacherNikolova.addGroup(groupFrench);
		// Add the Informatics group
		Group groupInformatics = new Group("информатика");
		groupInformatics.addStudent(new Student("Милка", "Колева"));
		groupInformatics.addStudent(new Student("Пенчо", "Тошев"));
		groupInformatics.addStudent(new Student("Ива", "Борисова"));
		groupInformatics.addStudent(new Student("Милена", "Иванова"));
		groupInformatics.addStudent(new Student("Христо", "Тодоров"));
		school.addGroup(groupInformatics);
		teacherGeorgiev.addGroup(groupInformatics);
	}

	public static void main(String[] args) {
		School school = new School("Свобода");
		addObjectsToSchool(school);
		for (Teacher teacher : school.getTeachers()) {
			teacher.printGroups(System.out);
			System.out.println();
		}
	}
}
