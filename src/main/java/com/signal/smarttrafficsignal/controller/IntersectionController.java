package com.signal.smarttrafficsignal.controller;


//import com.traffic.model.Intersection;
//import com.traffic.repository.IntersectionRepository;
import com.signal.smarttrafficsignal.model.Intersection;
import com.signal.smarttrafficsignal.repository.IntersectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/intersections")
    @RequiredArgsConstructor
    public class IntersectionController {

        private final IntersectionRepository intersectionRepository;

        @PostMapping
        public Intersection createIntersection(@RequestBody Intersection intersection) {
            return intersectionRepository.save(intersection);
        }
    }


