
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Partie {
	private List<Niveau> niveauDunePartie=new ArrayList<Niveau>();
	
    public Partie(List<Niveau> niveauDunePartie) {
		super(); 
		this.niveauDunePartie = niveauDunePartie;
	}
    public Partie(String fileName) {
    	this.niveauDunePartie=readPuzzles(fileName);
    }
    
	public List<Niveau> getNiveauDunePartie() {
		return niveauDunePartie;
	}
	public void setNiveauDunePartie(List<Niveau> niveauDunePartie) {
		this.niveauDunePartie = niveauDunePartie;
	}
	
	public void afficher_les_niveaux() {
		for ( int i=0;i<niveauDunePartie.size();i++) {
			//niveauDunePartie.get(i)
			for (Niveau n : niveauDunePartie ) {
				n.afficherNiveau();
				
			}
		}
	}
	//recherche dans un hashmap selon la lettre et renvoie la position
	static Position rechPos( Character c,HashMap<Character,Position> map) {
		for(Map.Entry<Character, Position> entry : map.entrySet()) {
			if(entry.getKey().equals(c)) {
				return entry.getValue(); 
			}
		}
		return null;
	}
	
	public static List<Niveau> readPuzzles(String fileName) {
		HashMap<Character,Position> positionWorlds = new HashMap<Character,Position>();
        List<Niveau> puzzles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("P")) {//si la ligne commence par un P , donc une sous partie
                    String[] puzzleHeader = line.split(" ");
                    //int size = Integer.parseInt(puzzleHeader[1]);
                   // char numPartie = puzzleHeader[0].charAt(1);
                    char c = puzzleHeader[1].charAt(0);// le size en caractère
                    int size = Character.getNumericValue(c);
                    char[][] data = new char[size][size];//jusqu'ici cv 
                    for (int i = 0; i < size; i++) {
                    	line = reader.readLine();
                        for (int j = 0; j < size; j++) {
                            data[i][j] = line.charAt(j); 
                            if(line.charAt(j)!='#' && line.charAt(j)!='a' && line.charAt(j)!='A' && line.charAt(j)!='b' && line.charAt(j)!='B' && line.charAt(j)!=' '&& line.charAt(j)!='@') {
                            	//on doit preserver la position
                            	// sik c'est un monde 
                            	positionWorlds.put(data[i][j], new Position(i,j));//bien ça marche 
                            }
                        }
                    }//on a jusqu'a cette ligne la premiere partie
                    puzzles.add(new Niveau(puzzleHeader[0], size, data)); //on construit les parties
                	char cc = puzzleHeader[0].charAt(1);
                    int num = Character.getNumericValue(cc);
                    puzzles.get(num-1).setBoard(puzzles.get(num-1).const_brd(data, size));//on construit le board

               } else if(line != null && line.length()>0  && !line.startsWith("P"))  {
                	//cas monde//
            	    char letter = line.charAt(0);
                    Monde monde=new Monde(letter);
                    int size = Character.getNumericValue(line.charAt(2));//Integer.parseInt(line.substring(2));
                    char[][] data = new char[size][size];
                    for (int i = 0; i < size; i++) {
                        line = reader.readLine();
                        if (line.length() < size) {
                    	    // la ligne ne contient pas suffisamment de caractères
                    	    continue;
                    	}
                        for (int j = 0; j < size; j++) {
                 
                            data[i][j] = line.charAt(j);
                        } 
                    }
                    monde.setMatrice(monde.const_brd(data, size));
                    Position pos=rechPos(letter,positionWorlds);
                    //on doit récuperer la matrice du sous monde
                    puzzles.get(puzzles.size() - 1).getLesMondes().put(pos,monde);
                    puzzles.get(puzzles.size() - 1).getBoxes().addAll(monde.getListeBoites());
                    puzzles.get(puzzles.size() - 1).getCibles().addAll(monde.getListeCibles());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return puzzles;
    }
}