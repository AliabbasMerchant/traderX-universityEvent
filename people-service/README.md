# FINOS | TraderX Sample Trading App | People Service

![DEV Only Warning](https://badgen.net/badge/warning/not-for-production/red)
![Local Dev Machine Supported](http://badgen.net/badge/local-dev/supported/green)

## Description
The people service is used for managing users in the system.

* Returns information about a person by logonId or employeeId
* Returns the list of persons whose logonId or fullName contains the search text
* Returns if the logonId or employeeId can be associated to a valid person

## Running The People Service`

The API documentation is available via swagger UI:
`http://localhost:18089/v3/api-docs`
And via UI:
`http://localhost:18089/swagger-ui.html`

It runs by default on port 18089 which can be changed via the
`server.port=18089` system property or `$PEOPLE_SERVICE_PORT` environment variable.

## Building and Running
Check out the source code from git

```bash
gradlew bootRun
```

Configuration can be found in `application.properties` and can be overridden with env vars or command line parameters

Example URL:

`/People/GetPerson?LogonId=user01`
