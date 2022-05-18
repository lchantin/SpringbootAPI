# SpringbootAPI

## API features

SpringbootAPI contains 2 features :
* One that allows to register a user 
* One that displays the details of a registered user

A user is defined by:
* A user name
* Birthdate a country of residence
* A user has optional attributes:
* A phone number
* A gender

Only adult French residents are allowed to create an account.

## How to install and run the project

1. Copy the path https://github.com/lchantin/SpringbootAPI.git.

    ![img_1.png](src/main/resources/images/CloneRepo.png)

2. Open your IDE (IntelliJ) and copy this path to import the project.
3. Checkout on the main branch.
4. Go to SpringbootapiApplication.class and run the project.

## How to use the API

Go to the link http://localhost:8088/swagger-ui/ to see the different HTTP requests that can be executed. 

* To create a user, simply select the POST /users/saveuser method and click on "Try it out" to complete the body containing the user's information and send the request.

   ![img.png](src/main/resources/images/postRequestExample.png)

* To show all users, select the GET /users/getusers method and click on "Try it out" and send the request.
* It's the same for others requests.