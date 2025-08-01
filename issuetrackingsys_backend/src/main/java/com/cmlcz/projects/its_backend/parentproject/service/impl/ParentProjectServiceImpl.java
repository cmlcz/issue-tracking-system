package com.cmlcz.projects.its_backend.parentproject.service.impl;

import com.cmlcz.projects.its_backend.common.exception.ResourceNotFoundException;
import com.cmlcz.projects.its_backend.parentproject.dto.ParentProjectCreateDTO;
import com.cmlcz.projects.its_backend.parentproject.dto.ParentProjectResponseDTO;
import com.cmlcz.projects.its_backend.parentproject.dto.ParentProjectUpdateDTO;
import com.cmlcz.projects.its_backend.parentproject.mapper.ParentProjectMapper;
import com.cmlcz.projects.its_backend.parentproject.model.ParentProject;
import com.cmlcz.projects.its_backend.parentproject.repository.ParentProjectRepository;
import com.cmlcz.projects.its_backend.parentproject.service.ParentProjectService;
import com.cmlcz.projects.its_backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ParentProjectServiceImpl implements ParentProjectService {


    private final ParentProjectRepository parentProjectRepository;
    private final UserRepository userRepository;
    private final ParentProjectMapper parentProjectMapper;

    @Autowired
    public ParentProjectServiceImpl(ParentProjectRepository parentProjectRepository, UserRepository userRepository, ParentProjectMapper parentProjectMapper) {
        this.parentProjectRepository = parentProjectRepository;
        this.userRepository = userRepository;
        this.parentProjectMapper = parentProjectMapper;
    }

    @Override
    public ArrayList<ParentProjectResponseDTO> findAll() {


        List<ParentProject> parentProjectList = parentProjectRepository.findAllByOrderByCreationDateAsc();

        ArrayList<ParentProjectResponseDTO> parentProjectResponseDTOList = new ArrayList<>();

        parentProjectList.forEach(parentProject -> parentProjectResponseDTOList.add(parentProjectMapper.toDto(parentProject)));

        return parentProjectResponseDTOList;
    }

    @Override
    public ParentProjectResponseDTO findById(UUID uuid) {

        ParentProject parentProject = parentProjectRepository.findById(uuid).orElseThrow(
                () -> new ResourceNotFoundException("Parent project not found")
        );

        return parentProjectMapper.toDto(parentProject);
    }

    @Override
    public ParentProjectResponseDTO update(UUID uuid, ParentProjectUpdateDTO parentProjectUpdateRequestDTO) {
        ParentProject parentProject = parentProjectRepository.findById(uuid).orElseThrow(
                () -> new ResourceNotFoundException("Parent project not found")
        );

        parentProject.setProjectName(parentProjectUpdateRequestDTO.projectName());
        parentProject.setDescription(parentProjectUpdateRequestDTO.description());

        parentProjectRepository.save(parentProject);

        return parentProjectMapper.toDto(parentProject);

    }

    @Override
    public ParentProjectResponseDTO create(ParentProjectCreateDTO parentProjectCreateRequestDTO) {

        if(!userRepository.existsById(parentProjectCreateRequestDTO.createdById()))
            throw new ResourceNotFoundException("User not found");

        ParentProject parentProject = parentProjectMapper.toEntity(parentProjectCreateRequestDTO);
        parentProjectRepository.save(parentProject);

        return parentProjectMapper.toDto(parentProject);
    }

    @Override
    public boolean deleteById(UUID uuid) {
        ParentProject project = parentProjectRepository.findById(uuid).orElseThrow(
                () -> new ResourceNotFoundException("Parent project not found")
        );

        parentProjectRepository.delete(project);

        return true;
    }
}
