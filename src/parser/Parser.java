package parser;

import bibtex.BibBase;
import bibtex.CategoryType;
import bibtex.Field;
import bibtex.Record;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {

    private static final MandatoryNOptionalChecker checker = new MandatoryNOptionalChecker();
    private HashMap<String, String> bibStrings = new HashMap<>();

    public BibBase parse(String bibfile) {
        ArrayList<String> words = new ArrayList<String>();
        this.prepareForParsing(words, bibfile);
        return this.parse(words);
    }

    public BibBase parseFile(String bibfilePath) {
        final ArrayList<String> words = this.loadTextFile(bibfilePath);
        return this.parse(words);
    }

    private BibBase parse(final ArrayList<String> words) {
        this.bibStrings.clear();
        BibBase bibBase = new BibBase();
        //Parse words
        ListIterator<String> itr = words.listIterator();
        while (itr.hasNext()) {
            String actWord = itr.next();
            if (actWord.equals("@")) {
                String nextWord = itr.next().toLowerCase();
                itr.next();
                String baseKey = itr.next();
                itr.previous();
                itr.previous();
                switch (nextWord) {
                    case "preamble": {
                        break;
                    }
                    case "string": {
                        Field field = this.parseBibString(itr, words);
                        bibStrings.put(field.name, field.value);
                        break;
                    }
                    default: {
                        Record record = this.parseRecord(nextWord, baseKey, itr, words);
                        bibBase.addRecord(record);
                        break;
                    }
                }
            }
        }
        bibBase = this.cleanBibBase(bibBase);
        return bibBase;
    }

    private ArrayList<String> loadTextFile(String filePath) {
        ArrayList<String> words = new ArrayList<>();
        try {
            //Create word array
            Scanner textfile = new Scanner(new File(filePath));
            while (textfile.hasNext()) {
                String word = textfile.next();
                this.prepareForParsing(words, word);
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

    private void prepareForParsing(ArrayList<String> array, String word) {
        word = word.replace("@", " @ ");
        word = word.replace("{", " { ");
        word = word.replace("}", " } ");
        word = word.replace("\"", " \" ");
        word = word.replace("\\ \"", "\\\"");
        word = word.replace("\n", "");
        word = word.replace("#", "");
        word = word.replace(",", " , ");
        String[] splitedWord = word.split(" ");
        for (String w : splitedWord) {
            if (!w.equals("") && !w.equals("\n")) {
                array.add(w);
            }
        }
    }

    private Record parseRecord(String category, String baseKey, ListIterator<String> itr, final ArrayList<String> words) {
        int bracketsCount = 0;
        Record record = new Record(CategoryType.valueOf(category.toUpperCase()), baseKey.toLowerCase());
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
                        if (!nextWord.equals("")) {
                            record.addField(new Field(actWord, nextWord));
                        }
                    }
                    itr.previous();
                }
            }
        } while (bracketsCount > 0);
        throw new ParseException("Unexpected end of record.");
    }

    private String parseValue(final ListIterator<String> baseItr, final ArrayList<String> words) {
        //baseItr should be on "="
        ListIterator<String> itr = words.listIterator(baseItr.nextIndex());
        StringBuilder stringBuilder = new StringBuilder();
        String actWord = itr.next();
        int bracketCounter = 1;
        boolean isBetweenQMs = false;
        if (actWord.equals("{")) bracketCounter++;
        while (isBetweenQMs || (!actWord.equals(",") && bracketCounter > 0)) {
            if (actWord.equals("\"")) {
                isBetweenQMs = !isBetweenQMs;
            } else {
                if (!isBetweenQMs && this.bibStrings.containsKey(actWord)) {
                    stringBuilder.append(this.bibStrings.get(actWord));
                    stringBuilder.append(' ');
                } else {
                    stringBuilder.append(actWord);
                    stringBuilder.append(' ');
                }
            }
            actWord = itr.next();
            if (actWord.equals("{")) bracketCounter++;
            else if (actWord.equals("}")) bracketCounter--;
        }
        //Cut spaces
        return stringBuilder.toString().trim();
    }

    private Field parseBibString(final ListIterator<String> baseItr, final ArrayList<String> words) {
        //baseItr should be on "STRING" declaration
        ListIterator<String> itr = words.listIterator(baseItr.nextIndex());
        itr.next();
        String name = itr.next();
        itr.next();
        return new Field(name, this.parseValue(itr, words));
    }

    private BibBase cleanBibBase(BibBase bibBase) {
        //Apply crossrefs
        try {
            bibBase.applyCrossrefs();
        } catch (NullCrossrefException ex) {
            System.out.println(ex.getMessage());
        }
        //Remove incorrect records
        BibBase newBibBase = new BibBase();
        for (Record record : bibBase.getRecords()) {
            try {
                if (this.isRecordCorrect(record)) {
                    newBibBase.addRecord(record);
                }
            } catch (MissingFieldException ex) {
                System.out.println(ex.getMessage());
            }
        }
        //Remove incorrect fields
        newBibBase.removeIncorrectFields(checker);
        return newBibBase;
    }

    private boolean isRecordCorrect(Record record) throws MissingFieldException {
        for (String[] mandatoryTable : checker.getRequired(record.categoryType)) {
            if (!isAnyInRecord(mandatoryTable, record)) {
                StringBuilder message = new StringBuilder();
                for (String s : mandatoryTable) {
                    message.append(s);
                    message.append(" or ");
                }
                message.delete(message.length() - 4, message.length() - 1);
                throw new MissingFieldException("Ignoring record: {\n" + record + "}\nMissing " + message + "\n");
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
