package bibtex;

import parser.MandatoryNOptionalChecker;
import parser.NullCrossrefException;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BibBase {

    private HashMap<String, Record> records = new HashMap<>();

    public void addRecord(Record record) {
        this.records.put(record.baseKey, record);
    }

    public void removeRecord(Record record) {
        this.records.remove(record.baseKey);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nBibTeX BIBLIOGRAPHY: {\n\n");
        for (Record r : this.records.values()) {
            stringBuilder.append(r.toString());
            stringBuilder.append('\n');
        }
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    public boolean equals(Object other) {
        BibBase otherBase = (BibBase) other;
        return (this.records.entrySet().equals(otherBase.records.entrySet()));
    }

    public void applyCrossrefs() throws NullCrossrefException {
        for (Record record : this.records.values()) {
            if (record.hasField("crossref")) {
                String ref = record.getField("crossref").value.toLowerCase();
                record.removeField("crossref");

                ref = ref.replace("{", "");
                ref = ref.replace("}", "");
                ref = ref.trim();

                Record refdRecord = records.get(ref);
                if (refdRecord == null) {
                    throw new NullCrossrefException("Crossreference to non-existing record: " + ref);
                }
                for (Field field : refdRecord.getFields()) {
                    if (!record.hasField(field.name)) {
                        record.addField(field);
                    }
                }
            }
        }
    }

    public Collection<Record> getRecords() {
        return records.values();
    }

    public void removeIncorrectFields(MandatoryNOptionalChecker checker) {
        for (Record record : this.records.values()) {
            record.removeIncorrectFields(checker);
        }
    }

    public BibBase searchForCategories(Set<String> categories) {
        if (categories.isEmpty()) {
            return this;
        } else {
            //Create categories set
            HashSet<CategoryType> enumCategories = new HashSet<>();
            for (String s : categories) {
                enumCategories.add(CategoryType.valueOf(s.trim().toUpperCase()));
            }
            //Add only wanted records
            BibBase result = new BibBase();
            for (Record record : this.records.values()) {
                if (enumCategories.contains(record.categoryType)) {
                    result.addRecord(record);
                }
            }
            return result;
        }
    }

    public BibBase searchForAuthors(Set<String> authors) {
        if (authors.isEmpty()) {
            return this;
        } else {
            //Add only wanted records
            BibBase result = new BibBase();
            for (Record record : this.records.values()) {
                Field field = record.getField("author");
                if (field != null) {
                    for (String author : field.value.split(" ")) {
                        for (String searchedAuthor : authors) {
                            if (searchedAuthor.contains(author.trim())) {
                                result.addRecord(record);
                                break;
                            }
                        }

                    }
                }
            }
            return result;
        }
    }

}
