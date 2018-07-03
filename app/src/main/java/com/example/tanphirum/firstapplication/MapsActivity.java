package com.example.tanphirum.firstapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.tanphirum.firstapplication.bean.LatLngItem;
import com.example.tanphirum.firstapplication.bean.map.DirectionsResultItem;
import com.example.tanphirum.firstapplication.bean.map.EndLocation_;
import com.example.tanphirum.firstapplication.bean.map.Leg;
import com.example.tanphirum.firstapplication.bean.map.Route;
import com.example.tanphirum.firstapplication.bean.map.StartLocation_;
import com.example.tanphirum.firstapplication.bean.map.Step;
import com.example.tanphirum.firstapplication.utils.PermissionUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.gson.Gson;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MapsActivity extends AppCompatActivity
        implements
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private static final String TAG = MapsActivity.class.getSimpleName();

    private static final int LOCATION_UPDATE_MIN_TIME = 2000;
    private static final int LOCATION_UPDATE_MIN_DISTANCE = 10;

    private LinkedList<LatLng> mListLatLng;
    private LatLng mMyCurrentLocation;

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;

    private GoogleMap mMap;

    private List<Polyline> mListPolyLines;

    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        mListPolyLines = new ArrayList<>();

        mListLatLng = new LinkedList<>();
        mListLatLng.add(new LatLng(11.5779907, 104.9164398));
        mListLatLng.add(new LatLng(11.5821424, 104.9126096));
        mListLatLng.add(new LatLng(11.565787, 104.9104294));
        mListLatLng.add(new LatLng(11.584462, 104.891448));
        mListLatLng.add(new LatLng(11.5482364, 104.8976085));
        mListLatLng.add(new LatLng(11.5550195, 104.9196265));
        mListLatLng.add(new LatLng(11.5821424, 104.9126096));
        mListLatLng.add(new LatLng(11.5438609, 104.9122386));

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        addMarkerToMap();

        mMap.setOnMarkerClickListener(this);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();
    }

    private void addMarkerToMap() {
        for (LatLng latLng : mListLatLng) {
            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title("Title " + latLng.latitude)
                    .snippet("msg " + latLng.latitude)
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_acleda_marker)));
        }
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
            getCurrentLocation();
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        mMyCurrentLocation = new LatLng(location.getLatitude(), location.getLongitude());
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(getString(R.string.google_api_key))
                .build();

        LatLngItem mCurrent = new LatLngItem(mMyCurrentLocation.latitude, mMyCurrentLocation.longitude);

        final LatLngItem mDis = new LatLngItem(marker.getPosition().latitude, marker.getPosition().longitude);

        DirectionsApiRequest req = DirectionsApi.getDirections(context, mCurrent.toString(), mDis.toString())
                .optimizeWaypoints(true).alternatives(true).mode(TravelMode.WALKING);


        /*final List<LatLng> path = new ArrayList();
        try {
            DirectionsResult res = req.await();

            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs != null) {
                    for (int i = 0; i < route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j = 0; j < leg.steps.length; j++) {
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length > 0) {
                                    for (int k = 0; k < step.steps.length; k++) {
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getLocalizedMessage());
        }

        //Draw the polyline
        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
            mMap.addPolyline(opts);
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mMyCurrentLocation, 6));*/
        //async call
        req.setCallback(new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult result) {

                //Define list to get all latlng for the route
                final List<LatLng> path = new ArrayList();
                Gson gson = new Gson();
                DirectionsResultItem items = gson.fromJson(gson.toJson(result), DirectionsResultItem.class);
                int met = 0;
                Leg leg = new Leg();
                for (Route route : items.routes) {
                    for (Leg l : route.legs) {
                        int inMeters = l.distance.inMeters;
                        if (met == 0) {
                            met = inMeters;
                            leg = l;
                        } else {
                            if (met > inMeters) {
                                met = inMeters;
                                leg = l;
                            }
                        }
                    }
                }

                for (Step step : leg.steps) {
                    StartLocation_ startLocation = step.startLocation;
                    EndLocation_ endLocation_ = step.endLocation;
                    path.add(new LatLng(startLocation.lat, startLocation.lng));
                    path.add(new LatLng(endLocation_.lat, endLocation_.lng));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Draw the polyline
                        if (path.size() > 0) {

                            if (mListPolyLines.size() > 0) {
                                for (Polyline polyline : mListPolyLines) {
                                    polyline.remove();
                                }
                            }

                            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.RED).width(10);
                            Polyline routePoly = mMap.addPolyline(opts);

                            List<LatLng> pathStart = new ArrayList();
                            pathStart.add(new LatLng(mMyCurrentLocation.latitude, mMyCurrentLocation.longitude));
                            pathStart.add(path.get(0));
                            Polyline startPoly = mMap.addPolyline(new PolylineOptions().addAll(pathStart));
                            startPoly.setPattern(PATTERN_POLYLINE_DOTTED);

                            List<LatLng> pathEnd = new ArrayList();
                            pathEnd.add(new LatLng(mDis.latitude, mDis.longitude));
                            pathEnd.add(path.get(path.size() - 1));
                            Polyline endPoly = mMap.addPolyline(new PolylineOptions().addAll(pathEnd));
                            endPoly.setPattern(PATTERN_POLYLINE_DOTTED);

                            mListPolyLines.add(routePoly);
                            mListPolyLines.add(startPoly);
                            mListPolyLines.add(endPoly);
                        }


                    }
                });

            }

            @Override
            public void onFailure(Throwable e) {

            }
        });


        //request with retrofit

        /*APIMapInterface mapApi = APIClient.getClient("https://maps.googleapis.com").create(APIMapInterface.class);

        Call<MapItem> call = mapApi.getMapDirection("11.5769, 104.9202", "11.57742590,104.91693970",
                "optimize:true|Providence,ACLEDA Bank Plc. Headquarters (PhnomPenh, Cambodia)",
                "AIzaSyC4nlrVmoeTxp25te3KwjuZ6HqCyT5ADOM");

        call.enqueue(new Callback<MapItem>() {
            @Override
            public void onResponse(Call<MapItem> call, Response<MapItem> response) {

                Gson gson = new Gson();

                final List<LatLng> path = new ArrayList();
                MapItem items = response.body();
                int met = 0;
                Leg leg = new Leg();
                for (com.example.tanphirum.firstapplication.bean.map_api.Route route : items.routes) {
                    for (com.example.tanphirum.firstapplication.bean.map_api.Leg l : route.legs) {
                        for (com.example.tanphirum.firstapplication.bean.map_api.Step step : l.steps) {
                            com.example.tanphirum.firstapplication.bean.map_api.Polyline points1 = step.polyline;
                            if (points1 != null) {
                                //Decode polyline and add points to list of route coordinates
                                List<com.google.maps.model.LatLng> coords1 = PolylineEncoding.decode(points1.points);
                                for (com.google.maps.model.LatLng coord1 : coords1) {
                                    path.add(new LatLng(coord1.lat, coord1.lng));
                                }
                            }
                        }
                    }
                }

                if (path.size() > 0) {
                    path.add(0, new LatLng(11.5769, 104.9202));
                    path.set(path.size() - 1, new LatLng(11.57742590, 104.91693970));
                    PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.RED).width(10);
                    mMap.addPolyline(opts);
                }
            }

            @Override
            public void onFailure(Call<MapItem> call, Throwable t) {
                MessageUtils.showToast(MapsActivity.this, t.getLocalizedMessage());
            }
        });*/

        return false;
    }

    /**
     * Styles the polyline, based on type.
     *
     * @param polyline The polyline object that needs styling.
     */
    private void stylePolyline(Polyline polyline) {
        String type = "";
        // Get the data object stored with the polyline.
        if (polyline.getTag() != null) {
            type = polyline.getTag().toString();
        }

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "A":
                // Use a custom bitmap as the cap at the start of the line.
                polyline.setStartCap(
                        new CustomCap(
                                BitmapDescriptorFactory.fromResource(R.drawable.ic_android), 10));
                break;
            case "B":
                // Use a round cap at the start of the line.
                polyline.setStartCap(new RoundCap());
                break;
        }

        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setColor(COLOR_BLACK_ARGB);
        polyline.setJointType(JointType.ROUND);
    }

    private static final PatternItem DOT = new Dot();
    private static final PatternItem GAP = new Gap(20);

    // Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);

    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int POLYLINE_STROKE_WIDTH_PX = 12;

    private void getCurrentLocation() {
        mMap.getUiSettings().setZoomControlsEnabled(true);

        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location location = null;
        if (!(isGPSEnabled || isNetworkEnabled)) {
            Toast.makeText(this, "Can't get current location", Toast.LENGTH_SHORT).show();
            //Snackbar.make(mMapView, R.string.error_location_provider, Snackbar.LENGTH_INDEFINITE).show();
        } else {
            if (isNetworkEnabled) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    return;

                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (isGPSEnabled) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
        if (location != null)
            moveToCurrentLocation(location);

    }

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            moveToCurrentLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d(TAG, "");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d(TAG, "");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d(TAG, "");
        }
    };

    private void moveToCurrentLocation(Location location) {
        if (location != null) {
            mMyCurrentLocation = new LatLng(location.getLatitude(), location.getLongitude());

            LatLngBounds.Builder bounds = new LatLngBounds.Builder();
            for (LatLng item : mListLatLng) {
                bounds.include(item);
            }
            bounds.include(mMyCurrentLocation);

            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 30));

            mLocationManager.removeUpdates(mLocationListener);
        } else {
            LatLngBounds.Builder bounds = new LatLngBounds.Builder();
            for (LatLng item : mListLatLng) {
                bounds.include(item);
            }

            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 12));

            mLocationManager.removeUpdates(mLocationListener);
        }
    }
}