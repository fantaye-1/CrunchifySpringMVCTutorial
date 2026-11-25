# CrunchifySpringMVCTutorial

A minimal Spring MVC web app (based on the Crunchify "Hello World" tutorial): a classic
`DispatcherServlet` + XML configuration + JSP views, packaged as a WAR.

- `GET /` → `index.jsp` with a link to the controller
- `GET /welcome` (also `/welcome.html`) → `CrunchifyHelloWorld` puts a message in the model and renders `WEB-INF/jsp/welcome.jsp`

## Tech

- Java 17
- Spring Framework 6.2 (Spring MVC), XML config (`WEB-INF/crunchify-servlet.xml`)
- Jakarta Servlet 6.0, JSP + JSTL 3.0
- JUnit 5 + Spring MockMvc tests
- Maven, GitHub Actions CI

## Project layout

```
CrunchifySpringMVCTutorial/
├── pom.xml
├── src/com/crunchify/controller/CrunchifyHelloWorld.java
├── test/com/crunchify/controller/CrunchifyHelloWorldTest.java
└── WebContent/
    ├── index.jsp
    └── WEB-INF/
        ├── web.xml                 # DispatcherServlet mapped to "/"
        ├── crunchify-servlet.xml   # component scan + JSP view resolver
        └── jsp/welcome.jsp
```

## Build and test

```bash
cd CrunchifySpringMVCTutorial
mvn verify          # compiles, runs the tests, builds target/CrunchifySpringMVCTutorial-0.0.1-SNAPSHOT.war
```

The tests load the real `crunchify-servlet.xml`, so they check the component scan, the
request mapping and the view resolver together.

## Run

Spring 6 needs a Jakarta EE 9+ container: **Tomcat 10.1+** or **Jetty 12** (Tomcat 9 and older will not work).

1. `mvn verify`
2. Copy `target/CrunchifySpringMVCTutorial-0.0.1-SNAPSHOT.war` to Tomcat's `webapps/` (rename it to `crunchify.war` for a shorter URL).
3. Start Tomcat and open `http://localhost:8080/crunchify/` → click the link, or go straight to `/crunchify/welcome`.

In Eclipse: import as an existing Maven project and run it on a Tomcat 10.1 server.
