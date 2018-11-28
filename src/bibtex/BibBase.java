package bibtex;

import java.util.Arrays;
import java.util.LinkedList;

public class BibBase {

    public LinkedList<Record> records = new LinkedList<>();

    public void addRecord(Record record) {
        this.records.add(record);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nBibTeX BIBLIOGRAPHY: {\n\n");
        for (Record r : this.records) {
            stringBuilder.append(r.toString() + '\n');
        }
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    public boolean equals(Object other) {
        BibBase otherBase = (BibBase) other;
        return Arrays.deepEquals(this.records.toArray(), otherBase.records.toArray());
    }

}
