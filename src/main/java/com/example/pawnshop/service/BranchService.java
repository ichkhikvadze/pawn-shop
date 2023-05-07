package com.example.pawnshop.service;

import com.example.pawnshop.entity.Branch;
import com.example.pawnshop.exception.BranchAlreadyExistsException;
import com.example.pawnshop.exception.BranchNotFoundException;
import com.example.pawnshop.repository.BranchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BranchService {

    private final BranchRepository branchRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Iterable<Branch> findAllBranches() {
        log.info("Find all branches");
        return branchRepository.findAll();
    }

    public Branch createBranch(final Branch branch) {
        log.info(String.format("Create %s", branch));
        Optional<Branch> branchOptional = branchRepository.findById(branch.getId());
        if (branchOptional.isPresent()) {
            String errorMessage = String.format("Branch with id = %s already exists", branch.getId());
            log.error(errorMessage);
            throw new BranchAlreadyExistsException(errorMessage);
        }
        return branchRepository.save(branch);
    }

    public Branch findBranchById(final long id) {
        log.info(String.format("Find branch by id = %s", id));
        Optional<Branch> branchOptional = branchRepository.findById(id);
        if (branchOptional.isEmpty()) {
            String errorMessage = String.format("Branch with id = %s not found", id);
            log.error(errorMessage);
            throw new BranchNotFoundException(errorMessage);
        }
        return branchOptional.get();
    }

    public Branch updateBranch(final long id, final Branch branch) {
        log.info(String.format("Update branch with id = %s to %s", id, branch));
        if (!branchRepository.existsById(id)) {
            String errorMessage = String.format("Branch with id = %s not found", id);
            log.error(errorMessage);
            throw new BranchNotFoundException(errorMessage);
        }
        branch.setId(id);
        return branchRepository.save(branch);
    }

    public Branch deleteBranchById(final long id) {
        log.info(String.format("Delete branch with id = %s", id));
        Optional<Branch> branchOptional = branchRepository.findById(id);
        if (branchOptional.isEmpty()) {
            String errorMessage = String.format("Branch with id = %s not found", id);
            log.error(errorMessage);
            throw new BranchNotFoundException(errorMessage);
        }
        branchRepository.deleteById(id);
        return branchOptional.get();
    }
}
