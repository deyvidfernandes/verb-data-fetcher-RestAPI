package br.com.deyvidfernandes.verbDataFetcher.utils;

public class Numbers {
    static public String correctlyDisplayDouble(Number d) {
        return d.toString().replace(",", ".");
    }

}
