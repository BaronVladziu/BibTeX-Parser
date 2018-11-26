package bibtex;

import java.util.HashSet;

public class Record {

    public final Category category;
    public HashSet<Field> fields;

    public Record(Category category) {
        this.category = category;
    }

    public void addField(Field field) {
        this.fields.add(field);
    }

}
