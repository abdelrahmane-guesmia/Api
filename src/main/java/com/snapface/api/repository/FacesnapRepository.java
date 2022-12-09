package com.snapface.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snapface.api.model.Facesnap;

@Repository
public interface FacesnapRepository extends JpaRepository<Facesnap, Long> {
}
