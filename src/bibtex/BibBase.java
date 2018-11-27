package bibtex;

import java.util.LinkedList;

public class BibBase {

    public LinkedList<Record> records = new LinkedList<>();

    public void addRecord(Record record) {
        this.records.add(record);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n\n");
        for (Record r : this.records) {
            stringBuilder.append(r.toString() + '\n');
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

}
