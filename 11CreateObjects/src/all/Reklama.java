package all;

import java.util.Random;

public class Reklama {
	private static String[] hvalebstveniFrazi = {"��������� � �������.", 
			"���� � ��������� �������.", "��������� ������� ���� �������.", 
			"���� � ���-������� ������� �� ���� ���������."};
	private static String[] hvalebstveniSluchki = {"���� �� �������� �����.", 
			"����� �� �� �������.", "��� ������� ����.", 
			"�� ���� �� ��������, �� ���� �� �������� ���������.", 
			"�������� � ���. �� ��� ����� �������."};
	private static String[] firstNameOfAutor = {"�����", "����", "�����", "�����", "����"};
	private static String[] secondNameOfAutor = {"�������", "�������", "������"};
	private static String[] cities = {"�����", "�������", "�����", "����", "������"};
	
	public static void main(String[] args) {
		System.out.println(hvalebstveniFrazi[(int) (Math.random()*hvalebstveniFrazi.length)]
				+ " " + hvalebstveniSluchki[(int) (Math.random()*hvalebstveniSluchki.length)] 
				+ " - " + firstNameOfAutor[(int) (Math.random()*firstNameOfAutor.length)]
				+ " " + secondNameOfAutor[(int) (Math.random()*secondNameOfAutor.length)] 
				+ ", " + cities[(int) (Math.random()*cities.length)]);
		
		Random rnd = new Random();
		System.out.println(rnd.nextInt(hvalebstveniFrazi.length)); // moje da se napravi
		// i s math random i s metoda nextInt ot clasa random
	}
}
