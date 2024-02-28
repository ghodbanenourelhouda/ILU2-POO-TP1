package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	private static class Marche {
		private Etal[] etals;

		// Constructeur
		private Marche(int nbretals) {
			this.etals = new Etal[nbretals];

		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			System.out.println(vendeur.getNom() + " a occupé l'etal numéro " + indiceEtal);
			if (indiceEtal >= 0 && indiceEtal < etals.length) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			} else {
				// une exception
			}

		}

		private int trouverEtalLibre() {
			for (int indice = 0; indice < etals.length; indice++) {
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
			for ( int indice = 0 ; indice < etals.length ; indice ++) {
				if (etals[indice].getVendeur() == gaulois) {
					return etals[indice];
				}
			}
			
			return null;
			
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

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
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
}