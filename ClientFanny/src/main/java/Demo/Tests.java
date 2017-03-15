package Demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class Tests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * List<Integer>Lascii = new ArrayList(); for(int i=48 ; i<58 ; i++) {
		 * Lascii.add(i); }
		 * 
		 * for(int i=65 ; i<91 ; i++) { Lascii.add(i); }
		 * 
		 * for(int i=97 ; i<123 ; i++) { Lascii.add(i); } Random randomizer =
		 * new Random(); String random=""; for(int i=0 ; i<8 ; i++) { int x; x =
		 * Lascii.get(randomizer.nextInt(Lascii.size())); random
		 * +=Character.toString((char) ((char) x)); }
		 * System.out.println(random);
		 * 
		 * String x = ""; System.out.println("1 "+x.isEmpty()); String x1 = " ";
		 * System.out.println("1 "+x1.trim().isEmpty());
		 */
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		ValueComparator comparateur = new ValueComparator(map);
		TreeMap<Integer, Integer> mapTriee = new TreeMap<Integer, Integer>(comparateur);
		map.put(1, 10);
		map.put(2, 11);
		map.put(3, 8);
		System.out.println("map non-triée: " + map); // La commande suivante
														// affichera map
														// non-triée: {A=99.5,
														// B=67.4, C=65.2}.
		mapTriee.putAll(map);
		System.out.println("resultat du tri: " + mapTriee); // La commande
															// suivante
															// affichera
															// résultat:
															// {C=65.2, B=67.4,
															// A=99.5}.

	}

}
