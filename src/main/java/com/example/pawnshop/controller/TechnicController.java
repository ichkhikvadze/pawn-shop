package com.example.pawnshop.controller;

import com.example.pawnshop.entity.Technic;
import com.example.pawnshop.service.TechnicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/technic")
public class TechnicController {

    private final TechnicService technicService;

    @Autowired
    public TechnicController(TechnicService technicService) {
        this.technicService = technicService;
    }

    @Operation(summary = "Get all technic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all technic"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public Iterable<Technic> findAllTechnic() {
        return technicService.findAllTechnic();
    }

    @Operation(summary = "Create technic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Technic created"),
            @ApiResponse(responseCode = "400", description = "Invalid format"),
            @ApiResponse(responseCode = "409", description = "Technic already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Technic createTechnic(@Parameter(description = "Technic") @RequestBody @Valid Technic technic) {
        return technicService.createTechnic(technic);
    }

    @Operation(summary = "Find technic by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Technic found"),
            @ApiResponse(responseCode = "404", description = "Technic not found")
    })
    @GetMapping("/{id}")
    public Technic findTechnicById(@Parameter(description = "Technic id") @PathVariable(name = "id") final long id) {
        return technicService.findTechnicById(id);
    }

    @Operation(summary = "Update technic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Technic updated"),
            @ApiResponse(responseCode = "400", description = "Invalid format"),
            @ApiResponse(responseCode = "404", description = "Technic not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    public Technic updateTechnic(
            @Parameter(description = "Technic id") @RequestParam final long id,
            @Parameter(description = "Technic") @RequestBody @Valid final Technic technic) {
        return technicService.updateTechnic(id, technic);
    }

    @Operation(summary = "Delete technic by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Technic deleted"),
            @ApiResponse(responseCode = "404", description = "Technic not found")
    })
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping
    public Technic deleteTechnicById(@Parameter(description = "Technic id") @RequestParam final long id) {
        return technicService.deleteTechnicById(id);
    }
}
