# INFO832_PROJ

This is a student group project. Its aim is to teach us how to use tools such as jenkins, solarqube and their interactions between one another,
while also having us to write tests, and refactor the code. 

Main branch : clean, all test pass 
test branch : developping, all test might not pass 

## JENKINS - SONARQUBE
Regarding jenkins, we create a pipeline. When we tell jenkins to build (either through github webhooks or manually) 
it will build pull the repo (one can choose the branch - but we'll stick to main and test for now).  
Once the build is done and test are complete (regardless of their success or failure), jenkins sends the 
results to sonar qube, which displays them in a readable manner. 
