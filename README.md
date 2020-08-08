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

11. Make loactionUpdate Listener activate..check if sw_locationsupdates is truned on or nt
then look for if the user wants to turn on or off the tracking . USe this to get location request
ex :  fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallBack,null);
intsanciate LocationCallback to get the instance. Then create location call back method.
ex :  locationCallBack = new LocationCallback(){
            
        }
this method is triggered when location is updated.(the updated time). Then over ride this onLocationResult(LocationResult locationResult)  method inside
locationCallBack. Then get the current location and store it in location insatce and updateUi function will take this location as parameter and show the location.
check if switch is enable or not if enable show the lcoation ..else show not being tracked!

12.show current adress
- instanciate this : Geocoder geocoder = new Geocoder(MainActivity.this);
-now apply this code to get the current location :

  try{
            //we have to take a list bacz the geocoder stores list of most recently seen adresses.so take the values in a adress list.
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            tv_address.setText(addresses.get(0).getAddressLine(0).toString()+",locality : "+addresses.get(0).getLocality()+",COUNTRY :"+addresses.get(0).getCountryName());

        }catch (Exception e){

            tv_address.setText("failed to get location!");
        } }
        
        
13. Save waypoints in list : Add a nother class "MyApplication" and extend it using Applicattion(app.Application)
Create a singleton instance of MyApplication class.override the onCreate method. Next take a List of Location to breadcrum and set getter and setter
for the List.
Create two way point button : new way point and show list of way points.intilizae them in the onCreate
.Now get the current location where the suer is. To get the current location create an instance of Location.
 
ex :
MyApplication myApplication = (MyApplication) getApplicationContext();
               savedLocations = myApplication.getMyLocation();
               savedLocations.add(currentLocation);
- the upper code is for current location .use it on click. Then save the number of view points.
android:name=".MyApplication" must be added to use MyApplication myApplication = (MyApplication) getApplicationContext(); this code.

14. show the list of wyaypoints :
1.Create a nother class ShowSavedLocationList.
2.Create a MyApplication isntance in the ShowSavedLocationList.
3.MyApplication myApplication = (MyApplication) getApplicationContext();
        List<Location>savedLocation = myApplication.getMyLocation(); - this will store the list of Location in the list.
4.Use arrayAdapter to show the list

15. Show map : create a button of show Map in the button click intent the following map activity.
1.Take a map activity and
2.go the xml and follow instructions.Go to the link to get the goggle map API key.Take the API key.Then add the
key here : <string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">YOUR_KEY_HERE</string>

3.In the mapACtivity.java create a new list of location and create a instance of "MyApplication" to get the getAppLocationcontest() and then save all the lcoation in the savedLocation, List

4. use ex in a foreach loop :
LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Latitude : "+location.getLatitude()+ "Longitude : "+location.getLatitude());
            mMap.addMarker(markerOptions);

to set all the breadcrums in the locations

16. Zoom in the last activity we droped.
 mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLocationPlaced,15.0f));

- use a setOnMarkerClickListener .
    

