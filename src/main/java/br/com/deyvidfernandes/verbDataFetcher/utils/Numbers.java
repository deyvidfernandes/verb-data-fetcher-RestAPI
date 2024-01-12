package br.com.deyvidfernandes.verbDataFetcher.utils;

public class Numbers {
    static public String correctlyDisplayDouble(Double d) {
        return d.toString().replace(",", ".");
    }

}
