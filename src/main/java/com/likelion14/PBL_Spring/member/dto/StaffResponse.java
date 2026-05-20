package com.likelion14.PBL_Spring.member.dto;

import com.likelion14.PBL_Spring.member.domain.role.Role;
import com.likelion14.PBL_Spring.member.domain.role.Staff;

import javax.swing.text.Position;

public class StaffResponse {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String role;
    private String position;

    public static StaffResponse from(Staff staff) {
        StaffResponse response = new StaffResponse();
        response.name = staff.getName();
        response.major = staff.getMajor();
        response.generation = staff.getGeneration();
        response.part = staff.getPart();
        response.role = staff.roleName();
        response.position = staff.getPosition();
        return response;
    }
    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getGeneration() { return generation; }
    public String getPart() { return part; }
    public String getRole() { return role; }
    public String getPosition() { return position; }
}