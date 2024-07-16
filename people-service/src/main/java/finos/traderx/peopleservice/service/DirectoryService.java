package finos.traderx.peopleservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import finos.traderx.peopleservice.model.Person;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class DirectoryService {

    @Value("${people.jsonFilePath}")
    private Resource peopleFile;

    private List<Person> people;

    @PostConstruct
    private void readPeopleList() throws IOException {
        String jsonInput = FileCopyUtils.copyToString(new InputStreamReader(peopleFile.getInputStream()));

        ObjectMapper objectMapper = new ObjectMapper();
        this.people = objectMapper.readValue(jsonInput, new TypeReference<List<Person>>() {
        });
    }

    public List<Person> getMatchingPerson(String searchText, int take) {
        return this.people
                .stream()
                .filter(person ->
                        person.getFullName().contains(searchText) || person.getLogonId().contains(searchText))
                .limit(take)
                .toList();
    }

    @Nullable
    public Person getPerson(String logonId, String employeeId) {
        if (!StringUtils.isBlank(logonId)) {
            Person person = this.people
                    .stream()
                    .filter(p -> logonId.equals(p.getLogonId()))
                    .findFirst()
                    .orElse(null);
            if (person != null) {
                return person;
            }
        }
        if (!StringUtils.isBlank(employeeId)) {
            return this.people
                    .stream()
                    .filter(person -> employeeId.equals(person.getEmployeeId()))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public boolean validatePerson(String logonId, String employeeId) {
        return getPerson(logonId, employeeId) != null;
    }
}
