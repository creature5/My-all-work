package all;

public class Problem5Animal {
	private int age;
	private String name;
	private Problem5Gender animalGender;
	private String sound;
	
	public Problem5Animal()	{
		this(0, "", Problem5Gender.MALE);
	}
	
	
	public Problem5Animal(int age, String name, Problem5Gender animalGender) {
		this.age = age;
		this.name = name;
		this.animalGender = animalGender;
	}

	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
	public Problem5Gender getAnimalGender() {
		return animalGender;
	}


	public void setAnimalGender(Problem5Gender animalGender) {
		this.animalGender = animalGender;
	}


	public String getSound() {
		return sound;
	}


	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getAnimalSound() {
		return this.sound;
	}

	@Override
	public String toString() {
		return "name = " + name + ", age = " + age +  ", sound = "
				+ getAnimalSound();
	}

}
