package com.tus.vehicle_mgmt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping({"/", "/vehicle-management"})
public class ApiGatewayController {

    private final Logger logger = LoggerFactory.getLogger(ApiGatewayController.class);

    @GetMapping
    public ResponseEntity<String> index() {
        try {
            if (isUserAuthenticated() && isUserAuthorized()) {
                // Redirect to the Swagger page
                return ResponseEntity.status(HttpStatus.FOUND)
                        .header("Location", "/swagger-ui/index.html")
                        .build();
            } else {
                // Handle unauthorized access
                return handleUnauthorizedAccess();
            }
        } catch (Exception e) {
            logger.error("Error in index method", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    // Check if the user is authenticated
    private boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    // Check if the user is authorized
    private boolean isUserAuthorized() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }

    // Handle unauthorized access
    private ResponseEntity<String> handleUnauthorizedAccess() {
        // You can customize this response based on your requirements
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access! You do not have permission to access this resource.");
    }
}