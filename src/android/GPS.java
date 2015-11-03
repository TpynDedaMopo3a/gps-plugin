package com.etrans.cordova.plugin.gps;

import org.apache.cordova.CordovaActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.*;
import android.location.*;
import android.net.Uri;
import android.text.Html;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.PluginResult;

public class GPS extends CordovaPlugin {

    private Context context;
    private LocationManager locationManager;
    private CordovaActivity cordovaActivity;

    public GPS() {
        context = this.cordova.getActivity().getApplicationContext();
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        cordovaActivity = (CordovaActivity) this.cordova.getActivity();
    }

    private CallbackContext onNewIntentCallbackContext = null;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
//        try {

        if (action.equals("isGPSEnabled")) {
            actionIsEnableGPS(args, callbackContext);
            return true;
        } else if (action.equals("gotoGPSSettings")) {
            actionGotoGPSSettings(args, callbackContext);
            return true;
        } else if (action.equals("checkGPS")) {
            actionCheckGPS(args, callbackContext);
            return true;
        }


//            if (action.equals("startActivity")) {
//                if (args.length() != 1) {
//                    //return new PluginResult(PluginResult.Status.INVALID_ACTION);
//                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
//                    return false;
//                }
//
//                // Parse the arguments
//				final CordovaResourceApi resourceApi = webView.getResourceApi();
//                JSONObject obj = args.getJSONObject(0);
//                String type = obj.has("type") ? obj.getString("type") : null;
//                Uri uri = obj.has("url") ? resourceApi.remapUri(Uri.parse(obj.getString("url"))) : null;
//                JSONObject extras = obj.has("extras") ? obj.getJSONObject("extras") : null;
//                Map<String, String> extrasMap = new HashMap<String, String>();
//
//                // Populate the extras if any exist
//                if (extras != null) {
//                    JSONArray extraNames = extras.names();
//                    for (int i = 0; i < extraNames.length(); i++) {
//                        String key = extraNames.getString(i);
//                        String value = extras.getString(key);
//                        extrasMap.put(key, value);
//                    }
//                }
//
//                startActivity(obj.getString("action"), uri, type, extrasMap);
//                //return new PluginResult(PluginResult.Status.OK);
//                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
//                return true;
//
//            } else if (action.equals("hasExtra")) {
//                if (args.length() != 1) {
//                    //return new PluginResult(PluginResult.Status.INVALID_ACTION);
//                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
//                    return false;
//                }
//                Intent i = ((CordovaActivity)this.cordova.getActivity()).getIntent();
//                String extraName = args.getString(0);
//                //return new PluginResult(PluginResult.Status.OK, i.hasExtra(extraName));
//                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, i.hasExtra(extraName)));
//                return true;
//
//            } else if (action.equals("getExtra")) {
//                if (args.length() != 1) {
//                    //return new PluginResult(PluginResult.Status.INVALID_ACTION);
//                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
//                    return false;
//                }
//                Intent i = ((CordovaActivity)this.cordova.getActivity()).getIntent();
//                String extraName = args.getString(0);
//                if (i.hasExtra(extraName)) {
//                    //return new PluginResult(PluginResult.Status.OK, i.getStringExtra(extraName));
//                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, i.getStringExtra(extraName)));
//                    return true;
//                } else {
//                    //return new PluginResult(PluginResult.Status.ERROR);
//                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
//                    return false;
//                }
//            } else if (action.equals("getUri")) {
//                if (args.length() != 0) {
//                    //return new PluginResult(PluginResult.Status.INVALID_ACTION);
//                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
//                    return false;
//                }
//
//                Intent i = ((CordovaActivity)this.cordova.getActivity()).getIntent();
//                String uri = i.getDataString();
//                //return new PluginResult(PluginResult.Status.OK, uri);
//                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, uri));
//                return true;
//            } else if (action.equals("onNewIntent")) {
//            	//save reference to the callback; will be called on "new intent" events
//                this.onNewIntentCallbackContext = callbackContext;
//
//                if (args.length() != 0) {
//                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
//                    return false;
//                }
//
//                PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
//                result.setKeepCallback(true); //re-use the callback on intent events
//                callbackContext.sendPluginResult(result);
//                return true;
//                //return result;
//            } else if (action.equals("sendBroadcast"))
//            {
//                if (args.length() != 1) {
//                    //return new PluginResult(PluginResult.Status.INVALID_ACTION);
//                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
//                    return false;
//                }
//
//                // Parse the arguments
//                JSONObject obj = args.getJSONObject(0);
//
//                JSONObject extras = obj.has("extras") ? obj.getJSONObject("extras") : null;
//                Map<String, String> extrasMap = new HashMap<String, String>();
//
//                // Populate the extras if any exist
//                if (extras != null) {
//                    JSONArray extraNames = extras.names();
//                    for (int i = 0; i < extraNames.length(); i++) {
//                        String key = extraNames.getString(i);
//                        String value = extras.getString(key);
//                        extrasMap.put(key, value);
//                    }
//                }
//
//                sendBroadcast(obj.getString("action"), extrasMap);
//                //return new PluginResult(PluginResult.Status.OK);
//                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
//                return true;
//            }

        //return new PluginResult(PluginResult.Status.INVALID_ACTION);
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
        return false;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            String errorMessage=e.getMessage();
//            //return new PluginResult(PluginResult.Status.JSON_EXCEPTION);
//            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION,errorMessage));
//            return false;
//        }
    }
//
//    public static void displayPromptForEnablingGPS(final Activity activity) {
//
//        final AlertDialog.Builder builder =  new AlertDialog.Builder(activity);
//        final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
//        final String message = "Do you want open GPS setting?";
//
//        builder.setMessage(message)
//                .setPositiveButton("OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface d, int id) {
//                                activity.startActivity(new Intent(action));
//                                d.dismiss();
//                            }
//                        })
//                .setNegativeButton("Cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface d, int id) {
//                                d.cancel();
//                            }
//                        });
//        builder.create().show();
//    }

    @Override
    public void onNewIntent(Intent intent) {
        if (this.onNewIntentCallbackContext != null) {
            PluginResult result = new PluginResult(PluginResult.Status.OK, intent.getDataString());
            result.setKeepCallback(true);
            this.onNewIntentCallbackContext.sendPluginResult(result);
        }
    }


    private void actionIsEnableGPS(JSONArray args, CallbackContext callbackContext) {
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, isGPSEnabled()));
    }

    private void actionGotoGPSSettings(JSONArray args, CallbackContext callbackContext) {
        showAlertDialog();
    }

    private void actionCheckGPS(JSONArray args, CallbackContext callbackContext) {
        if (!isGPSEnabled()) {
            showAlertDialog();
        }
    }

    private boolean isGPSEnabled() {
        try {
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            return false;
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Location Services Not Active");
        builder.setMessage("Please turn on GPS in High Accuracy mode in order to use application.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                gotoGPSSettings();
                closeApplication();
            }
        });
        Dialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    private void gotoGPSSettings() {
        cordovaActivity.startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
    }

    private void closeApplication() {
        System.exit(0);
    }

}
