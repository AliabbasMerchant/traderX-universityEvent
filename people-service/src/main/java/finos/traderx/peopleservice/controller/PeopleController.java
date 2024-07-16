package finos.traderx.peopleservice.controller;

import java.util.List;

import finos.traderx.peopleservice.exceptions.BadRequestException;
import finos.traderx.peopleservice.exceptions.PersonNotFoundException;
import finos.traderx.peopleservice.model.Person;
import finos.traderx.peopleservice.service.DirectoryService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping(value="/People")
public class PeopleController {

	private static final Logger logger = LoggerFactory.getLogger(PeopleController.class);

	@Autowired
	DirectoryService directoryService;

	@GetMapping(value="/GetPerson", produces="application/json")
	public ResponseEntity<Person> getPerson(@RequestParam(value = "LogonId", required = false) String logonId, @RequestParam(value = "EmployeeId", required = false) String employeeId) {
		logger.info("getPerson called with LogonId {} and EmployeeId {}", logonId, employeeId);
		if (StringUtils.isBlank(logonId) && StringUtils.isBlank(employeeId)) {
			logger.warn("getPerson called with invalid LogonId {} and EmployeeId {}", logonId, employeeId);
			throw new BadRequestException("Either LogonId or EmployeeId must be provided");
		}
		Person person = this.directoryService.getPerson(logonId, employeeId);
		if (person == null) {
			logger.warn("No person found with LogonId {} and EmployeeId {}", logonId, employeeId);
			throw new PersonNotFoundException("Person not found");
		}
		logger.info("Person found with LogonId {} and EmployeeId {}", logonId, employeeId);
		return ResponseEntity.ok(person);
	}

	@GetMapping(value="/GetMatchingPeople", produces="application/json")
	public ResponseEntity<List<Person>> getMatchingPeople(@RequestParam("SearchText") String searchText, @RequestParam(value = "Take", defaultValue = "10", required = false) int take) {
		logger.info("getMatchingPeople called with SearchText {} and Take {}", searchText, take);
		if (StringUtils.isBlank(searchText)) {
			logger.warn("getMatchingPeople called invalid with SearchText {}", searchText);
			throw new BadRequestException("SearchText must be provided");
		}
		if (searchText.length() < 3) {
			logger.warn("getMatchingPeople called invalid with SearchText {}", searchText);
			throw new BadRequestException("SearchText must be at least 3 characters long");
		}
		List<Person> matchingPeople = this.directoryService.getMatchingPerson(searchText, take);
		if (CollectionUtils.isEmpty(matchingPeople)) {
			logger.warn("People not found with SearchText {}", searchText);
			throw new PersonNotFoundException("No matching people found");
		}
		logger.info("People found with SearchText {}", searchText);
		return ResponseEntity.ok(matchingPeople);
	}

	@GetMapping(value="/ValidatePerson", produces="application/json")
	public ResponseEntity<Boolean> validatePerson(@RequestParam(value = "LogonId", required = false) String logonId, @RequestParam(value = "EmployeeId", required = false) String employeeId) {
		logger.info("validatePerson called with LogonId {} and EmployeeId {}", logonId, employeeId);
		if (StringUtils.isBlank(logonId) && StringUtils.isBlank(employeeId)) {
			logger.warn("validatePerson called with invalid LogonId {} and EmployeeId {}", logonId, employeeId);
			throw new BadRequestException("Either LogonId or EmployeeId must be provided");
		}
		boolean isValid = this.directoryService.validatePerson(logonId, employeeId);
		if (!isValid) {
			logger.warn("Person does not exist with LogonId {} and EmployeeId {}", logonId, employeeId);
			throw new PersonNotFoundException("No such person");
		} else {
			logger.info("Valid person with LogonId {} and EmployeeId {}", logonId, employeeId);
			return ResponseEntity.ok(true);
		}
	}

	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<String> personNotFoundExceptionMapper(PersonNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<String> badRequestExceptionMapper(BadRequestException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> generalError(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
	}
}
