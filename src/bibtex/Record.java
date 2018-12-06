package bibtex;

import parser.MandatoryNOptionalChecker;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Record {

    private final static int LINE_LENGTH = 120;

    public final CategoryType categoryType;
    public final String baseKey;
    private HashMap<String, Field> fields = new HashMap<>();

    public Record(CategoryType categoryType, String baseKey) {
        this.categoryType = categoryType;
        this.baseKey = baseKey.toLowerCase();
    }

    public void addField(final Field field) {
        this.fields.put(field.name, field);
    }

    public boolean hasField(String fieldName) {
        return this.fields.containsKey(fieldName);
    }

    public Field getField(String fieldName) {
        return this.fields.get(fieldName);
    }

    public void removeField(String fieldName) {
        this.fields.remove(fieldName);
    }

    public Collection<Field> getFields() {
        return this.fields.values();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.createHorizontalLine(LINE_LENGTH, '=') + '\n');
        String line = "| " + this.categoryType.toString() + " (" + baseKey + ")";
        stringBuilder.append(line + this.createHorizontalLine(LINE_LENGTH - line.length() - 1, ' ') + "|\n");
        for (Field f : this.fields.values()) {
            stringBuilder.append(this.createHorizontalLine(LINE_LENGTH, '-') + '\n');
            String[] fieldStrings = f.toStrings();
            line = "| " + fieldStrings[0] + " = ";
            String line2 = fieldStrings[1];
            stringBuilder.append(line + line2 + this.createHorizontalLine(LINE_LENGTH - line.length() - line2.length() - 1, ' ') + "|\n");
            for (int i = 2; i < fieldStrings.length; i++) {
                line2 = "| " + this.createHorizontalLine(line.length() - 2, ' ') + fieldStrings[i];
                stringBuilder.append(line2 + this.createHorizontalLine(LINE_LENGTH - line2.length() - 1, ' ') + "|\n");
            }
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
        return (this.categoryType == otherRecord.categoryType &&
                this.baseKey.equals(otherRecord.baseKey) &&
                Arrays.deepEquals(this.fields.values().toArray(), otherRecord.fields.values().toArray()));
    }

    public int hashCode() {
        return baseKey.hashCode();
    }

    void removeIncorrectFields(MandatoryNOptionalChecker checker) {
        HashMap<String, Field> newFields = new HashMap<>();
        for (Map.Entry<String, Field> fieldEntry : this.fields.entrySet()) {
            if (checker.isAllowed(fieldEntry.getKey(), this.categoryType)) {
                newFields.put(fieldEntry.getKey(), fieldEntry.getValue());
            } else {
                System.out.println("Ignoring field: " + fieldEntry.getKey());
            }
        }
        this.fields = newFields;
    }

}
