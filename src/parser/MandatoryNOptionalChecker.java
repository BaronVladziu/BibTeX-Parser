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
        articleRequired.add(new String[]{"author", "editor"});
        articleRequired.add(new String[]{"title"});
        articleRequired.add(new String[]{"publisher"});
        articleRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.BOOK, bookRequired);
        //        BOOKLET
        ArrayList<String[]> bookletRequired = new ArrayList<>();
        articleRequired.add(new String[]{"title"});
        this.requiredFields.put(CategoryType.BOOKLET, bookletRequired);
        //        CONFERENCE
        ArrayList<String[]> conferenceRequired = new ArrayList<>();
        articleRequired.add(new String[]{"author"});
        articleRequired.add(new String[]{"title"});
        articleRequired.add(new String[]{"booktitle"});
        articleRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.CONFERENCE, conferenceRequired);
        //        INBOOK
        ArrayList<String[]> inbookRequired = new ArrayList<>();
        articleRequired.add(new String[]{"author", "editor"});
        articleRequired.add(new String[]{"title"});
        articleRequired.add(new String[]{"chapter", "pages"});
        articleRequired.add(new String[]{"publisher"});
        articleRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.INBOOK, inbookRequired);
        //        INCOLLECTION
        ArrayList<String[]> incollectionRequired = new ArrayList<>();
        articleRequired.add(new String[]{"author"});
        articleRequired.add(new String[]{"title"});
        articleRequired.add(new String[]{"booktitle"});
        articleRequired.add(new String[]{"publisher"});
        articleRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.INCOLLECTION, incollectionRequired);
        //        INPROCEEDINGS
        ArrayList<String[]> inproceedingsRequired = new ArrayList<>();
        articleRequired.add(new String[]{"author"});
        articleRequired.add(new String[]{"title"});
        articleRequired.add(new String[]{"booktitle"});
        articleRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.INPROCEEDINGS, inproceedingsRequired);
        //        MANUAL
        ArrayList<String[]> manualRequired = new ArrayList<>();
        articleRequired.add(new String[]{"title"});
        this.requiredFields.put(CategoryType.MANUAL, manualRequired);
        //        MASTERSTHESIS
        ArrayList<String[]> masterthesisRequired = new ArrayList<>();
        articleRequired.add(new String[]{"author"});
        articleRequired.add(new String[]{"title"});
        articleRequired.add(new String[]{"school"});
        articleRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.MASTERSTHESIS, masterthesisRequired);
        //        MISC
        ArrayList<String[]> miscRequired = new ArrayList<>();
        this.requiredFields.put(CategoryType.MISC, miscRequired);
        //        PHDTHESIS
        ArrayList<String[]> phdthesisRequired = new ArrayList<>();
        articleRequired.add(new String[]{"author"});
        articleRequired.add(new String[]{"title"});
        articleRequired.add(new String[]{"school"});
        articleRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.PHDTHESIS, phdthesisRequired);
        //        PROCEEDINGS
        ArrayList<String[]> proceedingsRequired = new ArrayList<>();
        articleRequired.add(new String[]{"title"});
        articleRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.PROCEEDINGS, proceedingsRequired);
        //        TECHREPORT
        ArrayList<String[]> techreportRequired = new ArrayList<>();
        articleRequired.add(new String[]{"author"});
        articleRequired.add(new String[]{"title"});
        articleRequired.add(new String[]{"institution"});
        articleRequired.add(new String[]{"year"});
        this.requiredFields.put(CategoryType.TECHREPORT, techreportRequired);
        //        UNPUBLISHED
        ArrayList<String[]> unpublishedRequired = new ArrayList<>();
        articleRequired.add(new String[]{"author"});
        articleRequired.add(new String[]{"title"});
        articleRequired.add(new String[]{"note"});
        this.requiredFields.put(CategoryType.UNPUBLISHED, unpublishedRequired);
    }

    final ArrayList<String[]> getRequired(CategoryType categoryType) {
        return this.requiredFields.get(categoryType);
    }

}
