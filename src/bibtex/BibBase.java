package bibtex;

import java.util.LinkedList;

public class BibBase {

    public LinkedList<Record> records;

    public void addRecord(Record record) {
        this.records.add(record);
    }

}
