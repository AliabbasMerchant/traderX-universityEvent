package finos.traderx.peopleservice.model;

import java.util.Objects;

public class Person {

	private String logonId;
	private String fullName;
	private String email;
	private String employeeId;
	private String department;
	private String photoUrl;

	public String getLogonId() {
		return this.logonId;
	}
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
	public String getFullName() {
		return this.fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmployeeId() {
		return this.employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getDepartment() {
		return this.department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPhotoUrl() {
		return this.photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	@Override
	public String toString() {
		return "Person: " + this.logonId + " | " + this.fullName + " | " + this.email + " | " + this.employeeId + " | " + this.department + " |";
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Person person)) return false;

        return Objects.equals(logonId, person.logonId) && Objects.equals(fullName, person.fullName) && Objects.equals(email, person.email) && Objects.equals(employeeId, person.employeeId) && Objects.equals(department, person.department) && Objects.equals(photoUrl, person.photoUrl);
	}
}
