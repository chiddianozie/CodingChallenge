# Submission for coding challenge
### requirements
- `gradle`
- `java8`


## To run the solution
There are two main folders: `gradleProjectCreditSussieTest` contains the solution code and `hsqldb-2.5.0` is for the database.
- Create the database as follows 
    - `cd \hsqldb-2.5.0\hsqldb`
    - `java -classpath lib/hsqldb.jar org.hsqldb.server.Server`

    - This creates a database `eventLogdb` as specified in  `\hsqldb-2.5.0\hsqldb\server.properties`
- Start the database server: Depending on your local settings, you may need to close the `terminal` from above before starting the database server
    - run the following command from java `\hsqldb-2.5.0\hsqldb` `java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/eventLogdb --dbname.0 logDetailsDB`
- Run the actual solution as follows from the root of `gradleProjectCreditSussieTest`
    - `gradle build` 
    - `java -cp build/libs/gradleProjectCreditSussieTest.jar:build/libs/* gradleProjectCreditSussieTest.driverApp logfile.txt`
    -  you can replace `logfile.txt` with the path of any new file you want to run.

## Caveats
- tested only on `OSX` and `Ubuntu`.
