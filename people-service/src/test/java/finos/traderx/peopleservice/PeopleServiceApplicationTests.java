package finos.traderx.peopleservice;

import finos.traderx.peopleservice.model.Person;
import finos.traderx.peopleservice.service.DirectoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations = "/test-application.properties")
class PeopleServiceApplicationTests {

    @Autowired
    DirectoryService directoryService;
    
    @Test
    void contextLoads() {
    }

    @Test
    void createAccount() {
        List<Person> people = directoryService.getMatchingPerson("Wil", 10);

        assertEquals(2, people.size());

        Person p1 = people.get(0);

        assertEquals(p1, directoryService.getPerson(p1.getLogonId(), ""));
        assertEquals(p1, directoryService.getPerson("", p1.getEmployeeId()));

        assertTrue(directoryService.validatePerson(p1.getLogonId(), ""));
        assertTrue(directoryService.validatePerson("", p1.getEmployeeId()));
    }
}
