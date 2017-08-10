package com.github.jmetzz.functional.guava.splitAdmission.builder;


import com.github.jmetzz.functional.guava.splitAdmission.pojo.SplitAdmission;

public class SplitAdmissionBuilder {

    private long id;
    private String originLike;
    private String destinationLike;
    private String distributor;
    private String configProfile;
    private String tariffGroup;
    private String splittingStationSequence;

    private SplitAdmissionBuilder(){
        // leave like this ... Initialization-on-demand holder idiom for singleton
    }

    public SplitAdmissionBuilder withId(long id) {
        this.id = id;
        return this;
    }

    private static class LazyHolder {
        private static SplitAdmissionBuilder INSTANCE = new SplitAdmissionBuilder();
    }

    public static SplitAdmissionBuilder getInstance(){
        return LazyHolder.INSTANCE;
    }

    public SplitAdmissionBuilder withOriginLike(String originLike){
        this.originLike = originLike;
        return this;
    }

    public SplitAdmissionBuilder withDestinationLike(String destinationLike){
        this.destinationLike = destinationLike;
        return this;
    }

    public SplitAdmissionBuilder withDistributor(String distributor){
        this.distributor = distributor;
        return this;
    }

    public SplitAdmissionBuilder withConfigProfile(String configProfile){
        this.configProfile = configProfile;
        return this;
    }

    public SplitAdmissionBuilder withTariffGroup(String tariffGroup){
        this.tariffGroup= tariffGroup;
        return this;
    }

    public SplitAdmissionBuilder withsplittingStationSequence(String splittingStationSequence){
        this.splittingStationSequence= splittingStationSequence;
        return this;
    }

    public SplitAdmission createSplitAdmission(){
        return new SplitAdmission(id, originLike, destinationLike, distributor, configProfile, tariffGroup, splittingStationSequence);
    }

}
