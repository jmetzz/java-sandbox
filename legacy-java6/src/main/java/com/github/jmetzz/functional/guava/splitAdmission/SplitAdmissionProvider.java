package com.github.jmetzz.functional.guava.splitAdmission;

import com.github.jmetzz.functional.guava.splitAdmission.pojo.SplitAdmission;

import java.util.List;

public class SplitAdmissionProvider {

    private List<SplitAdmission> splitAdmissions;

    public SplitAdmissionProvider(List<SplitAdmission> splitAdmissions){
        this.splitAdmissions = splitAdmissions;
    }

    public List<SplitAdmission> getSplitAdmissions() {
        return splitAdmissions;
    }
}
