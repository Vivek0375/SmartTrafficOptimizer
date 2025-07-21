package com.signal.smarttrafficsignal.repository;


import com.signal.smarttrafficsignal.model.Intersection;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface IntersectionRepository extends JpaRepository<Intersection, Long> {
    }

