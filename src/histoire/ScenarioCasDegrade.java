package histoire;
import villagegaulois.Etal;
import personnages.Gaulois;


public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal();
	
		
		Gaulois acheteur1 = null ; 
		etal.acheterProduit(5, acheteur1); 
		Gaulois acheteur2 = new Gaulois ("Obélix", 25 ) ; 
		Gaulois vendeur = new Gaulois ("Test", 25 ) ; 
		etal.occuperEtal(vendeur, "fleurs", 20) ;
		etal.acheterProduit(-5, acheteur2); 
		
		etal.occuperEtal (acheteur2 , "pomme", 30);
		System.out.println("Fin du test");
		
		}
	
		
		
		
		

}
