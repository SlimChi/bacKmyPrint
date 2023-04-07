package fr.rt.MyPrintRed.dto;

import java.util.HashMap;
import java.util.Map;

public class HateOas {

    public Map<String, String> links;

    public HateOas(){
        links = new HashMap<>();
    }


    public void addLink(String key,String link){

        links.put(key,link);
    }
}
