package parser;

import org.junit.jupiter.api.Test;

class ParserTest {

    private Parser parser = new Parser();

    @Test
    void parse() {
        System.out.println(parser.parse("resources/bibtexExample1.bib"));
    }
}
