package com.example.pawnshop.controller;

import com.example.pawnshop.entity.Branch;
import com.example.pawnshop.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branches")
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @Operation(summary = "Get all branches")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all branches"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public Iterable<Branch> findAllBranches() {
        return branchService.findAllBranches();
    }

    @Operation(summary = "Create branch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Branch created"),
            @ApiResponse(responseCode = "400", description = "Invalid format"),
            @ApiResponse(responseCode = "409", description = "Branch already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Branch createBranch(@Parameter(description = "Branch") @RequestBody @Valid Branch branch) {
        return branchService.createBranch(branch);
    }

    @Operation(summary = "Find branch by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Branch found"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    @GetMapping("/{id}")
    public Branch findBranchById(@Parameter(description = "Branch id") @PathVariable(name = "id") final long id) {
        return branchService.findBranchById(id);
    }

    @Operation(summary = "Update branch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Branch updated"),
            @ApiResponse(responseCode = "400", description = "Invalid format"),
            @ApiResponse(responseCode = "404", description = "Branch not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    public Branch updateBranch(
            @Parameter(description = "Branch id") @RequestParam final long id,
            @Parameter(description = "Branch") @RequestBody @Valid final Branch branch) {
        return branchService.updateBranch(id, branch);
    }

    @Operation(summary = "Delete branch by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Branch deleted"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping
    public Branch deleteBranchById(@Parameter(description = "Branch id") @RequestParam final long id) {
        return branchService.deleteBranchById(id);
    }
}
