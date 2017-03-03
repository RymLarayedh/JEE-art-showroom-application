package Demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Integer>Lascii = new ArrayList();
		for(int i=48 ; i<58 ; i++)
		{
			Lascii.add(i);
		}
		
		for(int i=65 ; i<91 ; i++)
		{
			Lascii.add(i);
		}
		
		for(int i=97 ; i<123 ; i++)
		{
			Lascii.add(i);
		}
		Random randomizer = new Random();
		String random="";
		for(int i=0 ; i<8 ; i++)
		{
			int x;
			x = Lascii.get(randomizer.nextInt(Lascii.size()));		
			random +=Character.toString((char) ((char) x));
		}
		System.out.println(random);
	}

}
