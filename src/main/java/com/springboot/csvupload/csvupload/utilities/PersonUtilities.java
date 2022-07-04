package com.springboot.csvupload.csvupload.utilities;

public class PersonUtilities {

    public static int getZipcode(String address){
        if (address.isBlank())
            return 0;
        else
            return Integer.parseInt(address.replaceAll("[^0-9]", ""));
    }

    public static String getCity(String address){
        if (address.isBlank())
            return "";
        else
            return address.replaceAll("[^a-zA-Z\\s]", "").trim();
    }

    public static String getColor(int id){
        switch (id) {
            case 1: return "blau";
            case 2: return "grün";
            case 3: return "violett";
            case 4: return "rot";
            case 5: return "gelb";
            case 6: return "türkis";
            case 7: return "weiß";
            default: return "";
        }
    }

}
