package com.likelion14.PBL_Spring.member.service;

import com.likelion14.PBL_Spring.member.domain.role.Staff;
import com.likelion14.PBL_Spring.member.dto.LionUpdateRequest;
import com.likelion14.PBL_Spring.member.dto.StaffCreateRequest;
import com.likelion14.PBL_Spring.member.dto.StaffUpdateRequest;
import org.springframework.stereotype.Service;
import com.likelion14.PBL_Spring.member.domain.role.Lion;
import com.likelion14.PBL_Spring.member.domain.role.Role;
import com.likelion14.PBL_Spring.member.dto.LionCreateRequest;
import com.likelion14.PBL_Spring.member.repository.MemberRepository;
import java.util.List;

@Service
public class MemberService {
    // 인터페이스에 의존 (구현체에 의존하지 않음)
    private final MemberRepository repository;


    public MemberService(MemberRepository repository) {this.repository = repository;}

    public boolean registerMember(Role member) {
        if (repository.existsByName(member.getName())) {
            return false;
        }
        repository.save(member);
        return true;
    }

    public Role searchByName(String name) {return repository.findByName(name);}

    public List<Role> getAllMembers() {return repository.findAll();}

    public boolean isEmpty() {return repository.findAll().isEmpty();}

    public Role createLion(LionCreateRequest request) {
        Lion lion = new Lion(request.getName(), request.getMajor(),
                request.getGeneration(),request.getPart(), request.getStudentId());
        if(repository.existsByName(request.getStudentId())){
            return null;
        }
        repository.save(lion);
        return lion;
    }
    public Role createStaff(StaffCreateRequest request) {
        Staff staff = new Staff(request.getName(), request.getMajor(),
                request.getGeneration(), request.getPart(), request.getPosition());
        if (repository.existsByName(request.getName())) {
            return null;
        }
        repository.save(staff);
        return staff;
    }
    public Role updateLion(String name, LionUpdateRequest request) {
        if (repository.findByName(name) == null) {
            return null;
        }

        Lion updated = new Lion(name, request.getMajor(),
                request.getGeneration(), request.getPart(), request.getStudentId());
        repository.updateByName(name, updated);
        return updated;
    }
    public Role updateStaff(String name, StaffUpdateRequest request) {
        if (repository.findByName(name) == null) {
            return null;
        }

        Staff updated = new Staff(name, request.getMajor(),
                request.getGeneration(), request.getPart(), request.getPosition());
        repository.updateByName(name, updated);
        return updated;
    }
    public boolean deleteMember(String name) {
        return repository.deleteMember(name);
    }

}

