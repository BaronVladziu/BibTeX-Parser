package parser;

import bibtex.CategoryType;

import java.util.ArrayList;
import java.util.HashMap;

public class MandatoryNOptionalChecker {

    private HashMap<CategoryType, ArrayList<String[]>> requiredFields = new HashMap<>();

    MandatoryNOptionalChecker() {
        //        ARTICLE
        ArrayList<String[]> articleRequired = new ArrayList<>();
        articleRequired.add(new String[]{"author"});
        articleRequired.add(new String[]{"title"});
        articleRequired.add(new String[]{"journal"});
        articleRequired.add(new String[]{"year"});
        articleRequired.add(new String[]{"volume"});
        this.requiredFields.put(CategoryType.ARTICLE, articleRequired);
        //        BOOK
        ArrayList<String[]> bookRequired = new ArrayList<>();
        bookRequired.add(new String[]{"author", "editor"});
        bookRequired.add(new String[]{"title"});
        bookRequired.add(new String[]{"publisher"});
        bookRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.BOOK, bookRequired);
        //        BOOKLET
        ArrayList<String[]> bookletRequired = new ArrayList<>();
        bookletRequired.add(new String[]{"title"});
        this.requiredFields.put(CategoryType.BOOKLET, bookletRequired);
        //        CONFERENCE
        ArrayList<String[]> conferenceRequired = new ArrayList<>();
        conferenceRequired.add(new String[]{"author"});
        conferenceRequired.add(new String[]{"title"});
        conferenceRequired.add(new String[]{"booktitle"});
        conferenceRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.CONFERENCE, conferenceRequired);
        //        INBOOK
        ArrayList<String[]> inbookRequired = new ArrayList<>();
        inbookRequired.add(new String[]{"author", "editor"});
        inbookRequired.add(new String[]{"title"});
        inbookRequired.add(new String[]{"chapter", "pages"});
        inbookRequired.add(new String[]{"publisher"});
        inbookRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.INBOOK, inbookRequired);
        //        INCOLLECTION
        ArrayList<String[]> incollectionRequired = new ArrayList<>();
        incollectionRequired.add(new String[]{"author"});
        incollectionRequired.add(new String[]{"title"});
        incollectionRequired.add(new String[]{"booktitle"});
        incollectionRequired.add(new String[]{"publisher"});
        incollectionRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.INCOLLECTION, incollectionRequired);
        //        INPROCEEDINGS
        ArrayList<String[]> inproceedingsRequired = new ArrayList<>();
        inproceedingsRequired.add(new String[]{"author"});
        inproceedingsRequired.add(new String[]{"title"});
        inproceedingsRequired.add(new String[]{"booktitle"});
        inproceedingsRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.INPROCEEDINGS, inproceedingsRequired);
        //        MANUAL
        ArrayList<String[]> manualRequired = new ArrayList<>();
        manualRequired.add(new String[]{"title"});
        this.requiredFields.put(CategoryType.MANUAL, manualRequired);
        //        MASTERSTHESIS
        ArrayList<String[]> masterthesisRequired = new ArrayList<>();
        masterthesisRequired.add(new String[]{"author"});
        masterthesisRequired.add(new String[]{"title"});
        masterthesisRequired.add(new String[]{"school"});
        masterthesisRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.MASTERSTHESIS, masterthesisRequired);
        //        MISC
        ArrayList<String[]> miscRequired = new ArrayList<>();
        this.requiredFields.put(CategoryType.MISC, miscRequired);
        //        PHDTHESIS
        ArrayList<String[]> phdthesisRequired = new ArrayList<>();
        phdthesisRequired.add(new String[]{"author"});
        phdthesisRequired.add(new String[]{"title"});
        phdthesisRequired.add(new String[]{"school"});
        phdthesisRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.PHDTHESIS, phdthesisRequired);
        //        PROCEEDINGS
        ArrayList<String[]> proceedingsRequired = new ArrayList<>();
        proceedingsRequired.add(new String[]{"title"});
        proceedingsRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.PROCEEDINGS, proceedingsRequired);
        //        TECHREPORT
        ArrayList<String[]> techreportRequired = new ArrayList<>();
        techreportRequired.add(new String[]{"author"});
        techreportRequired.add(new String[]{"title"});
        techreportRequired.add(new String[]{"institution"});
        techreportRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.TECHREPORT, techreportRequired);
        //        UNPUBLISHED
        ArrayList<String[]> unpublishedRequired = new ArrayList<>();
        unpublishedRequired.add(new String[]{"author"});
        unpublishedRequired.add(new String[]{"title"});
        unpublishedRequired.add(new String[]{"note"});
        this.requiredFields.put(CategoryType.UNPUBLISHED, unpublishedRequired);
    }

    final ArrayList<String[]> getRequired(CategoryType categoryType) {
        return this.requiredFields.get(categoryType);
    }

}
