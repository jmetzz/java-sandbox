package com.github.jmetzz.functional.guava.splitAdmission.pojo;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * Created by exi853 on 18/12/2015.
 */
public class SplitAdmission implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String originLike;
    private String destinationLike;
    private String distributor;
    private String configProfile;
    private String tariffGroup;
    private String splittingStationSequence;


    public SplitAdmission(long id, String originLike, String destinationLike, String distributor, String configProfile, String tariffGroup, String splittingStationSequence) {
        this.id = id;
        this.originLike = originLike;
        this.destinationLike = destinationLike;
        this.distributor = distributor;
        this.configProfile = configProfile;
        this.tariffGroup = tariffGroup;
        this.splittingStationSequence = splittingStationSequence;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginLike() {
        return originLike;
    }

    public void setOriginLike(String originLike) {
        this.originLike = originLike;
    }

    public String getDestinationLike() {
        return destinationLike;
    }

    public void setDestinationLike(String destinationLike) {
        this.destinationLike = destinationLike;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public String getConfigProfile() {
        return configProfile;
    }

    public void setConfigProfile(String configProfile) {
        this.configProfile = configProfile;
    }

    public String getTariffGroup() {
        return tariffGroup;
    }

    public void setTariffGroup(String tariffGroup) {
        this.tariffGroup = tariffGroup;
    }

    public String getSplittingStationSequence() {
        return splittingStationSequence;
    }

    public void setSplittingStationSequence(String splittingStationSequence) {
        this.splittingStationSequence = splittingStationSequence;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("originLike", originLike)
                .add("destinationLike", destinationLike)
                .add("distributor", distributor)
                .add("configProfile", configProfile)
                .add("tariffGroup", tariffGroup)
                .add("splittingStationSequence", splittingStationSequence)
                .omitNullValues()
                .toString();
    }

}
