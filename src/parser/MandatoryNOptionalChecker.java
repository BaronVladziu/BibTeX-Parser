package parser;

import bibtex.CategoryType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MandatoryNOptionalChecker {

    private HashMap<CategoryType, ArrayList<String[]>> requiredFields = new HashMap<>();
    private HashMap<CategoryType, HashSet<String>> allowedFields = new HashMap<>();

    MandatoryNOptionalChecker() {
        //        ARTICLE
        ArrayList<String[]> articleRequired = new ArrayList<>();
        articleRequired.add(new String[]{"author"});
        articleRequired.add(new String[]{"title"});
        articleRequired.add(new String[]{"journal"});
        articleRequired.add(new String[]{"year"});
        articleRequired.add(new String[]{"volume"});
        this.requiredFields.put(CategoryType.ARTICLE, articleRequired);
        HashSet<String> articleAllowed = new HashSet<>();
        for (String[] names : articleRequired) {
            for (String name : names) {
                articleAllowed.add(name);
            }
        }
        articleAllowed.add("number");
        articleAllowed.add("pages");
        articleAllowed.add("month");
        articleAllowed.add("doi");
        articleAllowed.add("note");
        articleAllowed.add("key");
        articleAllowed.add("crossref");
        this.allowedFields.put(CategoryType.ARTICLE, articleAllowed);

        //        BOOK
        ArrayList<String[]> bookRequired = new ArrayList<>();
        bookRequired.add(new String[]{"author", "editor"});
        bookRequired.add(new String[]{"title"});
        bookRequired.add(new String[]{"publisher"});
        bookRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.BOOK, bookRequired);
        HashSet<String> bookAllowed = new HashSet<>();
        for (String[] names : bookRequired) {
            for (String name : names) {
                bookAllowed.add(name);
            }
        }
        bookAllowed.add("volume");
        bookAllowed.add("number");
        bookAllowed.add("series");
        bookAllowed.add("address");
        bookAllowed.add("edition");
        bookAllowed.add("month");
        bookAllowed.add("note");
        bookAllowed.add("key");
        bookAllowed.add("url");
        bookAllowed.add("crossref");
        this.allowedFields.put(CategoryType.BOOK, bookAllowed);

        //        BOOKLET
        ArrayList<String[]> bookletRequired = new ArrayList<>();
        bookletRequired.add(new String[]{"title"});
        this.requiredFields.put(CategoryType.BOOKLET, bookletRequired);
        HashSet<String> bookletAllowed = new HashSet<>();
        for (String[] names : bookletRequired) {
            for (String name : names) {
                bookletAllowed.add(name);
            }
        }
        bookletAllowed.add("author");
        bookletAllowed.add("howpublished");
        bookletAllowed.add("address");
        bookletAllowed.add("month");
        bookletAllowed.add("year");
        bookletAllowed.add("note");
        bookletAllowed.add("key");
        bookletAllowed.add("crossref");
        this.allowedFields.put(CategoryType.BOOKLET, bookletAllowed);

        //        CONFERENCE
        ArrayList<String[]> conferenceRequired = new ArrayList<>();
        conferenceRequired.add(new String[]{"author"});
        conferenceRequired.add(new String[]{"title"});
        conferenceRequired.add(new String[]{"booktitle"});
        conferenceRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.CONFERENCE, conferenceRequired);
        HashSet<String> conferenceAllowed = new HashSet<>();
        for (String[] names : conferenceRequired) {
            for (String name : names) {
                conferenceAllowed.add(name);
            }
        }
        conferenceAllowed.add("editor");
        conferenceAllowed.add("volume");
        conferenceAllowed.add("number");
        conferenceAllowed.add("series");
        conferenceAllowed.add("pages");
        conferenceAllowed.add("address");
        conferenceAllowed.add("month");
        conferenceAllowed.add("organization");
        conferenceAllowed.add("publisher");
        conferenceAllowed.add("note");
        conferenceAllowed.add("key");
        conferenceAllowed.add("crossref");
        this.allowedFields.put(CategoryType.CONFERENCE, conferenceAllowed);

        //        INBOOK
        ArrayList<String[]> inbookRequired = new ArrayList<>();
        inbookRequired.add(new String[]{"author", "editor"});
        inbookRequired.add(new String[]{"title"});
        inbookRequired.add(new String[]{"chapter", "pages"});
        inbookRequired.add(new String[]{"publisher"});
        inbookRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.INBOOK, inbookRequired);
        HashSet<String> inbookAllowed = new HashSet<>();
        for (String[] names : inbookRequired) {
            for (String name : names) {
                inbookAllowed.add(name);
            }
        }
        inbookAllowed.add("volume");
        inbookAllowed.add("number");
        inbookAllowed.add("series");
        inbookAllowed.add("type");
        inbookAllowed.add("address");
        inbookAllowed.add("edition");
        inbookAllowed.add("month");
        inbookAllowed.add("note");
        inbookAllowed.add("key");
        inbookAllowed.add("crossref");
        this.allowedFields.put(CategoryType.INBOOK, inbookAllowed);

        //        INCOLLECTION
        ArrayList<String[]> incollectionRequired = new ArrayList<>();
        incollectionRequired.add(new String[]{"author"});
        incollectionRequired.add(new String[]{"title"});
        incollectionRequired.add(new String[]{"booktitle"});
        incollectionRequired.add(new String[]{"publisher"});
        incollectionRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.INCOLLECTION, incollectionRequired);
        HashSet<String> incollectionAllowed = new HashSet<>();
        for (String[] names : incollectionRequired) {
            for (String name : names) {
                incollectionAllowed.add(name);
            }
        }
        incollectionAllowed.add("editor");
        incollectionAllowed.add("volume");
        incollectionAllowed.add("number");
        incollectionAllowed.add("series");
        incollectionAllowed.add("type");
        incollectionAllowed.add("chapter");
        incollectionAllowed.add("pages");
        incollectionAllowed.add("address");
        incollectionAllowed.add("edition");
        incollectionAllowed.add("month");
        incollectionAllowed.add("note");
        incollectionAllowed.add("key");
        incollectionAllowed.add("crossref");
        this.allowedFields.put(CategoryType.INCOLLECTION, incollectionAllowed);

        //        INPROCEEDINGS
        ArrayList<String[]> inproceedingsRequired = new ArrayList<>();
        inproceedingsRequired.add(new String[]{"author"});
        inproceedingsRequired.add(new String[]{"title"});
        inproceedingsRequired.add(new String[]{"booktitle"});
        inproceedingsRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.INPROCEEDINGS, inproceedingsRequired);
        HashSet<String> inproceedingsAllowed = new HashSet<>();
        for (String[] names : inproceedingsRequired) {
            for (String name : names) {
                inproceedingsAllowed.add(name);
            }
        }
        inproceedingsAllowed.add("editor");
        inproceedingsAllowed.add("volume");
        inproceedingsAllowed.add("number");
        inproceedingsAllowed.add("series");
        inproceedingsAllowed.add("pages");
        inproceedingsAllowed.add("address");
        inproceedingsAllowed.add("month");
        inproceedingsAllowed.add("organization");
        inproceedingsAllowed.add("publisher");
        inproceedingsAllowed.add("note");
        inproceedingsAllowed.add("key");
        inproceedingsAllowed.add("crossref");
        this.allowedFields.put(CategoryType.INPROCEEDINGS, inproceedingsAllowed);

        //        MANUAL
        ArrayList<String[]> manualRequired = new ArrayList<>();
        manualRequired.add(new String[]{"title"});
        this.requiredFields.put(CategoryType.MANUAL, manualRequired);
        HashSet<String> manualAllowed = new HashSet<>();
        for (String[] names : manualRequired) {
            for (String name : names) {
                manualAllowed.add(name);
            }
        }
        manualAllowed.add("author");
        manualAllowed.add("organization");
        manualAllowed.add("address");
        manualAllowed.add("edition");
        manualAllowed.add("month");
        manualAllowed.add("year");
        manualAllowed.add("note");
        manualAllowed.add("key");
        manualAllowed.add("crossref");
        this.allowedFields.put(CategoryType.MANUAL, manualAllowed);

        //        MASTERSTHESIS
        ArrayList<String[]> masterthesisRequired = new ArrayList<>();
        masterthesisRequired.add(new String[]{"author"});
        masterthesisRequired.add(new String[]{"title"});
        masterthesisRequired.add(new String[]{"school"});
        masterthesisRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.MASTERSTHESIS, masterthesisRequired);
        HashSet<String> masterthesisAllowed = new HashSet<>();
        for (String[] names : masterthesisRequired) {
            for (String name : names) {
                masterthesisAllowed.add(name);
            }
        }
        masterthesisAllowed.add("type");
        masterthesisAllowed.add("address");
        masterthesisAllowed.add("month");
        masterthesisAllowed.add("note");
        masterthesisAllowed.add("key");
        masterthesisAllowed.add("crossref");
        this.allowedFields.put(CategoryType.MASTERSTHESIS, masterthesisAllowed);

        //        MISC
        ArrayList<String[]> miscRequired = new ArrayList<>();
        this.requiredFields.put(CategoryType.MISC, miscRequired);
        HashSet<String> miscAllowed = new HashSet<>();
        for (String[] names : miscRequired) {
            for (String name : names) {
                miscAllowed.add(name);
            }
        }
        miscAllowed.add("author");
        miscAllowed.add("title");
        miscAllowed.add("howpublished");
        miscAllowed.add("month");
        miscAllowed.add("year");
        miscAllowed.add("note");
        miscAllowed.add("key");
        miscAllowed.add("crossref");
        this.allowedFields.put(CategoryType.MISC, miscAllowed);

        //        PHDTHESIS
        ArrayList<String[]> phdthesisRequired = new ArrayList<>();
        phdthesisRequired.add(new String[]{"author"});
        phdthesisRequired.add(new String[]{"title"});
        phdthesisRequired.add(new String[]{"school"});
        phdthesisRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.PHDTHESIS, phdthesisRequired);
        HashSet<String> phdthesisAllowed = new HashSet<>();
        for (String[] names : phdthesisRequired) {
            for (String name : names) {
                phdthesisAllowed.add(name);
            }
        }
        phdthesisAllowed.add("type");
        phdthesisAllowed.add("address");
        phdthesisAllowed.add("month");
        phdthesisAllowed.add("note");
        phdthesisAllowed.add("key");
        phdthesisAllowed.add("crossref");
        this.allowedFields.put(CategoryType.PHDTHESIS, phdthesisAllowed);

        //        PROCEEDINGS
        ArrayList<String[]> proceedingsRequired = new ArrayList<>();
        proceedingsRequired.add(new String[]{"title"});
        proceedingsRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.PROCEEDINGS, proceedingsRequired);
        HashSet<String> proceedingsAllowed = new HashSet<>();
        for (String[] names : proceedingsRequired) {
            for (String name : names) {
                proceedingsAllowed.add(name);
            }
        }
        proceedingsAllowed.add("editor");
        proceedingsAllowed.add("volume");
        proceedingsAllowed.add("number");
        proceedingsAllowed.add("series");
        proceedingsAllowed.add("address");
        proceedingsAllowed.add("month");
        proceedingsAllowed.add("publisher");
        proceedingsAllowed.add("organization");
        proceedingsAllowed.add("note");
        proceedingsAllowed.add("key");
        proceedingsAllowed.add("crossref");
        this.allowedFields.put(CategoryType.PROCEEDINGS, proceedingsAllowed);

        //        TECHREPORT
        ArrayList<String[]> techreportRequired = new ArrayList<>();
        techreportRequired.add(new String[]{"author"});
        techreportRequired.add(new String[]{"title"});
        techreportRequired.add(new String[]{"institution"});
        techreportRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.TECHREPORT, techreportRequired);
        HashSet<String> techreportAllowed = new HashSet<>();
        for (String[] names : techreportRequired) {
            for (String name : names) {
                techreportAllowed.add(name);
            }
        }
        techreportAllowed.add("type");
        techreportAllowed.add("number");
        techreportAllowed.add("address");
        techreportAllowed.add("month");
        techreportAllowed.add("note");
        techreportAllowed.add("key");
        techreportAllowed.add("crossref");
        this.allowedFields.put(CategoryType.TECHREPORT, techreportAllowed);

        //        UNPUBLISHED
        ArrayList<String[]> unpublishedRequired = new ArrayList<>();
        unpublishedRequired.add(new String[]{"author"});
        unpublishedRequired.add(new String[]{"title"});
        unpublishedRequired.add(new String[]{"note"});
        this.requiredFields.put(CategoryType.UNPUBLISHED, unpublishedRequired);
        HashSet<String> unpublishedAllowed = new HashSet<>();
        for (String[] names : unpublishedRequired) {
            for (String name : names) {
                unpublishedAllowed.add(name);
            }
        }
        unpublishedAllowed.add("month");
        unpublishedAllowed.add("year");
        unpublishedAllowed.add("key");
        unpublishedAllowed.add("crossref");
        this.allowedFields.put(CategoryType.UNPUBLISHED, unpublishedAllowed);
    }

    public boolean isAllowed(String name, CategoryType categoryType) {
        return this.allowedFields.get(categoryType).contains(name);
    }

    final ArrayList<String[]> getRequired(CategoryType categoryType) {
        return this.requiredFields.get(categoryType);
    }

}
