# FINOS | TraderX Sample Trading App | Reference Data Service

![DEV Only Warning](https://badgen.net/badge/warning/not-for-production/red) ![Local Dev Machine Supported](http://badgen.net/badge/local-dev/supported/green)

The Reference Data service provides a list of stock tickers and their associated company names via a RESTful interface.

## Prerequisites

This project assumes that your environment is already configured to use node and npm

By default this will run the application on localhost, port 18085, however the hostname and port can be modified by the following environment variables:

| Environment Variable Name  | Default Value    |
| -------------------------  | ---------------- |
| REFERENCE_DATA_SERVICE_PORT| 18085             |
| HOSTNAME                   | localhost        |


## Installation

```bash
$ ./gradlew build
```

## Running the app

It runs on port 18085 which can be changed via the

`server.port=18090`  system property or `$REFERENCE_DATA_SERVICE_PORT` environment variable.

How to run the application
Check out the source code from git

```bash
gradlew bootRun
```

Configuration can be found in `application.properties` and can be overridden with env vars or command line parameters


## Accessing the Reference Data service

Assuming the Reference Data service is running from the default location, otherwise modify the hostname and/or port
accordingly, then the following links are available:
 - http://localhost:18085/swagger-ui/index.html - the OpenAPI UI
 - http://localhost:18085/stocks - the reference data
 - http://localhost:18085/stocks/:ticker - the reference data for a specific ticker (or 404 if it does not exist)

 ## S&P 500 companies

The [CSV of S&P 500 companies](./data/s-and-p-500-companies.csv) was populated by copying the data from
[this table from Wikipedia](https://en.wikipedia.org/wiki/List_of_S%26P_500_companies#S&P_500_component_stocks).

