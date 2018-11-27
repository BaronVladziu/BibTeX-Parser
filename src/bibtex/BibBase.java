package bibtex;

import java.util.LinkedList;

public class BibBase {

    public LinkedList<Record> records = new LinkedList<>();

    public void addRecord(Record record) {
        System.out.println("\nAdding record:");
        System.out.println(record);
        this.records.add(record);
    }

}
