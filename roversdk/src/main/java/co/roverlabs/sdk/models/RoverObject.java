package co.roverlabs.sdk.models;

import android.content.Context;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import co.roverlabs.sdk.managers.RoverNotificationManager;
import co.roverlabs.sdk.networks.RoverNetworkListener;
import co.roverlabs.sdk.networks.RoverNetworkManager;

/**
 * Created by SherryYang on 2015-02-20.
 */
public abstract class RoverObject {

    //JSON members
    @SerializedName("id") protected String mId;
    
    //Local members
    private static final String TAG = RoverObject.class.getName();
    protected String mObjectName;
    protected transient RoverNetworkManager mNetworkManager;
    //TODO: Best place to put the notification manager?
    protected transient RoverNotificationManager mNotificationManager;
    
    //Constructor
    public RoverObject(Context con) { 
        
        mNotificationManager = new RoverNotificationManager(con);
        mNetworkManager = new RoverNetworkManager(); 
    }
    
    //Getters
    public String getId() { return mId; }
    public String getObjectName() { return mObjectName; }
    
    //Setters
    public void setId(String id) { mId = id; }

    
    public void save() {
        
        String method;
        final RoverObject self = this;
        
        if(mId == null) {
            method = "POST";
        }
        else {
            method = "PUT";
        }
        
        //TODO: Should network callback be here or in RoverVisitManager?
        mNetworkManager.setNetworkListener(new RoverNetworkListener() {
            
            @Override
            public void onSuccess(RoverObject object) {
                
                self.update(object);
                Log.d(TAG, "Network call from " + self.getObjectName() + " ID " + self.getId() + " has succeeded");
            }

            @Override
            public void onFailure() {
                
                Log.d(TAG, "Network call from " + self.getObjectName() + " ID " + self.getId() + " has failed");
            }
        });
        
        mNetworkManager.sendRequest(method, this);
    }
    
    public void update(RoverObject object) {
        
        mId = object.getId();
    }
}
