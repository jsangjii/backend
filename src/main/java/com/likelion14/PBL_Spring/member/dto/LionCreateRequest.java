package com.likelion14.PBL_Spring.member.dto;

import javax.swing.text.Position;

public class LionCreateRequest {
    String name;
    String major;
    int generation;
    String part;
    String studentId;
    String position;

    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getGeneration() { return generation; }
    public String getPart() { return part; }
    public String getStudentId() { return studentId; }
    public String getPosition() {return position;}
    }