package villagegaulois;

import java.util.ArrayList;
import java.util.List;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche ;

	public Village(String nom, int nbVillageoisMaximum, int nbretals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbretals);
		
	}

	private static class Marche {
		private Etal[] etals;

		// Constructeur
		private Marche(int nbretals) {
			this.etals = new Etal[nbretals];
			for (int i = 0; i < nbretals; i++) {
	            this.etals[i] = new Etal();
	        }

		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			System.out.println(vendeur.getNom() + " vend des " + produit + " à l'étal n° " + indiceEtal);
			if (indiceEtal >= 0 && indiceEtal < etals.length) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			} else {
				throw new IndexOutOfBoundsException("L'indice de l'étal est hors les limites.");
			}

		}

		private int trouverEtalLibre() {
			for (int indice = 1; indice <= etals.length; indice++) {
				if (etals[indice].isEtalOccupe() == false) {
					return indice;
				}
			}
			return -1;
		}

		private Etal[] trouverEtals(String produit) {
			Etal[] etalsPossibles = new Etal[etals.length];
			int j = 0;
			for (int indice = 0; indice < etals.length; indice++) { 
				if (etals[indice].isEtalOccupe()) {
					etalsPossibles[j] = etals[indice];
					j++;
				}
			}
			
			return etalsPossibles;
		}

		
		private Etal trouverVendeur(Gaulois gaulois) {
			for ( int indice = 1 ; indice <= etals.length ; indice ++) {
				if (etals[indice].getVendeur() == gaulois) {
					return etals[indice];
				}
			}
			
			return null;
		}
		
		
		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder (); 
			int etalsLibre = 0 ; 
			for (int indice = 0; indice < etals.length; indice++) {	
				if (etals[indice].isEtalOccupe()) {
					chaine.append(etals[indice].afficherEtal()) ;  }
				else {
					etalsLibre ++ ;
				} 
			}
			if (etalsLibre > 0 ) {
				chaine.append("Il reste " + etalsLibre + " étals non utilisés dans le marché. \n" );
				}
			return chaine.toString() ;
			}
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() throws VillageSansChefException {
		StringBuilder chaine = new StringBuilder();
		if (chef == null) {
			throw new VillageSansChefException("Le village n'a pas de chef.");
		}
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit ,int nbProduit) {
		StringBuilder chaine = new StringBuilder () ;
		int indiceEtal = marche.trouverEtalLibre () ;
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " "+ produit + ". \n" ) ; 
		marche.utiliserEtal (indiceEtal , vendeur , produit , nbProduit) ;
		return chaine.toString();
	
	}

	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		int nbVendeur = 0 ; 
		List<String> vendeurs = new ArrayList<>();
		for (Etal etal : marche.trouverEtals(produit)) { 
			if (etal != null && etal.contientProduit(produit)) {
				nbVendeur ++ ; 
				vendeurs.add(etal.getVendeur().getNom());
			}
		}
		if (nbVendeur == 0) {
			chaine.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n");
		} 
		if (nbVendeur == 1) {
			chaine.append("Seul le vendeur " + vendeurs.get(0) + " propose " + produit + " dans le marché.\n" );
		}
		if (nbVendeur > 1 )  {
			 chaine.append("Les vendeurs qui proposent des " + produit + " sont : \n");
			 for (String vendeur : vendeurs) {
		            chaine.append("- " + vendeur + "\n");
		        }
		}
		
		return chaine.toString() ;
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur); 
	}
	
	public String partirVendeur(Gaulois vendeur) {
		Etal etal = rechercherEtal(vendeur) ; 
		StringBuilder chaine = new StringBuilder (); 
		chaine.append(etal.libererEtal()) ; 
		chaine.append(" qu'il voulait vendre. \n ");
		return chaine.toString();
	}
	
	public String afficherMarche() {
		System.out.println("Le marché du village \"" + this.getNom() + "\" possède plusieurs étals : \n");
		return marche.afficherMarche() ;
	}
	
	
	
}