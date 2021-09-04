Please see our group submission for code metrics and other details; this ReadMe file describes how to install and use the application.

https://docs.google.com/document/d/1feAFRtmd10nAfo74BSCUAtvkfL9vnJKI9TBX1F1RMGc/edit?usp=sharing

# Perfect Day Application

Group 5 CS5500 Project


## Setup/Installation

Install the following applications onto your system.
- Any Java IDE with a Spring add-on which can install Maven projects.
- PostgreSQL (Any version), which should also install PgAdmin.

Clone the GIT repository to your system.

After PostgreSQL installation, create a user with your chosen username and password. Create a database named perfect_day. Select the new database and open the file sql\createDatabase.sql in the repository. Run the script and ensure it succeeds.

After creating the database, edit the following file by setting your PostgreSQL username and password:
- src\main\resources\application.properties

Run Maven install on the project.

Build and run the project.

## Feature Documentation

The following features are supported in the application:

### REST API CALLS

Walking:
- GET activities\walkingActivities
- GET activities\walkingActivity\{id}
- POST activities\walkingActivity
- DELETE activities\walkingActivity\{id}

Running:
- GET activities\runningActivities
- GET activities\runningActivity\{id}
- POST activities\runningActivity
- DELETE activities\runningActivity\{id}

Cycling:
- GET activities\cyclingActivities
- GET activities\cyclingActivity\{id}
- POST activities\cyclingActivity
- DELETE activities\cyclingActivity\{id}

Kayaking:
- GET activities\kayakingActivities
- GET activities\kayakingActivity\{id}
- POST activities\kayakingActivity
- DELETE activities\kayakingActivity\{id}

CrossCountrySkiing:
- GET activities\crossCountrySkiingActivities
- GET activities\crossCountrySkiingActivity\{id}
- POST activities\crossCountrySkiingActivity
- DELETE activities\crossCountrySkiingActivity\{id}

### WEB PAGES

- GET /perfectday/index
- GET /perfectday/activityChart
- GET /perfectday/activityPieChart

### FILE PARSING

- JSON
- GEOJSON
- CSV

### SPRING OR CUSTOM DATABASE ACCESS LAYER

Two implementations of the application's REST API, web page, and file parsing are available in the codebase. Currently, the application will build with the Spring/JPA implementation active. The previous implementation can be found in it's entirety in the com.perfectday.legacy package.
