package org.zhadaev.vdcomtest.converter;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileParserTest {

    @Test
    public void testParsingFile() {
        String resource = getClass().getClassLoader().getResource("in.txt").getFile();

        List<Equality> equalities = FileParser.parseFile(resource);
        int filledEqualityCount = 0;
        int notFilledEqualityCount = 0;
        for (Equality equality: equalities) {
            if (equality.getRightValue() == null) {
                notFilledEqualityCount++;
            } else {
                filledEqualityCount++;
            }
        }

        assertThat(equalities).hasSize(11).doesNotContainNull();
        assertThat(filledEqualityCount).isEqualTo(7);
        assertThat(notFilledEqualityCount).isEqualTo(4);
    }

}
