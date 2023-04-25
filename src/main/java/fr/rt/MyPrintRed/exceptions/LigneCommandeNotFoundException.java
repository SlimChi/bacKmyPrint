package fr.rt.MyPrintRed.exceptions;

public class LigneCommandeNotFoundException extends RuntimeException{

    public LigneCommandeNotFoundException(Integer numeroCommande,Integer numeroLigneCommande){super("Could not find LigneCommande with numeroCommande : "+numeroCommande
                                            +" and numeroLigneCommande : "+numeroLigneCommande);}
}
