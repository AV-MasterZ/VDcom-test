package org.zhadaev.vdcomtest.webapp.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSVHelper {

    private static final String type = "text/csv";
    private static final String[] headers = {"id", "lastName", "firstName", "middleName", "position"};

    public static boolean hasCSVFormat(MultipartFile file) {
        return type.equals(file.getContentType());
    }

    public static List<Person> csvToPersons(InputStream file) {
        if (file == null) return Collections.emptyList();
        try {
            Reader fileReader = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8));
            List<Person> persons = new ArrayList<>();

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(headers)
                    .withFirstRecordAsHeader()
                    .parse(fileReader);

            for (CSVRecord record: records) {
                String stringId = record.get(headers[0]);
                Long id = (StringUtils.hasText(stringId)) ? Long.parseLong(stringId) : null;
                String lastName = record.get(headers[1]);
                String firstName = record.get(headers[2]);
                String middleName = record.get(headers[3]);
                String position = record.get(headers[4]);

                Person person = new Person();
                if (id != null) person.setId(id);
                person.setLastName(lastName);
                person.setFirstName(firstName);
                person.setMiddleName(middleName);
                person.setPosition(position);

                persons.add(person);
            }
            return persons;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
