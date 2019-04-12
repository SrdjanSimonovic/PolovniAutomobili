package validacija;

public class Validacija {

	public static boolean daLiJeBroj(String nizKaraktera) {
		
		boolean odgovor = true;
		
		for(int i=0;i<nizKaraktera.length();i++) {
			
			char karakter = nizKaraktera.charAt(i);
			
			if(!(Character.isDigit(karakter))) {
				odgovor=false;
				break;
			}
			
		}
		return odgovor;
	}
	
}
