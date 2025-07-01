package com.shaunstudies12.gmail.com.SpringProject.services;

import com.shaunstudies12.gmail.com.SpringProject.dto.EmployeeDTO;
import com.shaunstudies12.gmail.com.SpringProject.entities.EmployeeEntity;
import com.shaunstudies12.gmail.com.SpringProject.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    final EmployeeRepository employeeRepository;
    final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }
    //model mapper convers this to type EmployeeDTO automatically we dont have to manually return all tthe fields

    public EmployeeDTO getEmployeeById(Long id){
        EmployeeEntity employeeEntity=employeeRepository.getById(id);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    //model mapper convers this to type EmployeeDTO automatically we dont have to manually return all tthe fields
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO){
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);

    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> list = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeRepository
                .findAll()) {
            EmployeeDTO map = modelMapper.map(employeeEntity, EmployeeDTO.class);
            list.add(map);
        }
        return list;

        //opt 2 use stream
        /**
         return employeeRepository
         .findAll()
         .stream()
         .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
         .collect(Collectors.toList());
         */


    }

    public boolean deleteEmployeeById(Long id) {
        boolean isPresent = employeeRepository.existsById(id);
        if (isPresent) {
            employeeRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedDTO) {
        EmployeeEntity existingEntity = employeeRepository.findById(id).orElseThrow();
        // Map fields from DTO into existing entity
        modelMapper.map(updatedDTO, existingEntity);
        // Save and return updated DTO
        return modelMapper.map(employeeRepository.save(existingEntity), EmployeeDTO.class);
    }
}
