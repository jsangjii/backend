package com.likelion14.PBL_Spring.member.domain.role;


import com.likelion14.PBL_Spring.member.domain.policy.LionSubmissionPolicy;
import com.likelion14.PBL_Spring.member.domain.policy.SubmissionPolicy;

public class Lion extends Role {

    @Override
    public String getInfo() {
        return "\uD83C\uDF40이름: " + getName() + " | \uD83C\uDF40전공: " + getMajor() +
                " | \uD83C\uDF40기수: " + getGeneration() + " | \uD83C\uDF40파트: " + getPart() +
                "\n \uD83C\uDF40학번: " + studentId;
    }
    @Override
    public String roleName() {return "아기사자";}

    public SubmissionPolicy submissionPolicy() {
        return new LionSubmissionPolicy();
    }
    private String studentId;

    public Lion(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }
}