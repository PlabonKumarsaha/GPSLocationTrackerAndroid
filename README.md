# GPS Location Tracker Android

#GPS

1.FusedLocationproviderClient - The stadard android api for rendering GPS and cell phone tower location data.
2.Permission - allow an app to track loaction of phone
3.LocationRequest- how frequent the location tracking should be.
4.Google play service - making phone app work
5.On sucess listener - update the UI when location is found
6.Starting and stopping location trackig
7.Geocode - automatically transalte a GPS location to a street adress.


8.FuseLocationProvider -  using providr API we can know user's last location.
a. set location request
b. set an interval
c.how fast to fetch data(between times)
d.add google play service. - Google Location and Activity Recognition	com.google.android.gms:play-services-location:17.0.0. add that in gradle script inside dependency and then
sync
e. insatnticate, FusedLocationProviderClient which is Google map's api for location serice. Then add Loacation request is a configurable file/settings related to FusedLocationProviderClient
f.set all prperties of locationRequest in oncreate.
g. set priority about the service
ex: locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

9. switch between GPS and cell phone tower tracking
-make on click listener for the GPS/save switch - in this switch you can check weather your using GPS or ther things
if we are using GPS then we use ex :

locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    tv_sensor.setText("Using GPS sensor...");
else the thing will be. ex :

locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    tv_sensor.setText("using WIFI + tower..");

10. Next use updateGPS()
-permit from the users to track GPS
- get the current location from the fused client
-update the UI to show the values in the screen

use fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this); then ask for user permit

-the using the LocationService we getFused loaction.
-then we check for permit

Now we make a UpdateGPS method which will show  the lat,long,altitude and speed of the location
-For altitude and speed we must check if condition as all phones don't have this functionality.

