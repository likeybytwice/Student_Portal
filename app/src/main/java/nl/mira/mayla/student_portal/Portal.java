package nl.mira.mayla.student_portal;

import android.os.Parcel;
import android.os.Parcelable;

public class Portal {

    //variables
    private String mPortalText;
    private String portalUrl;

    //constructor
    public Portal(String mPortalText, String portalUrl) {
        this.mPortalText = mPortalText;
        this.portalUrl = portalUrl;
    }

    public String getmPortalText() {
        return mPortalText;
    }

    public String getPortalUrl() {
        return portalUrl;
    }

    @Override
    public String toString() {
        return mPortalText;
    }

}
