package app;

import bibtex.BibBase;
import parser.Parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class App {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage:");
            System.out.println("main <BibTeX file> , <Author1> | <Author2> | ... , <Category1> | <Category2> | ...");
            System.out.println("\n<BibTeX file> - file with BibTeX bibliography");
            System.out.println("<Author> - Author's name to search for");
            System.out.println("<Category> - Category to search for");
            System.out.println("\nExample:");
            System.out.println("main bibtexExample1.bib , , PROCEEDINGS | INPROCEEDINGS | MISC");
        } else {
            Parser parser = new Parser();
            BibBase bibBase = parser.parseFile(args[0]);
            //Parse input
            StringBuilder input = new StringBuilder();
            for (String arg : args) {
                input.append(arg);
                input.append(" ");
            }
            String[] groups = input.toString().trim().split(",");
            Set<String> authorSet = new HashSet<>();
            if (groups.length > 1) {
                //Create author set
                for (String s : groups[1].trim().split("\\|")) {
                    if (!s.equals("")) authorSet.add(s);
                }
            }
            Set<String> categorySet = new HashSet<>();
            if (groups.length > 2) {
                //Create category set
                for (String s : groups[2].trim().split("\\|")) {
                    if (!s.equals("")) categorySet.add(s);
                }
            }
            System.out.println(bibBase.searchForCategories(categorySet).searchForAuthors(authorSet));
        }
    }

}
