package es.cervecitas.food.foodcomposition.app;

public class Utils {

    public static String capitalizeFirstLetter(String str) {
        return String.valueOf(str.charAt(0)).toUpperCase().concat(str.substring(1, str.length()));
    }
}
