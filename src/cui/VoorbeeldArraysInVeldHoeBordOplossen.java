package cui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VoorbeeldArraysInVeldHoeBordOplossen {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		List<Integer> lijst = Arrays.asList(new Integer[10]);
		
		
		List<Integer> listArray = new ArrayList<>(lijst);
		
		
		System.out.println("Wil je een steen vervangen?");
		int antwoord = input.nextInt();
		
		while (antwoord != 0) {
			try{
		
			System.out.println("Welke index?");
			int index = input.nextInt();
			
			System.out.println("Welke getal?");
			int getal = input.nextInt();
			
			veranderLijst(listArray, index, getal);
			
			System.out.println("1 2 3 4 5 6 7 8 9 10");
			listArray.forEach(g -> System.out.print(g == null? "  " : g +" "));
			System.out.println();
			
			System.out.println("Wil je een steen vervangen?");
			antwoord = input.nextInt();
			
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
				input.next();
			}
		
		};
		
		System.out.println();
		System.out.println("Kies een index om te verwijderen:");
		int index = input.nextInt();
		
		listArray.toString();
		System.out.println();
		
		listArray.set(index, null);
		System.out.println("1 2 3 4 5 6 7 8 9 10");
		listArray.forEach(g -> System.out.print(g == null? "  " : g +" "));
		System.out.println();
		
		listArray.toString();
		
		
		
	}
	
	public static void veranderLijst (List<Integer> lijst, int index, int getal) {
		if (lijst.get(index) == null) {
			lijst.set(index, getal);
		} else {
			throw new IllegalArgumentException("Nah cunt");
		}
	}
}
