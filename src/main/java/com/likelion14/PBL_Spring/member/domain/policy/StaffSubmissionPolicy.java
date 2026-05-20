package com.likelion14.PBL_Spring.member.domain.policy;

public class StaffSubmissionPolicy implements SubmissionPolicy {

    private int currentCardinal; // 현재 객체의 기수
    private final int minGeneration; // 제출 가능한 최소 기수 기준

    public StaffSubmissionPolicy(int minGeneration) {
        this.minGeneration = minGeneration;
    }

    public void setCardinal(int cardinal) {
        this.currentCardinal = cardinal;
    }

    @Override
    public boolean canSubmit() {
        // 현재 기수가 최소 기준 기수보다 크거나 같을 때만 true 반환
        return currentCardinal >= minGeneration;
    }
}