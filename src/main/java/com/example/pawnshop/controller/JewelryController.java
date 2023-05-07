package com.example.pawnshop.controller;

import com.example.pawnshop.entity.Jewelry;
import com.example.pawnshop.service.JewelryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jewelry")
public class JewelryController {

    private final JewelryService jewelryService;

    @Autowired
    public JewelryController(JewelryService jewelryService) {
        this.jewelryService = jewelryService;
    }

    @Operation(summary = "Get all jewelry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all jewelry"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public Iterable<Jewelry> findAllJewelry() {
        return jewelryService.findAllJewelry();
    }

    @Operation(summary = "Create jewelry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Jewelry created"),
            @ApiResponse(responseCode = "400", description = "Invalid format"),
            @ApiResponse(responseCode = "409", description = "Jewelry already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Jewelry createJewelry(@Parameter(description = "Jewelry") @RequestBody @Valid Jewelry jewelry) {
        return jewelryService.createJewelry(jewelry);
    }

    @Operation(summary = "Find jewelry by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jewelry found"),
            @ApiResponse(responseCode = "404", description = "Jewelry not found")
    })
    @GetMapping("/{id}")
    public Jewelry findJewelryById(@Parameter(description = "Jewelry id") @PathVariable(name = "id") final long id) {
        return jewelryService.findJewelryById(id);
    }

    @Operation(summary = "Update jewelry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jewelry updated"),
            @ApiResponse(responseCode = "400", description = "Invalid format"),
            @ApiResponse(responseCode = "404", description = "Jewelry not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    public Jewelry updateJewelry(
            @Parameter(description = "Jewelry id") @RequestParam final long id,
            @Parameter(description = "Jewelry") @RequestBody @Valid final Jewelry jewelry) {
        return jewelryService.updateJewelry(id, jewelry);
    }

    @Operation(summary = "Delete jewelry by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Jewelry deleted"),
            @ApiResponse(responseCode = "404", description = "Jewelry not found")
    })
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping
    public Jewelry deleteJewelryById(@Parameter(description = "Jewelry id") @RequestParam final long id) {
        return jewelryService.deleteJewelryById(id);
    }
}
