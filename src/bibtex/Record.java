package bibtex;

import java.util.HashSet;

public class Record {

    public final CategoryType categoryType;
    public HashSet<Field> fields = new HashSet<>();

    public Record(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public void addField(Field field) {
        this.fields.add(field);
    }

    public boolean hasField(String fieldName) {
        return this.fields.contains(new Field(fieldName, null));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.categoryType.toString() + '\n');
        for (Field f : this.fields) {
            stringBuilder.append(f.toString() + '\n');
        }
        return stringBuilder.toString();
    }

}
