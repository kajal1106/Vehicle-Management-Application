package com.tus.vehicle_mgmt.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{ownerid}")
    public OwnerDTO getOwnerById(@PathVariable Long ownerid) {
        return ownerService.getOwnerById(ownerid);
    }

    @PostMapping
    public OwnerDTO createOwner(@RequestBody OwnerDTO ownerDTO) {
        return ownerService.createOwner(ownerDTO);
    }

    @PutMapping("/{ownerid}")
    public OwnerDTO updateOwner(@PathVariable Long ownerid, @RequestBody OwnerDTO ownerDTO) {
        return ownerService.updateOwner(ownerid, ownerDTO);
    }

    @DeleteMapping("/{ownerid}")
    public ResponseEntity<String> deleteOwner(@PathVariable Long ownerid) {
        try {
            // Attempt to delete a owner
            boolean deleted = ownerService.deleteOwner(ownerid);
            return (deleted) ?
                    ResponseEntity.ok("Owner deleted successfully") :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
//            e.printStackTrace();
            // Handle exceptions when deleting a owner
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting owner");
        }
    }
    
}
