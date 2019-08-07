- The implementation is generic for nearly any city in the world and a proximity of 50 miles max
- The maths involved are the less complex formulas
- We don't need to use Bigdecimal fas all calculations have a margin of error anyway, starting with the very distance calculation
- If more than 50 miles, the calculations of distance could be grossly wrong, but still would eliminate further cities (un;ess too close to poles)
- Only an exception is coded (the main one about the needed params) as instructions actually just told about 200 success return

TO RUN: right click on Main class and run as JavaApp (or java -jar target/{snapshot}	
being a jar can be only run with embedded tomcat or deployed in Heroku (or adapted for external Tomcat putting manually the servletinitializer)
go to http://localhost:8888/swagger-ui.html