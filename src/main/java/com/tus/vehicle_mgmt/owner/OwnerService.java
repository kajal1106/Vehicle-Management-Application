package com.tus.vehicle_mgmt.owner;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<OwnerDTO> getAllOwners() {
        return ownerRepository.findAll();
    }

    public OwnerDTO getOwnerById(Long ownerid) {
        Optional<OwnerDTO> ownerOptional = ownerRepository.findById(ownerid);
        return ownerOptional.orElse(null); // or throw an exception if needed
    }

    public OwnerDTO createOwner(OwnerDTO ownerDTO) {
        // Additional logic if needed
        return ownerRepository.save(ownerDTO);
    }

    public OwnerDTO updateOwner(Long ownerid, OwnerDTO updatedOwnerDTO) {
        // Additional logic if needed
        if (ownerRepository.existsById(ownerid)) {
            updatedOwnerDTO.setId(ownerid);
            return ownerRepository.save(updatedOwnerDTO);
        } else {
            throw new NoSuchElementException("Owner with ID " + ownerid + " not found");
        }
    }

    public boolean deleteOwner(Long ownerid) {
	 if (ownerRepository.existsById(ownerid)) {
		 ownerRepository.deleteById(ownerid);
         return true;
     }
     return false;
    }
}
