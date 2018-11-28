package bibtex;

import java.util.HashSet;

public class Record {

    private final static int LINE_LENGTH = 120;

    public final CategoryType categoryType;
    public final String baseKey;
    private HashSet<Field> fields = new HashSet<>();

    public Record(CategoryType categoryType, String baseKey) {
        this.categoryType = categoryType;
        this.baseKey = baseKey;
    }

    public void addField(final Field field) {
        this.fields.add(field);
    }

    public boolean hasField(String fieldName) {
        return this.fields.contains(new Field(fieldName, ""));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.createHorizontalLine(LINE_LENGTH, '=') + '\n');
        String line = "| " + this.categoryType.toString() + " (" + baseKey + ")";
        stringBuilder.append(line + this.createHorizontalLine(LINE_LENGTH - line.length() - 1, ' ') + "|\n");
        for (Field f : this.fields) {
            stringBuilder.append(this.createHorizontalLine(LINE_LENGTH, '-') + '\n');
            line = "| " + f.toString();
            stringBuilder.append(line + this.createHorizontalLine(LINE_LENGTH - line.length() - 1, ' ') + "|\n");
        }
        stringBuilder.append(this.createHorizontalLine(LINE_LENGTH, '=') + '\n');
        return stringBuilder.toString();
    }

    private String createHorizontalLine(int length, char c) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public boolean equals(Object other) {
        Record otherRecord = (Record) other;
        if (this.fields.size() != otherRecord.fields.size()) {
            return false;
        }
        Field[] arr1 = new Field[this.fields.size()];
        Object[] oarr1 = this.fields.toArray();
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = (Field) oarr1[i];
        }
        Field[] arr2 = new Field[otherRecord.fields.size()];
        Object[] oarr2 = otherRecord.fields.toArray();
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = (Field) oarr2[i];
        }
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }
        return true;
    }

}
