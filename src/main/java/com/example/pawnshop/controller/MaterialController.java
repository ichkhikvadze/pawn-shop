package com.example.pawnshop.controller;

import com.example.pawnshop.entity.Material;
import com.example.pawnshop.service.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materials")
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @Operation(summary = "Get all materials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all materials"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public Iterable<Material> findAllMaterials() {
        return materialService.findAllMaterials();
    }

    @Operation(summary = "Create material")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Material created"),
            @ApiResponse(responseCode = "400", description = "Invalid format"),
            @ApiResponse(responseCode = "409", description = "Material already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Material createMaterial(@Parameter(description = "Material") @RequestBody @Valid final Material material) {
        return materialService.createMaterial(material);
    }

    @Operation(summary = "Find material by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Material found"),
            @ApiResponse(responseCode = "404", description = "Material not found")
    })
    @GetMapping("/{id}")
    public Material findMaterialById(@Parameter(description = "Material id") @PathVariable(name = "id") final long id) {
        return materialService.findMaterialById(id);
    }

    @Operation(summary = "Update material")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Material updated"),
            @ApiResponse(responseCode = "400", description = "Invalid format"),
            @ApiResponse(responseCode = "404", description = "Material not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    public Material updateMaterial(
            @Parameter(description = "Material id") @RequestParam final long id,
            @Parameter(description = "Material") @RequestBody @Valid final Material material) {
        return materialService.updateMaterial(id, material);
    }

    @Operation(summary = "Delete material by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Material deleted"),
            @ApiResponse(responseCode = "404", description = "Material not found")
    })
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping
    public Material deleteMaterialById(@Parameter(description = "Material id") @RequestParam final long id) {
        return materialService.deleteMaterialById(id);
    }
}
