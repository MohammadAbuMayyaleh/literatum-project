package com.atypon.literatumproject.accesscontrol.licenses;

import com.atypon.literatumproject.accesscontrol.util.Pair;
import java.time.ZonedDateTime;

public class PeriodLicense extends TimeBasedLicense {

    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private Pair<String, Integer> period;

    public PeriodLicense(ZonedDateTime startDate, Pair<String, Integer> period) {
        this.startDate = startDate;
        this.period = period;
        if (this.period.getKey().equalsIgnoreCase("years")) {
            endDate = startDate.plusYears(period.getValue());
        } else if (this.period.getKey().equalsIgnoreCase("months")) {
            endDate = startDate.plusMonths(period.getValue());
        } else if (this.period.getKey().equalsIgnoreCase("days")) {
            endDate = startDate.plusDays(period.getValue());
        }

    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public Pair<String, Integer> getPeriod() {
        return period;
    }

    public boolean isStillValid() {
        ZonedDateTime now = ZonedDateTime.now();
        return (now.equals(endDate));
    }

}
