package co.roverlabs.sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SherryYang on 2015-02-20.
 */
public class RoverCard extends RoverObject {

    //JSON members
    @SerializedName("title") private String mTitle;
    //TODO: View object needs to be added
    
    //TODO: Remove these (used for testing)
    private String mMessage;
    private int mImageResourceId;
    
    //Constructor
    public RoverCard() { mObjectName = "card"; }

    //Getters
    public String getTitle() { return mTitle; }
    //TODO: Remove these (used for testing)
    public String getMessage() { return mMessage; }
    public int getImageResourceId() { return mImageResourceId; }

    //Setters
    public void setTitle(String title) { mTitle = title; }
    //TODO: Remove these (used for testing)
    public void setMessage(String message) { mMessage = message; }
    public void setImageResourceId(int imageResourceId) { mImageResourceId = imageResourceId; }
}
