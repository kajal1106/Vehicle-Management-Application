package com.tus.vehicle_mgmt.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<OwnerDTO> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{ownerId}")
    public OwnerDTO getOwnerById(@PathVariable Long ownerId) {
        return ownerService.getOwnerById(ownerId);
    }

    @PostMapping
    public OwnerDTO addOwner(@RequestBody OwnerDTO ownerDTO) {
        return ownerService.createOwner(ownerDTO);
    }

    @PutMapping("/{ownerId}")
    public OwnerDTO updateOwner(@PathVariable Long ownerId, @RequestBody OwnerDTO ownerDTO) {
        return ownerService.updateOwner(ownerId, ownerDTO);
    }

    @DeleteMapping("/{ownerId}")
    public void deleteOwner(@PathVariable Long ownerId) {
        ownerService.deleteOwner(ownerId);
    }
}
