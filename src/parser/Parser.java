package parser;

import bibtex.BibBase;
import bibtex.CategoryType;
import bibtex.Field;
import bibtex.Record;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Parser {

    private static final MandatoryNOptionalChecker checker = new MandatoryNOptionalChecker();

    public BibBase parse(String bibfilePath) {
        final ArrayList<String> words = this.loadTextFile(bibfilePath);
        BibBase bibBase = new BibBase();
        //Parse words
        int bracketsCount = 0;
        ListIterator<String> itr = words.listIterator();
        while (itr.hasNext()) {
            String actWord = itr.next();
            if (actWord.equals("@")) {
                String nextWord = itr.next().toLowerCase();
                switch (nextWord) {
                    case "preamble": {
                        break;
                    }
                    case "string": {
                        break;
                    }
                    default: {
                        Record record = this.parseRecord(nextWord, itr, words);
                        if (this.isRecordCorrect(record)) bibBase.addRecord(record);
                        break;
                    }
                }
            }
        }

        return bibBase;
    }

    private ArrayList<String> loadTextFile(String filePath) {
        ArrayList<String> words = new ArrayList<String>();
        try {
            //Create word array
            Scanner textfile = new Scanner(new File(filePath));
            while (textfile.hasNext()) {
                String word = textfile.next();
                word = word.replace("@", " @ ");
                word = word.replace("{", " { ");
                word = word.replace("}", " } ");
                word = word.replace("\n", "");
//                word = word.replace("[", "");
//                word = word.replace("]", "");
//                word = word.replace("\"", " \" ");
//                word = word.replace("\\", " \\");
                word = word.replace(",", " , ");
                String[] splitedWord = word.split(" ");
                for (String w : splitedWord) {
                    if (!w.equals("") && !w.equals("\n")) {
                        words.add(w);
                    }
                }
            }
            textfile.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        //Print words
//        for (String w : words) {
//            System.out.println(w);
//        }
        return words;
    }

    private Record parseRecord(String category, ListIterator<String> itr, final ArrayList<String> words) {
        int bracketsCount = 0;
        Record record = new Record(CategoryType.valueOf(category.toUpperCase()));
        do {
            if (!itr.hasNext()) {
                throw new ParseException("Unexpected end of file.");
            }
            String actWord = itr.next();
            switch (actWord) {
                case "{": {
                    bracketsCount++;
                    break;
                }
                case "}": {
                    bracketsCount--;
                    if (bracketsCount == 0) {
                        return record;
                    }
                    break;
                }
                default: {
                    String nextWord = itr.next();
                    if (nextWord.equals("=")) {
                        nextWord = this.parseValue(itr, words);
                        record.addField(new Field(actWord, nextWord));
                    }
                    itr.previous();
                }
            }
        } while (bracketsCount > 0);
        throw new ParseException("Unexpected end of record.");
    }

    private String parseValue(final ListIterator<String> baseItr, final ArrayList<String> words) {
        ListIterator<String> itr = words.listIterator(baseItr.nextIndex());
        StringBuilder stringBuilder = new StringBuilder();
        String actWord = itr.next();
        int bracketCounter = 1;
        if (actWord.equals("{")) bracketCounter++;
        while (!actWord.equals(",") && bracketCounter > 0) {
            stringBuilder.append(actWord + ' ');
            actWord = itr.next();
            if (actWord.equals("{")) bracketCounter++;
            else if (actWord.equals("}")) bracketCounter--;
        }
        String result = stringBuilder.toString().trim();
        return result;
    }

    private boolean isRecordCorrect(Record record) {
        for (String[] mandatoryTable : checker.getRequired(record.categoryType)) {
            if (!isAnyInRecord(mandatoryTable, record)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAnyInRecord(String[] words, Record record) {
        for (String s : words) {
            if (record.hasField(s)) {
                return true;
            }
        }
        return false;
    }

}
