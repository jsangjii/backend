package com.likelion14.PBL_Spring.member.domain.role;


import com.likelion14.PBL_Spring.member.domain.policy.StaffSubmissionPolicy;
import com.likelion14.PBL_Spring.member.domain.policy.SubmissionPolicy;
import com.likelion14.PBL_Spring.member.dto.StaffCreateRequest;
import org.springframework.http.ResponseEntity;

public class Staff extends Role {

    private String position;
    private StaffSubmissionPolicy staffPolicy;

    public Staff(String name, String major, int cardinal, String part, String position) {
        super(name, major, cardinal, part);
        this.position = position;
        this.staffPolicy = new StaffSubmissionPolicy(0); // 초기값
    }

    @Override
    public String getInfo() {
        return "🌿이름: " + getName() + "| 🌿전공: " + getMajor() +
                "| 🌿기수: " + getGeneration() + "| 🌿파트: " + getPart() +
                "\n 🌿직책: " + position;
    }

    @Override
    public String roleName() {
        return "운영진";
    }

    @Override
    public SubmissionPolicy submissionPolicy() {
        return staffPolicy;  }

    public String getPosition() {
        return position;
    }

    public void updatePolicy(int minGeneration) {
        this.staffPolicy = new StaffSubmissionPolicy(minGeneration);
    }

}