package com.snapface.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.snapface.api.models.Facesnap;
import com.snapface.api.services.FacesnapService;

import java.util.Date;
import java.util.Optional;

import static com.snapface.api.security.WebSecurityConfig.SECURITY_CONFIG_NAME;

@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Facesnap", description = "Facesnap manipulation")
@RestController
@RequestMapping("/api/facesnap")
@SecurityRequirement(name = SECURITY_CONFIG_NAME)
public class FacesnapController {
    @Autowired
    private FacesnapService facesnapService;

    /**
     * Read - get all snapface
     * @return - An iterable object of snapface fulfilled
     */
    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Iterable<Facesnap> getSnapfaces() {
        return facesnapService.getFacesnaps();
    }

    /**
     * Read - get a snapface
     * @param id - the id of the snapface
     * @return An snapface object fulfilled
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Facesnap getSnapface(@PathVariable("id") final Long id) {
        Optional<Facesnap> facesnap = facesnapService.getFacesnap(id);
        return facesnap.orElse(null);
    }

    /**
     * Create - Add a new snapface
     * @param facesnap - An object snapface
     * @return The snapface object created
     */
    @PostMapping("")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Facesnap createSnapface(@RequestBody Facesnap facesnap) {
        return facesnapService.saveFacesnap(facesnap);
    }

    /**
     * Delete - Delete an facesnap
     * @param id - The id of the facesnap to delete
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public void deleteFacesnap(@PathVariable("id") final Long id) {
        facesnapService.deleteFacesnap(id);
    }

    /**
     * Update - Update an existing facesnap
     * @param id - the id of the facesnap to update
     * @param facesnap - the facesnap object to update
     * @return - Deleted facesnap
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Facesnap updateFacesnap(@PathVariable("id") final Long id, @RequestBody Facesnap facesnap) {
        Optional<Facesnap> f = facesnapService.getFacesnap(id);
        if(f.isPresent()) {
            Facesnap currentFacesnap = f.get();

            String title = facesnap.getTitle();
            if(title != null) {
                currentFacesnap.setTitle(title);
            }
            String description = facesnap.getDescription();
            if(description != null) {
                currentFacesnap.setDescription(description);
            }
            Date createdDate = facesnap.getCreatedDate();
            if(createdDate != null) {
                currentFacesnap.setCreatedDate(createdDate);
            }
            Integer snaps = facesnap.getSnaps();
            if(snaps != null) {
                currentFacesnap.setSnaps(snaps);
            }
            String imageUrl = facesnap.getImageUrl();
            if(imageUrl != null) {
                currentFacesnap.setImageUrl(imageUrl);
            }
            String location = facesnap.getLocation();
            if(location != null) {
                currentFacesnap.setLocation(location);
            }
            facesnapService.saveFacesnap(currentFacesnap);
            return currentFacesnap;
        } else {
            return null;
        }
    }


}
