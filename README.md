# Firebase Autentication Demo 

In this app we have implented Firebase Authentication and Firebase Remote Config 
Currently we can loging in this app with
1. Email 
2. Google 
3. Facebook 

### App Flow 
![App Flow](/Diagrams/App%20%20Flow.png)
1. On splash screen we are initialozing Facebook SDK loggin KeyHash and checking whether user is already logged in or not.
2. If not logged in then we will redirect user to Login page where user can login with Email, Google or Facebook.
3. If after login or if user is already loggeed in we will redirect to Logged in screen where we will display user name of current user with Firebase Authentication and Seasoal greeting text and image by as per configureation of Firebase Remote Config.
4. If user will tap on Logout button then firebase Authentication will logout user and we will redirect to Login Screen.

### Screenshots

|![Splash Screen Screenshot](/Screenshots/Splash%20Screen%20Screenshot.png)|      ![Login Screenshot](/Screenshots/Login%20Screenshot.png)       |![Login with other oprions Screenshot](/Screenshots/Login%20with%20other%20oprions%20Screenshot.png)|
|:------------------------------------------------------------------------:|:-------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------:|
|                        Splash Screen Screenshot                          |                          Login Screenshot                           |                                Login with other oprions Screenshot                                 |
|                                                                          | ![Logged In Screenshot](/Screenshots/Logged%20In%20Screenshot.png)  |                                                                                                    |
|                                                                          |                         Logged In Screenshot                        |                                                                                                    |
