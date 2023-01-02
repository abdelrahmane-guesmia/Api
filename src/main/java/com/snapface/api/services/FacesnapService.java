package com.snapface.api.services;

import com.snapface.api.models.Facesnap;
import com.snapface.api.repository.FacesnapRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class FacesnapService {
    @Autowired
    FacesnapRepository facesnapRepository;

    public Optional<Facesnap> getFacesnap(final long id) {
        return facesnapRepository.findById(id);
    }

    public Iterable<Facesnap> getFacesnaps() {
        return facesnapRepository.findAll();
    }

    public void deleteFacesnap(final long id) {
        facesnapRepository.deleteById(id);
    }

    public Facesnap saveFacesnap(Facesnap facesnap) {
        Facesnap savedFacesnap = facesnapRepository.save(facesnap);
        return savedFacesnap;
    }
}
