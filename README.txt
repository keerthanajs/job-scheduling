To clean, compile & create jar file
mvn clean package

To run with default options
java -jar job-scheduling.jar

To run with all options
java -jar -Dcores=2 -Dstrategy=sjf job-scheduling.jar