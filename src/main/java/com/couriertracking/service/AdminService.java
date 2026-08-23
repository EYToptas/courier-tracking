package com.couriertracking.service;

import com.couriertracking.dto.AdminRequest;
import com.couriertracking.dto.AdminResponse;
import com.couriertracking.exception.ResourceNotFoundException;
import com.couriertracking.mapper.AdminMapper;

import com.couriertracking.model.Admin;

import com.couriertracking.repository.AdminRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AdminService {

    private final AdminRepository adminRepository;
    public AdminResponse create(AdminRequest request){
        Admin admin = Admin.builder()
                .name(request.name())
                .build();
        return AdminMapper.toResponse(adminRepository.save(admin));
    }

    public List<AdminResponse> getAll(){
        return adminRepository.findAll().stream().map(admin -> AdminMapper.toResponse(admin)).toList();
    }

    public AdminResponse getByID(Long id){
        return AdminMapper.toResponse(searchId(id));

    }

    public AdminResponse update(Long id, AdminRequest request){
        Admin admin = searchId(id);
        admin.setName(request.name());
        return AdminMapper.toResponse(adminRepository.save(admin));
    }
    public void delete(Long id) {
        adminRepository.delete(searchId(id));
    }

    private Admin searchId(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));
    }

}
