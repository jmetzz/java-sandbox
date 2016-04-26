package com.github.jmetzz.functional.guava.splitAdmission;


public class SplitAdmissionSpec {

    protected String segmentOrigin;

    protected String segmentDestination;

    protected String configProfile;

    protected String tariffGroup;

    public SplitAdmissionSpec(String segmentOrigin, String segmentDestination, String tariffGroupConfigProfile, String tariffGroup) {
        this.segmentOrigin = segmentOrigin;
        this.segmentDestination = segmentDestination;
        this.configProfile = tariffGroupConfigProfile;
        this.tariffGroup = tariffGroup;
    }

    public String getSegmentOrigin() {
        return segmentOrigin;
    }

    public String getSegmentDestination() {
        return segmentDestination;
    }

    public String getConfigProfile() {
        return configProfile;
    }

    public String getTariffGroup() {
        return tariffGroup;
    }
}
