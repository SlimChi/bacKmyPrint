package fr.rt.MyPrintRed.exceptions;

public class TypeOptionNotFoundException extends RuntimeException{

    public TypeOptionNotFoundException(Integer idOption,Integer idTypeOption){super("Could not find TypeOption with idOption : "+idOption
            +" and idTypeOption :"+idTypeOption);}
}
