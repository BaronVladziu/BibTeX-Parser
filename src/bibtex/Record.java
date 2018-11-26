package bibtex;

import java.util.HashSet;

public class Record {

    public final CategoryType categoryType;
    public HashSet<Field> fields;

    public Record(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public void addField(Field field) {
        this.fields.add(field);
    }

}
