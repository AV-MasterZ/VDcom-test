package org.zhadaev.vdcomtest.webapp;

import org.junit.Test;
import org.zhadaev.vdcomtest.webapp.model.CSVHelper;
import org.zhadaev.vdcomtest.webapp.model.Person;

import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CSVHelperTest {

    @Test
    public void testParseValidFile() {
        InputStream file = getClass().getClassLoader().getResourceAsStream("valid.csv");

        List<Person> persons = CSVHelper.csvToPersons(file);

        assertThat(persons).hasSize(13);
    }

    @Test
    public void testParseInvalidFile() {
        InputStream file = getClass().getClassLoader().getResourceAsStream("invalid.csv");

        List<Person> persons = CSVHelper.csvToPersons(file);

        assertThat(persons).isNotNull().isEmpty();
    }

}
