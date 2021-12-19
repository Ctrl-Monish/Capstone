# Capstone
AR Navigation Capstone project.
-Ever got lost on campus?
-Were you unable to find H block as a fresher?
-Confused between in college Jaggi and Jaggi sweets?

This AR app aims to solve this issue by showing where classes/buildings are in the real world 
by indicating where they are over the camera view in augmented reality when the user holds the phone up and moves it about.
Just take out your phone from the pocket, open our app, choose the location, point the camera and you'll see where you want to go.

We get GPS latitude and longitude of a location and convert into ECEF(Earth Centre Earth Fixed) coordinate system.
This is unusable yet we have to convert it into ENU(East North Up) coordinate system for camera to understand and display it on screen in realtime in Augmented Reality.

First we store all the prominent Latitude/Longitude in the database with their specified details.
Then find our current location convert into ->ECEF then to ->ENU.
Finally convert ENU coordinate to Camera coordinate, we multiply camera projection matrix with ENU coordinate vector, the result is a vector [v0, v1, v2, v3].
Then x = (0.5 + v0 / v3) * widthOfCameraView and y = (0.5 - v1 / v3) * heightOfCameraView.
