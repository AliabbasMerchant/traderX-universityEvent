package finos.traderx.peopleservice.exceptions;

public class PersonNotFoundException extends RuntimeException {
	public PersonNotFoundException(String message) {
		super(message);
	}
}
