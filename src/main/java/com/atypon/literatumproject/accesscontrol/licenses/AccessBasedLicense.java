package com.atypon.literatumproject.accesscontrol.licenses;

public class AccessBasedLicense implements License {

    private int limit;
    private int count;

    public AccessBasedLicense(int limit, int count) {
        this.limit = limit;
        this.count = count;
    }

    public int getLimit() {
        return limit;
    }

    public int getCount() {
        return count;
    }

    public int getRemainingAccess() {
        return (limit - count);
    }


    public boolean isStillValid() {
        return (count <= limit);
    }


}
