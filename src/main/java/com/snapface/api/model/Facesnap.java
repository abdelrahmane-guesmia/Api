package com.snapface.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "facesnaps")
public class Facesnap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Column(name = "created_date")
    private Date createdDate;

    private Integer snaps;

    @Column(name = "image_url")
    private String imageUrl;

    private String location;
}
