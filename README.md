# Stellar Burgers — UI Test Automation

End-to-end UI tests for the [Stellar Burgers](https://qa-stellarburgers.education-services.ru) web application.

## Tech stack
Java 11 · Selenium 4 · JUnit 5 · WebDriverManager · REST Assured · Allure · Maven

## Test coverage
| Area | Scenarios |
|---|---|
| Registration | successful sign-up, password shorter than 6 characters |
| Login | via main page button, "Personal Account" link, registration page, password recovery page |
| Personal account | open profile, go to constructor via link and via logo, logout |
| Constructor | switch between Buns, Sauces and Fillings sections |

## Architecture
- **Page Object pattern** for all pages (Main, Login, Register, Forgot Password, Profile)
- **Driver factory** with a JUnit 5 extension for browser setup and teardown
- **REST Assured** creates test users via API before tests and deletes them afterwards, so tests are independent
- **Allure** steps for readable reports

## Run tests

mvn clean test                     # Chrome (default)
mvn clean test -Dbrowser=firefox   # Firefox
mvn allure:serve                   # open the report
Part 1 of the final (diploma) project, Yandex Practicum QA Automation course.
