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

    public OwnerDTO getOwnerById(Long ownerId) {
        Optional<OwnerDTO> ownerOptional = ownerRepository.findById(ownerId);
        return ownerOptional.orElse(null); // or throw an exception if needed
    }

    public OwnerDTO createOwner(OwnerDTO ownerDTO) {
        // Additional logic if needed
        return ownerRepository.save(ownerDTO);
    }

    public OwnerDTO updateOwner(Long ownerId, OwnerDTO updatedOwnerDTO) {
        // Additional logic if needed
        if (ownerRepository.existsById(ownerId)) {
            updatedOwnerDTO.setId(ownerId);
            return ownerRepository.save(updatedOwnerDTO);
        } else {
            throw new NoSuchElementException("Owner with ID " + ownerId + " not found");
        }
    }

    public void deleteOwner(Long ownerId) {
        // Additional logic if needed
        if (ownerRepository.existsById(ownerId)) {
            ownerRepository.deleteById(ownerId);
        } else {
            throw new NoSuchElementException("Owner with ID " + ownerId + " not found");
        }
    }
}
