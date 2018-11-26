package parser;

import bibtex.BibBase;
import bibtex.CategoryType;
import bibtex.Record;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Parser {

    private ParserState state;

    public BibBase parse(String bibfilePath) {
        BibBase bibBase = new BibBase();
        try {
            //Create word array
            Scanner bibtexfile = new Scanner(new File(bibfilePath));
            ArrayList<String> words = new ArrayList<String>();
            Record record = new Record(CategoryType.MISC);
            while(bibtexfile.hasNext()) {
                String word = bibtexfile.next();
                word = word.replace ("@", " @ ");
                word = word.replace ("{", " { ");
                word = word.replace ("}", " } ");
                word = word.replace ("\"", "");
                word = word.replace ("\\", "");
                word = word.replace ("~", "");
                word = word.replace (",", "");
                String[] splitedWord = word.split(" ");
                for (String w : splitedWord) {
                    words.add(w);
                }
            }
            //Print words
            for (String w : words) {
                System.out.println(w);
            }
            //Parse words
            ListIterator<String> itr = words.listIterator();
            this.state = ParserState.WAITING_FOR_RECORD;
            while (itr.hasNext()) {
                String actWord = itr.next();
                switch (this.state) {
                    case WAITING_FOR_RECORD: {
                        if (actWord.equals("@")) {
                            String nextWord = itr.next().toLowerCase();
                            switch (nextWord) {
                                case "preamble": {
                                    break;
                                }
                            }
                            this.state = ParserState.PARSING_RECORD;
                        }
                        break;
                    }
                    case PARSING_RECORD: {
                        break;
                    }
                }
            }
            bibtexfile.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return bibBase;
    }

}
