package br.com.deyvidfernandes.verbDataFetcher.ngram;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("api/ngram")
public class NgramGetterController {

    @GetMapping("")
    public ResponseEntity<Object> getNgram(@RequestParam("ngram") String ngram) throws IOException {
        var ngramUrl = "https://books.google.com/ngrams/json?content=" + ngram + "&year_start=2005&year_end=2019&corpus=en-2019&smoothing=0";
        var url = new URL(ngramUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(content);
    }
}
