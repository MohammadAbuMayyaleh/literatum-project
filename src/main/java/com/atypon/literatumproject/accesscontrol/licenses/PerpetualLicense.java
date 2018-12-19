package com.atypon.literatumproject.accesscontrol.licenses;

public class PerpetualLicense extends TimeBasedLicense {

    public boolean isStillValid()
    {
        return true;
    }
}
