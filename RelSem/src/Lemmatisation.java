

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Lemmatisation {
	
	static HashMap<String, ArrayList<String>> map;
	
	
	public Test () throws IOException {
		String filePath = "dico.txt";
	    map = new HashMap<String, ArrayList<String>>();
	    String line;
	    BufferedReader reader = new BufferedReader(new FileReader(filePath));
	    
	    while ((line = reader.readLine()) != null)
	    {
	        String[] parts = line.split("	");
	        if (parts.length >= 2)
	        {
	            String key = parts[0];
	            String value = parts[1];
	            
	            String deux = parts[1]+"	"+parts[2];
	            
	            if (map.keySet().contains(parts[0])) {
	            	map.get(parts[0]).add(deux);
	            }else {
	            	ArrayList<String> values = new ArrayList<String>();
	            	values.add(deux);
	            	map.put(key, values);
	            }
	            	
	        } else {
	            System.out.println(line);
	        }
	    }
	}
	//isPluriel isMasculin isFeminin isNom isVerb isAdj isAmbigu (plus d'une correspondance)
	//getLemme("bois") ==> boire
	//getPos("bois") ==> verb ou nom ou adj pro det 
	//getLemme("tapis","Nom") ==> tapis         getLemme("tapis","Ver") ==> tapir
	// penser à déclencher des exceptions dans les cas ambigus 
	// pour spliter sur plusieurs caractères split(":|+")   "Nom:Mas+SG"  ==>   [0]="Nom" [1]="Mas" [2]="SG"
	

	public boolean isPluriel(String mot) throws Exception{
		if (!map.keySet().contains(mot)) throw new Exception ("Le mot n'existe pas");
		else {
			for (String str : map.get(mot)){
				String[] tab = str.split("	");
				if (tab[1].contains("PL")) return true;
			}
		}
        return false;
	}
	
	
	public static void main(String[] args) throws Exception{
	    
	    Lemmatisation lm = new Lemmatisation();
	    
	    
	    
    	System.out.print("Veuillez saisir  : \n");    


    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String s = br.readLine();
    	/*
    	    String[] words = s.split(" ");

    	    

    	
	    	
    	    for(int i=0;i<words.length;i++){

    		    for (String key : lm.map.keySet()){
    	    
    	    
	        if(key.equals(words[i]))
	        {
	        	for (String str : lm.map.get(key))
	        	System.out.println(words[i]+" "+str+" ");
	        }


	    }
    }*/
	   // reader.close();
    	System.out.println(lm.isPluriel(s));
	}

}
