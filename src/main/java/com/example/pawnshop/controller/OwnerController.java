package com.example.pawnshop.controller;

import com.example.pawnshop.entity.Owner;
import com.example.pawnshop.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Operation(summary = "Get all owners")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all owners"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public Iterable<Owner> findAllOwners() {
        return ownerService.findAllOwners();
    }

    @Operation(summary = "Create owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created"),
            @ApiResponse(responseCode = "400", description = "Invalid format"),
            @ApiResponse(responseCode = "409", description = "Owner with personal no already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Owner createOwner(@Parameter(description = "Owner") @RequestBody @Valid Owner owner) {
        return ownerService.createOwner(owner);
    }

    @Operation(summary = "Find owner by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner found"),
            @ApiResponse(responseCode = "404", description = "Owner not found")
    })
    @GetMapping("/{id}")
    public Owner findOwnerById(@Parameter(description = "Owner id") @PathVariable(name = "id") final long id) {
        return ownerService.findOwnerById(id);
    }

    @Operation(summary = "Update owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner updated"),
            @ApiResponse(responseCode = "400", description = "Invalid format"),
            @ApiResponse(responseCode = "404", description = "Owner not found"),
            @ApiResponse(responseCode = "409", description = "Owner with personal no already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    public Owner updateOwner(
            @Parameter(description = "Owner id") @RequestParam final long id,
            @Parameter(description = "Owner") @RequestBody @Valid final Owner owner) {
        return ownerService.updateOwner(id, owner);
    }

    @Operation(summary = "Delete owner by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Owner deleted"),
            @ApiResponse(responseCode = "404", description = "Owner not found")
    })
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping
    public Owner deleteOwnerById(@Parameter(description = "Owner id") @RequestParam final long id) {
        return ownerService.deleteOwnerById(id);
    }
}
