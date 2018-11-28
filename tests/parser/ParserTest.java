package parser;

import bibtex.BibBase;
import bibtex.CategoryType;
import bibtex.Field;
import bibtex.Record;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParserTest {

    private Parser parser = new Parser();

    @Test
    void parseFile() {
        System.out.println(parser.parseFile("resources/bibtexExample1.bib"));
    }

    @Test
    void parse1() {
        BibBase bibBase = new BibBase();
        Assertions.assertEquals(bibBase,
                parser.parse("% Copyright (C) 1988, 2010 Oren Patashnik.\n" +
                        "% Unlimited copying and redistribution of this file are permitted if it\n" +
                        "% is unmodified.  Modifications (and their redistribution) are also\n" +
                        "% permitted, as long as the resulting file is renamed.\n" +
                        "\n" +
                        "@preamble{ \"\\newcommand{\\noopsort}[1]{} \"\n" +
                        "        # \"\\newcommand{\\printfirst}[2]{#1} \"\n" +
                        "        # \"\\newcommand{\\singleletter}[1]{#1} \"\n" +
                        "        # \"\\newcommand{\\switchargs}[2]{#2#1} \" }"));
    }
    @Test
    void parse2() {
        BibBase bibBase = new BibBase();
        Assertions.assertEquals(bibBase,
                parser.parse("@ARTICLE{article-minimal,\n" +
                        "   author = {L[eslie] A. Aamport},\n" +
                        "   title = {The Gnats and Gnus Document Preparation System},\n" +
                        "   journal = {\\mbox{G-Animal's} Journal},\n" +
                        "   year = 1986,\n" +
                        "}\n"));
    }
    @Test
    void parse3() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.ARTICLE, "article-full");
        record.addField(new Field("author", "{ L[eslie] A. Aamport }"));
        record.addField(new Field("title", "{ The Gnats and Gnus Document Preparation System }"));
        record.addField(new Field("journal", "{ \\mbox { G-Animal's } Journal }"));
        record.addField(new Field("volume", "41"));
        record.addField(new Field("number", "7"));
        record.addField(new Field("pages", "73+"));
        record.addField(new Field("month", "jul"));
        record.addField(new Field("note", "This is a full ARTICLE entry"));
        record.addField(new Field("year", "1986"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@ARTICLE{article-full,\n" +
                        "   author = {L[eslie] A. Aamport},\n" +
                        "   title = {The Gnats and Gnus Document Preparation System},\n" +
                        "   journal = {\\mbox{G-Animal's} Journal},\n" +
                        "   year = 1986,\n" +
                        "   volume = 41,\n" +
                        "   number = 7,\n" +
                        "   pages = \"73+\",\n" +
                        "   month = jul,\n" +
                        "   note = \"This is a full ARTICLE entry\",\n" +
                        "}\n" +
                        "\n" +
                        "The KEY field is here to override the KEY field in the journal being\n" +
                        "cross referenced (so is the NOTE field, in addition to its imparting\n" +
                        "information).\n" +
                        "\n" +
                        "@ARTICLE{article-crossref,\n" +
                        "   crossref = {WHOLE-JOURNAL},\n" +
                        "   key = \"\",\n" +
                        "   author = {L[eslie] A. Aamport},\n" +
                        "   title = {The Gnats and Gnus Document Preparation System},\n" +
                        "   pages = \"73+\",\n" +
                        "   note = \"This is a cross-referencing ARTICLE entry\",\n" +
                        "}"));
    }
    @Test
    public void parse4() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.INBOOK, "inbook-minimal");
        record.addField(new Field("author", "Donald E. Knuth"));
        record.addField(new Field("title", "Fundamental Algorithms"));
        record.addField(new Field("publisher", "Addison-Wesley"));
        record.addField(new Field("year", " { \\noopsort { 1973b } } 1973"));
        record.addField(new Field("chapter", "1.2"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@ARTICLE{whole-journal,\n" +
                        "   key = \"GAJ\",\n" +
                        "   journal = {\\mbox{G-Animal's} Journal},\n" +
                        "   year = 1986,\n" +
                        "   volume = 41,\n" +
                        "   number = 7,\n" +
                        "   month = jul,\n" +
                        "   note = {The entire issue is devoted to gnats and gnus\n" +
                        "                (this entry is a cross-referenced ARTICLE (journal))},\n" +
                        "}\n" +
                        "\n" +
                        "@INBOOK{inbook-minimal,\n" +
                        "   author = \"Donald E. Knuth\",\n" +
                        "   title = \"Fundamental Algorithms\",\n" +
                        "   publisher = \"Addison-Wesley\",\n" +
                        "   year = \"{\\noopsort{1973b}}1973\",\n" +
                        "   chapter = \"1.2\",\n" +
                        "}"));
    }
    @Test
    public void parse5() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.INBOOK, "inbook-full");
        record.addField(new Field("author", "Donald E. Knuth"));
        record.addField(new Field("title", "Fundamental Algorithms"));
        record.addField(new Field("volume", "1"));
        record.addField(new Field("series", "The Art of Computer Programming"));
        record.addField(new Field("publisher", "Addison-Wesley"));
        record.addField(new Field("address", "Reading , Massachusetts"));
        record.addField(new Field("edition", "Second"));
        record.addField(new Field("month", "10~ jan"));
        record.addField(new Field("year", " { \\noopsort { 1973b } } 1973"));
        record.addField(new Field("type", "Section"));
        record.addField(new Field("chapter", "1.2"));
        record.addField(new Field("pages", "10--119"));
        record.addField(new Field("note", "This is a full INBOOK entry"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@INBOOK{inbook-full,\n" +
                        "   author = \"Donald E. Knuth\",\n" +
                        "   title = \"Fundamental Algorithms\",\n" +
                        "   volume = 1,\n" +
                        "   series = \"The Art of Computer Programming\",\n" +
                        "   publisher = \"Addison-Wesley\",\n" +
                        "   address = \"Reading, Massachusetts\",\n" +
                        "   edition = \"Second\",\n" +
                        "   month = \"10~\" # jan,\n" +
                        "   year = \"{\\noopsort{1973b}}1973\",\n" +
                        "   type = \"Section\",\n" +
                        "   chapter = \"1.2\",\n" +
                        "   pages = \"10--119\",\n" +
                        "   note = \"This is a full INBOOK entry\",\n" +
                        "}\n" +
                        "\n" +
                        "@INBOOK{inbook-crossref,\n" +
                        "   crossref = \"whole-set\",\n" +
                        "   title = \"Fundamental Algorithms\",\n" +
                        "   volume = 1,\n" +
                        "   series = \"The Art of Computer Programming\",\n" +
                        "   edition = \"Second\",\n" +
                        "   year = \"{\\noopsort{1973b}}1973\",\n" +
                        "   type = \"Section\",\n" +
                        "   chapter = \"1.2\",\n" +
                        "   note = \"This is a cross-referencing INBOOK entry\",\n" +
                        "}"));
    }
    @Test
    public void parse6() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.BOOK, "book-minimal");
        record.addField(new Field("author", "Donald E. Knuth"));
        record.addField(new Field("title", "Seminumerical Algorithms"));
        record.addField(new Field("publisher", "Addison-Wesley"));
        record.addField(new Field("year", " { \\noopsort { 1973c } } 1981"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@BOOK{book-minimal,\n" +
                        "   author = \"Donald E. Knuth\",\n" +
                        "   title = \"Seminumerical Algorithms\",\n" +
                        "   publisher = \"Addison-Wesley\",\n" +
                        "   year = \"{\\noopsort{1973c}}1981\",\n" +
                        "}"));
    }
    @Test
    public void parse7() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.BOOK, "book-full");
        record.addField(new Field("author", "Donald E. Knuth"));
        record.addField(new Field("title", "Seminumerical Algorithms"));
        record.addField(new Field("volume", "2"));
        record.addField(new Field("series", "The Art of Computer Programming"));
        record.addField(new Field("publisher", "Addison-Wesley"));
        record.addField(new Field("address", "Reading , Massachusetts"));
        record.addField(new Field("edition", "Second"));
        record.addField(new Field("month", "10~ jan"));
        record.addField(new Field("year", " { \\noopsort { 1973c } } 1981"));
        record.addField(new Field("note", "This is a full BOOK entry"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@BOOK{book-full,\n" +
                        "   author = \"Donald E. Knuth\",\n" +
                        "   title = \"Seminumerical Algorithms\",\n" +
                        "   volume = 2,\n" +
                        "   series = \"The Art of Computer Programming\",\n" +
                        "   publisher = \"Addison-Wesley\",\n" +
                        "   address = \"Reading, Massachusetts\",\n" +
                        "   edition = \"Second\",\n" +
                        "   month = \"10~\" # jan,\n" +
                        "   year = \"{\\noopsort{1973c}}1981\",\n" +
                        "   note = \"This is a full BOOK entry\",\n" +
                        "}"));
    }
    @Test
    public void parse8() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.BOOK, "whole-set");
        record.addField(new Field("author", "Donald E. Knuth"));
        record.addField(new Field("publisher", "Addison-Wesley"));
        record.addField(new Field("title", "The Art of Computer Programming"));
        record.addField(new Field("series", "Four volumes"));
        record.addField(new Field("year", " { \\noopsort { 1973a } } { \\switchargs { --90 } { 1968 } }"));
        record.addField(new Field("note", "Seven volumes planned (this is a cross-referenced set of BOOKs)"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@BOOK{book-crossref,\n" +
                        "   crossref = \"whole-set\",\n" +
                        "   title = \"Seminumerical Algorithms\",\n" +
                        "   volume = 2,\n" +
                        "   series = \"The Art of Computer Programming\",\n" +
                        "   edition = \"Second\",\n" +
                        "   year = \"{\\noopsort{1973c}}1981\",\n" +
                        "   note = \"This is a cross-referencing BOOK entry\",\n" +
                        "}\n" +
                        "\n" +
                        "@BOOK{whole-set,\n" +
                        "   author = \"Donald E. Knuth\",\n" +
                        "   publisher = \"Addison-Wesley\",\n" +
                        "   title = \"The Art of Computer Programming\",\n" +
                        "   series = \"Four volumes\",\n" +
                        "   year = \"{\\noopsort{1973a}}{\\switchargs{--90}{1968}}\",\n" +
                        "   note = \"Seven volumes planned (this is a cross-referenced set of BOOKs)\",\n" +
                        "}"));
    }
    @Test
    public void parse9() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.BOOKLET, "booklet-minimal");
        record.addField(new Field("key", "Kn { \\printfirst { v } { 1987 } }"));
        record.addField(new Field("title", "The Programming of Computer Art"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@BOOKLET{booklet-minimal,\n" +
                        "   key = \"Kn{\\printfirst{v}{1987}}\",\n" +
                        "   title = \"The Programming of Computer Art\",\n" +
                        "}"));
    }

    @Test
    public void parse10() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.BOOKLET, "booklet-full");
        record.addField(new Field("author", "Jill C. Knvth"));
        record.addField(new Field("title", "The Programming of Computer Art"));
        record.addField(new Field("howpublished", "Vernier Art Center"));
        record.addField(new Field("address", "Stanford , California"));
        record.addField(new Field("month", "feb"));
        record.addField(new Field("year", "1988"));
        record.addField(new Field("note", "This is a full BOOKLET entry"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@BOOKLET{booklet-full,\n" +
                        "   author = \"Jill C. Knvth\",\n" +
                        "   title = \"The Programming of Computer Art\",\n" +
                        "   howpublished = \"Vernier Art Center\",\n" +
                        "   address = \"Stanford, California\",\n" +
                        "   month = feb,\n" +
                        "   year = 1988,\n" +
                        "   note = \"This is a full BOOKLET entry\",\n" +
                        "}"));
    }
    @Test
    public void parse11() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.INCOLLECTION, "incollection-minimal");
        record.addField(new Field("author", "Daniel D. Lincoll"));
        record.addField(new Field("title", "Semigroups of Recurrences"));
        record.addField(new Field("booktitle", "High Speed Computer and Algorithm Organization"));
        record.addField(new Field("publisher", "Academic Press"));
        record.addField(new Field("year", "1977"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@INCOLLECTION{incollection-minimal,\n" +
                        "   author = \"Daniel D. Lincoll\",\n" +
                        "   title = \"Semigroups of Recurrences\",\n" +
                        "   booktitle = \"High Speed Computer and Algorithm Organization\",\n" +
                        "   publisher = \"Academic Press\",\n" +
                        "   year = 1977,\n" +
                        "}"));
    }
    @Test
    public void parse12() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.INCOLLECTION, "incollection-full");
        record.addField(new Field("author", "Daniel D. Lincoll"));
        record.addField(new Field("title", "Semigroups of Recurrences"));
        record.addField(new Field("editor", "David J. Lipcoll and D. H. Lawrie and A. H. Sameh"));
        record.addField(new Field("booktitle", "High Speed Computer and Algorithm Organization"));
        record.addField(new Field("number", "23"));
        record.addField(new Field("series", "Fast Computers"));
        record.addField(new Field("chapter", "3"));
        record.addField(new Field("type", "Part"));
        record.addField(new Field("pages", "179--183"));
        record.addField(new Field("publisher", "Academic Press"));
        record.addField(new Field("address", "New York"));
        record.addField(new Field("edition", "Third"));
        record.addField(new Field("month", "sep"));
        record.addField(new Field("year", "1977"));
        record.addField(new Field("note", "This is a full INCOLLECTION entry"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@INCOLLECTION{incollection-full,\n" +
                        "   author = \"Daniel D. Lincoll\",\n" +
                        "   title = \"Semigroups of Recurrences\",\n" +
                        "   editor = \"David J. Lipcoll and D. H. Lawrie and A. H. Sameh\",\n" +
                        "   booktitle = \"High Speed Computer and Algorithm Organization\",\n" +
                        "   number = 23,\n" +
                        "   series = \"Fast Computers\",\n" +
                        "   chapter = 3,\n" +
                        "   type = \"Part\",\n" +
                        "   pages = \"179--183\",\n" +
                        "   publisher = \"Academic Press\",\n" +
                        "   address = \"New York\",\n" +
                        "   edition = \"Third\",\n" +
                        "   month = sep,\n" +
                        "   year = 1977,\n" +
                        "   note = \"This is a full INCOLLECTION entry\",\n" +
                        "}"));
    }
    @Test
    public void parse13() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.BOOK, "whole-collection");
        record.addField(new Field("editor", "David J. Lipcoll and D. H. Lawrie and A. H. Sameh"));
        record.addField(new Field("title", "High Speed Computer and Algorithm Organization"));
        record.addField(new Field("booktitle", "High Speed Computer and Algorithm Organization"));
        record.addField(new Field("number", "23"));
        record.addField(new Field("series", "Fast Computers"));
        record.addField(new Field("publisher", "Academic Press"));
        record.addField(new Field("address", "New York"));
        record.addField(new Field("edition", "Third"));
        record.addField(new Field("month", "sep"));
        record.addField(new Field("year", "1977"));
        record.addField(new Field("note", "This is a cross-referenced BOOK (collection) entry"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@INCOLLECTION{incollection-crossref,\n" +
                        "   crossref = \"whole-collection\",\n" +
                        "   author = \"Daniel D. Lincoll\",\n" +
                        "   title = \"Semigroups of Recurrences\",\n" +
                        "   pages = \"179--183\",\n" +
                        "   note = \"This is a cross-referencing INCOLLECTION entry\",\n" +
                        "}\n" +
                        "\n" +
                        "@BOOK{whole-collection,\n" +
                        "   editor = \"David J. Lipcoll and D. H. Lawrie and A. H. Sameh\",\n" +
                        "   title = \"High Speed Computer and Algorithm Organization\",\n" +
                        "   booktitle = \"High Speed Computer and Algorithm Organization\",\n" +
                        "   number = 23,\n" +
                        "   series = \"Fast Computers\",\n" +
                        "   publisher = \"Academic Press\",\n" +
                        "   address = \"New York\",\n" +
                        "   edition = \"Third\",\n" +
                        "   month = sep,\n" +
                        "   year = 1977,\n" +
                        "   note = \"This is a cross-referenced BOOK (collection) entry\",\n" +
                        "}"));
    }
    @Test
    public void parse14() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.MANUAL, "manual-minimal");
        record.addField(new Field("key", "Manmaker"));
        record.addField(new Field("title", "The Definitive Computer Manual"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@MANUAL{manual-minimal,\n" +
                        "   key = \"Manmaker\",\n" +
                        "   title = \"The Definitive Computer Manual\",\n" +
                        "}"));
    }
    @Test
    public void parse15() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.MANUAL, "manual-fill");
        record.addField(new Field("author", "Larry Manmaker"));
        record.addField(new Field("title", "The Definitive Computer Manual"));
        record.addField(new Field("organization", "Chips-R-Us"));
        record.addField(new Field("address", "Silicon Valley"));
        record.addField(new Field("edition", "Silver"));
        record.addField(new Field("month", "apr - may"));
        record.addField(new Field("year", "1986"));
        record.addField(new Field("note", "This is a full MANUAL entry"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@MANUAL{manual-full,\n" +
                        "   author = \"Larry Manmaker\",\n" +
                        "   title = \"The Definitive Computer Manual\",\n" +
                        "   organization = \"Chips-R-Us\",\n" +
                        "   address = \"Silicon Valley\",\n" +
                        "   edition = \"Silver\",\n" +
                        "   month = apr # \"-\" # may,\n" +
                        "   year = 1986,\n" +
                        "   note = \"This is a full MANUAL entry\",\n" +
                        "}"));
    }
    @Test
    public void parse16() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.MASTERSTHESIS, "mastersthesis-minimal");
        record.addField(new Field("author", "{ \\' { E } } douard Masterly"));
        record.addField(new Field("title", "Mastering Thesis Writing"));
        record.addField(new Field("school", "Stanford University"));
        record.addField(new Field("year", "1988"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@MASTERSTHESIS{mastersthesis-minimal,\n" +
                        "   author = \"{\\'{E}}douard Masterly\",\n" +
                        "   title = \"Mastering Thesis Writing\",\n" +
                        "   school = \"Stanford University\",\n" +
                        "   year = 1988,\n" +
                        "}"));
    }
    @Test
    public void parse17() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.MASTERSTHESIS, "mastersthesis-full");
        record.addField(new Field("author", "{ \\' { E } } douard Masterly"));
        record.addField(new Field("title", "Mastering Thesis Writing"));
        record.addField(new Field("school", "Stanford University"));
        record.addField(new Field("type", "Master's project"));
        record.addField(new Field("address", "English Department"));
        record.addField(new Field("month", "jun - aug"));
        record.addField(new Field("year", "1988"));
        record.addField(new Field("note", "This is a full MASTERSTHESIS entry"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@MASTERSTHESIS{mastersthesis-full,\n" +
                        "   author = \"{\\'{E}}douard Masterly\",\n" +
                        "   title = \"Mastering Thesis Writing\",\n" +
                        "   school = \"Stanford University\",\n" +
                        "   type = \"Master's project\",\n" +
                        "   address = \"English Department\",\n" +
                        "   month = jun # \"-\" # aug,\n" +
                        "   year = 1988,\n" +
                        "   note = \"This is a full MASTERSTHESIS entry\",\n" +
                        "}"));
    }
    @Test
    public void parse18() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.MISC, "misc-minimal");
        record.addField(new Field("key", "Missilany"));
        record.addField(new Field("note", "This is a minimal MISC entry"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@MISC{misc-minimal,\n" +
                        "   key = \"Missilany\",\n" +
                        "   note = \"This is a minimal MISC entry\",\n" +
                        "}"));
    }
    @Test
    public void parse19() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.MISC, "misc-full");
        record.addField(new Field("author", "Joe-Bob Missilany"));
        record.addField(new Field("title", "Handing out random pamphlets in airports"));
        record.addField(new Field("howpublished", "Handed out at O'Hare"));
        record.addField(new Field("month", "oct"));
        record.addField(new Field("year", "1984"));
        record.addField(new Field("note", "This is a full MISC entry"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@MISC{misc-full,\n" +
                        "   author = \"Joe-Bob Missilany\",\n" +
                        "   title = \"Handing out random pamphlets in airports\",\n" +
                        "   howpublished = \"Handed out at O'Hare\",\n" +
                        "   month = oct,\n" +
                        "   year = 1984,\n" +
                        "   note = \"This is a full MISC entry\",\n" +
                        "}"));
    }
    @Test
    public void parse20() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.INPROCEEDINGS, "inproceedings-minimal");
        record.addField(new Field("author", "Alfred V. Oaho and Jeffrey D. Ullman and Mihalis Yannakakis"));
        record.addField(new Field("title", "On Notions of Information Transfer in { VLSI } Circuits"));
        record.addField(new Field("booktitle", "Proc. Fifteenth Annual ACM Symposium on the Theory of Computing"));
        record.addField(new Field("year", "1983"));
        bibBase.addRecord(record);
        record = new Record(CategoryType.INPROCEEDINGS, "inproceedings-full");
        record.addField(new Field("author", "Alfred V. Oaho and Jeffrey D. Ullman and Mihalis Yannakakis"));
        record.addField(new Field("title", "On Notions of Information Transfer in { VLSI } Circuits"));
        record.addField(new Field("editor", "Wizard V. Oz and Mihalis Yannakakis"));
        record.addField(new Field("booktitle", "Proc. Fifteenth Annual ACM Symposium on the Theory of Computing"));
        record.addField(new Field("number", "17"));
        record.addField(new Field("series", "All ACM Conferences"));
        record.addField(new Field("pages", "133--139"));
        record.addField(new Field("month", "mar"));
        record.addField(new Field("year", "1983"));
        record.addField(new Field("address", "Boston"));
        record.addField(new Field("organization", "The OX Association for Computing Machinery"));
        record.addField(new Field("publisher", "Academic Press"));
        record.addField(new Field("note", "This is a full INPROCEDINGS entry"));
        bibBase.addRecord(record);
        record = new Record(CategoryType.PROCEEDINGS, "proceedings-minimal");
        record.addField(new Field("key", "OX { \\singleletter { stoc } }"));
        record.addField(new Field("title", "Proc. Fifteenth Annual Symposium on the Theory of Computing"));
        record.addField(new Field("year", "1983"));
        bibBase.addRecord(record);
        record = new Record(CategoryType.PROCEEDINGS, "proceedings-full");
        record.addField(new Field("editor", "Wizard V. Oz and Mihalis Yannakakis"));
        record.addField(new Field("title", "Proc. Fifteenth Annual Symposium on the Theory of Computing"));
        record.addField(new Field("number", "17"));
        record.addField(new Field("series", "All ACM Conferences"));
        record.addField(new Field("month", "mar"));
        record.addField(new Field("year", "1983"));
        record.addField(new Field("address", "Boston"));
        record.addField(new Field("organization", "The OX Association for Computing Machinery"));
        record.addField(new Field("publisher", "Academic Press"));
        record.addField(new Field("note", "This is a full PROCEEDINGS entry"));
        bibBase.addRecord(record);
        record = new Record(CategoryType.PROCEEDINGS, "whole-proceedings");
        record.addField(new Field("key", "OX { \\singleletter { stoc } }"));
        record.addField(new Field("organization", "The OX Association for Computing Machinery"));
        record.addField(new Field("title", "Proc. Fifteenth Annual Symposium on the Theory of Computing"));
        record.addField(new Field("address", "Boston"));
        record.addField(new Field("year", "1983"));
        record.addField(new Field("booktitle", "Proc. Fifteenth Annual ACM Symposium on the Theory of Computing"));
        record.addField(new Field("note", "This is a cross-referenced PROCEEDINGS"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@STRING{STOC-key = \"OX{\\singleletter{stoc}}\"}\n" +
                        "\n" +
                        "@STRING{ACM = \"The OX Association for Computing Machinery\"}\n" +
                        "\n" +
                        "@STRING{STOC = \" Symposium on the Theory of Computing\"}\n" +
                        "\n" +
                        "@INPROCEEDINGS{inproceedings-minimal,\n" +
                        "   author = \"Alfred V. Oaho and Jeffrey D. Ullman and Mihalis Yannakakis\",\n" +
                        "   title = \"On Notions of Information Transfer in {VLSI} Circuits\",\n" +
                        "   booktitle = \"Proc. Fifteenth Annual ACM\" # STOC,\n" +
                        "   year = 1983,\n" +
                        "}\n" +
                        "\n" +
                        "@INPROCEEDINGS{inproceedings-full,\n" +
                        "   author = \"Alfred V. Oaho and Jeffrey D. Ullman and Mihalis Yannakakis\",\n" +
                        "   title = \"On Notions of Information Transfer in {VLSI} Circuits\",\n" +
                        "   editor = \"Wizard V. Oz and Mihalis Yannakakis\",\n" +
                        "   booktitle = \"Proc. Fifteenth Annual ACM\" # STOC,\n" +
                        "   number = 17,\n" +
                        "   series = \"All ACM Conferences\",\n" +
                        "   pages = \"133--139\",\n" +
                        "   month = mar,\n" +
                        "   year = 1983,\n" +
                        "   address = \"Boston\",\n" +
                        "   organization = ACM,\n" +
                        "   publisher = \"Academic Press\",\n" +
                        "   note = \"This is a full INPROCEDINGS entry\",\n" +
                        "}\n" +
                        "\n" +
                        "@INPROCEEDINGS{inproceedings-crossref,\n" +
                        "   crossref = \"whole-proceedings\",\n" +
                        "   author = \"Alfred V. Oaho and Jeffrey D. Ullman and Mihalis Yannakakis\",\n" +
                        "   title = \"On Notions of Information Transfer in {VLSI} Circuits\",\n" +
                        "   organization = \"\",\n" +
                        "   pages = \"133--139\",\n" +
                        "   note = \"This is a cross-referencing INPROCEEDINGS entry\",\n" +
                        "}\n" +
                        "\n" +
                        "@PROCEEDINGS{proceedings-minimal,\n" +
                        "   key = STOC-key,\n" +
                        "   title = \"Proc. Fifteenth Annual\" # STOC,\n" +
                        "   year = 1983,\n" +
                        "}\n" +
                        "\n" +
                        "@PROCEEDINGS{proceedings-full,\n" +
                        "   editor = \"Wizard V. Oz and Mihalis Yannakakis\",\n" +
                        "   title = \"Proc. Fifteenth Annual\" # STOC,\n" +
                        "   number = 17,\n" +
                        "   series = \"All ACM Conferences\",\n" +
                        "   month = mar,\n" +
                        "   year = 1983,\n" +
                        "   address = \"Boston\",\n" +
                        "   organization = ACM,\n" +
                        "   publisher = \"Academic Press\",\n" +
                        "   note = \"This is a full PROCEEDINGS entry\",\n" +
                        "}\n" +
                        "\n" +
                        "@PROCEEDINGS{whole-proceedings,\n" +
                        "   key = STOC-key,\n" +
                        "   organization = ACM,\n" +
                        "   title = \"Proc. Fifteenth Annual\" # STOC,\n" +
                        "   address = \"Boston\",\n" +
                        "   year = 1983,\n" +
                        "   booktitle = \"Proc. Fifteenth Annual ACM\" # STOC,\n" +
                        "   note = \"This is a cross-referenced PROCEEDINGS\",\n" +
                        "}"));
    }
    @Test
    public void parse21() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.PHDTHESIS, "phdthesis-minimal");
        record.addField(new Field("author", "F. Phidias Phony-Baloney"));
        record.addField(new Field("title", "Fighting Fire with Fire: Festooning { F } rench Phrases"));
        record.addField(new Field("school", "Fanstord University"));
        record.addField(new Field("year", "1988"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@PHDTHESIS{phdthesis-minimal,\n" +
                        "   author = \"F. Phidias Phony-Baloney\",\n" +
                        "   title = \"Fighting Fire with Fire: Festooning {F}rench Phrases\",\n" +
                        "   school = \"Fanstord University\",\n" +
                        "   year = 1988,\n" +
                        "}"));
    }
    @Test
    public void parse22() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.PHDTHESIS, "phdthesis-minimal");
        record.addField(new Field("author", "F. Phidias Phony-Baloney"));
        record.addField(new Field("title", "Fighting Fire with Fire: Festooning { F } rench Phrases"));
        record.addField(new Field("school", "Fanstord University"));
        record.addField(new Field("type", "{ PhD } Dissertation"));
        record.addField(new Field("address", "Department of French"));
        record.addField(new Field("month", "jun - aug"));
        record.addField(new Field("year", "1988"));
        record.addField(new Field("note", "This is a full PHDTHESIS entry"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@PHDTHESIS{phdthesis-full,\n" +
                        "   author = \"F. Phidias Phony-Baloney\",\n" +
                        "   title = \"Fighting Fire with Fire: Festooning {F}rench Phrases\",\n" +
                        "   school = \"Fanstord University\",\n" +
                        "   type = \"{PhD} Dissertation\",\n" +
                        "   address = \"Department of French\",\n" +
                        "   month = jun # \"-\" # aug,\n" +
                        "   year = 1988,\n" +
                        "   note = \"This is a full PHDTHESIS entry\",\n" +
                        "}"));
    }
    @Test
    public void parse23() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.TECHREPORT, "techreport-minimal");
        record.addField(new Field("author", "Tom Terrific"));
        record.addField(new Field("title", "An { $O(n \\log n / \\! \\log\\log n)$ } Sorting Algorithm"));
        record.addField(new Field("institution", "Fanstord University"));
        record.addField(new Field("year", "1988"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@TECHREPORT{techreport-minimal,\n" +
                        "   author = \"Tom Terrific\",\n" +
                        "   title = \"An {$O(n \\log n / \\! \\log\\log n)$} Sorting Algorithm\",\n" +
                        "   institution = \"Fanstord University\",\n" +
                        "   year = 1988,\n" +
                        "}"));
    }
    @Test
    public void parse24() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.TECHREPORT, "techreport-full");
        record.addField(new Field("author", "Tom T { \\' { e } } rrific"));
        record.addField(new Field("title", "An { $O(n \\log n / \\! \\log\\log n)$ } Sorting Algorithm"));
        record.addField(new Field("institution", "Fanstord University"));
        record.addField(new Field("type", "Wishful Research Result"));
        record.addField(new Field("number", "7"));
        record.addField(new Field("address", "Computer Science Department , Fanstord , California"));
        record.addField(new Field("month", "oct"));
        record.addField(new Field("year", "1988"));
        record.addField(new Field("note", "This is a full TECHREPORT entry"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@TECHREPORT{techreport-full,\n" +
                        "   author = \"Tom T{\\'{e}}rrific\",\n" +
                        "   title = \"An {$O(n \\log n / \\! \\log\\log n)$} Sorting Algorithm\",\n" +
                        "   institution = \"Fanstord University\",\n" +
                        "   type = \"Wishful Research Result\",\n" +
                        "   number = \"7\",\n" +
                        "   address = \"Computer Science Department, Fanstord, California\",\n" +
                        "   month = oct,\n" +
                        "   year = 1988,\n" +
                        "   note = \"This is a full TECHREPORT entry\",\n" +
                        "}"));
    }
    @Test
    public void parse25() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.UNPUBLISHED, "unpublished-minimal");
        record.addField(new Field("author", "Ulrich { \\\" { U } } nderwood and Ned { \\~N } et and Paul { \\= { P } } ot"));
        record.addField(new Field("title", "Lower Bounds for Wishful Research Results"));
        record.addField(new Field("note", "Talk at Fanstord University (this is a minimal UNPUBLISHED entry)"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@UNPUBLISHED{unpublished-minimal,\n" +
                        "   author = \"Ulrich {\\\"{U}}nderwood and Ned {\\~N}et and Paul {\\={P}}ot\",\n" +
                        "   title = \"Lower Bounds for Wishful Research Results\",\n" +
                        "   note = \"Talk at Fanstord University (this is a minimal UNPUBLISHED entry)\",\n" +
                        "}"));
    }
    @Test
    public void parse26() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.UNPUBLISHED, "unpublished-full");
        record.addField(new Field("author", "Ulrich { \\\" { U } } nderwood and Ned { \\~N } et and Paul { \\= { P } } ot"));
        record.addField(new Field("title", "Lower Bounds for Wishful Research Results"));
        record.addField(new Field("month", "nov , dec"));
        record.addField(new Field("year", "1988"));
        record.addField(new Field("note", "Talk at Fanstord University (this is a full UNPUBLISHED entry)"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@UNPUBLISHED{unpublished-full,\n" +
                        "   author = \"Ulrich {\\\"{U}}nderwood and Ned {\\~N}et and Paul {\\={P}}ot\",\n" +
                        "   title = \"Lower Bounds for Wishful Research Results\",\n" +
                        "   month = nov # \", \" # dec,\n" +
                        "   year = 1988,\n" +
                        "   note = \"Talk at Fanstord University (this is a full UNPUBLISHED entry)\",\n" +
                        "}"));
    }
    @Test
    public void parse27() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.MISC, "random-note-crossref");
        record.addField(new Field("key", "{ Volume-2 }"));
        record.addField(new Field("note", "Volume~2 is listed under Knuth \\cite { book-full }"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@MISC{random-note-crossref,\n" +
                        "   key = {Volume-2},\n" +
                        "   note = \"Volume~2 is listed under Knuth \\cite{book-full}\"\n" +
                        "}"));
    }
    @Test
    public void parse28() {
        BibBase bibBase = new BibBase();
        Record record = new Record(CategoryType.ARTICLE, "article-crossref");
        record.addField(new Field("author", "{ L[eslie] A. Aamport }"));
        record.addField(new Field("title", "{ The Gnats and Gnus Document Preparation System }"));
        record.addField(new Field("year", "1988"));
        record.addField(new Field("volume", "41"));
        record.addField(new Field("journal", "dummy journal"));
        bibBase.addRecord(record);
        Assertions.assertEquals(bibBase,
                parser.parse("@ARTICLE{article-crossref,\n" +
                        "   key = \"\",\n" +
                        "   author = {L[eslie] A. Aamport},\n" +
                        "   title = {The Gnats and Gnus Document Preparation System},\n" +
                        "   pages = ,\n" +
                        "   year = 1988,\n" +
                        "   volume = 41,\n" +
                        "   journal = dummy journal\n" +
                        "}"));
    }

}
