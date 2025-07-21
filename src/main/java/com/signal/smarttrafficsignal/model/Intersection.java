package com.signal.smarttrafficsignal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Intersection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Optional free-form address or “lat,lng”
    private String location;

    private String city;

    private boolean active = true;

    // ✅ New fields
    private Double latitude;
    private Double longitude;
}
