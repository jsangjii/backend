package com.likelion14.PBL_Spring.member.domain.role;

import com.likelion14.PBL_Spring.member.domain.policy.LionSubmissionPolicy;
import com.likelion14.PBL_Spring.member.domain.policy.SubmissionPolicy;

public abstract class Role {
    public abstract String getInfo();
    public abstract String roleName();
    public abstract com.likelion14.PBL_Spring.member.domain.policy.SubmissionPolicy submissionPolicy();


    public String getRoleName() {
        return roleName();
    }

    public boolean canSubmit() {
        return submissionPolicy().canSubmit();
    }

    private String name;
    private String major;
    private int generation;
    private String part;

    public Role(String name, String major, int cardinal, String part) {
        this.name = name;
        this.major = major;
        this.generation = cardinal;
        this.part = part;
    }

    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getGeneration() { return generation; }
    public String getPart() { return part; }


}