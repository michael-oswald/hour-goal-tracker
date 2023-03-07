# Hour Goal Tracker
This app is for users to track hours spent towards their goals. 
Sometimes we as humans are a terrible at judging how many hours we ACTUALLY put into working on our goals.
If we actually track the hours, we'll have more accountability and likely achieve more progress. 

## What does this app do?
Lets you create an account (via email address or unique string)
Lets you create goals and record your time spent on the goal. 
Visit App here: https://up9psjypvd.us-east-1.awsapprunner.com/

[screen shot here of gif of app]

## The Tech Stack
**Frontend:**
* React 18.2.x
* React Router 6.8.x
* MDB Bootstrap (ui elements)
* Dockerized and deployed to AWS App Runner

**Backend:**
* Spring boot 3.0.x
* AWS DynamoDB
* Dockerized and deployed to AWS App Runner
* Written in 100% TDD style of development

## How to run locally?
#### Frontend:

The frontend is already pointing to the backend server deployed on AWS, so you don't need 
to deploy a local backend server to interact with the UI locally.

First you need to clone or download the zip of this github repo
```                                                                                                            
git clone https://github.com/goshipcode/hour-goal-tracker.git                                               
```                                                                                                            

Navigate into this repo's frontend directory on your computer:
```                                                                                                            
cd hour-goal-tracker/frontend                                                                                 
```                                                                                                            

Install the app with `npm` (If you don't have npm, then [install nodejs here](https://nodejs.org/en/download/))
```                                                                                                            
npm install                                                                                                    
``` 

Now run the app with npm
```                                                                                                            
npm start                                                                                                    
``` 

Navigate to http://localhost:3000/ to see the app!

#### Backend:
To run the spring boot backend first navigate to the backend directory on your cloned code:
```
cd hour-goal-tracker/backend
```

Run the application locally with the maven wrapper (you'll need Java 17 installed first) Here is a the [brew link if you use a mac](https://formulae.brew.sh/formula/openjdk@17)

```
./mvnw spring-boot:run
```
You can see the health say `UP` here: http://localhost:8080/actuator/health

**Note:** To fully run this backend you'll need a local dynamodb instance, or create your own AWS dynamodb table with the table name: `hour-goal-tracker`

`TODO: Put some instructions on how to run local dynamodb for development`

## Deployment to AWS
I deployed the frontend and backend to [AWS App Runner](https://aws.amazon.com/apprunner/) that just takes a container and runs it for you. 
I deployed using the handy [copilot cli](https://aws.amazon.com/containers/copilot/)

You can see the copilot config files in this repo here, and here. 

I have not tried this, but you should be able to deploy these applications yourself to AWS copilot by 
cloning this repo and executing the following 


From the frontend:
```
cd frontend
copilot svc deploy
```

From the backend: (Note Prereq: you'll need to add your aws account id for the dynamodb table arn in [this file](./backend/copilot/hgt-backend/addons/mytable-ddb.yaml))
```
cd backend
copilot svc deploy
```


## What I learned during this project
* Dynamodb enhanced client using java aws sdk 2.0. I've always used the 1.0 dynamodb mapper before, so this was a new 
library I haven't used before. It seemed to be easy to use and setup, I'll be using it from now on.

* React Router passing props to the new page. TODO: Add link here

* React [Lifting State up](https://beta.reactjs.org/learn/sharing-state-between-components) 
  By passing event handlers. Don't pass state objects down to child components, only pass static props, and event handlers!

