package com.signal.smarttrafficsignal.controller;

import com.signal.smarttrafficsignal.dto.LiveSignalHistoryDTO;
import com.signal.smarttrafficsignal.dto.SignalTimingDTO;
import com.signal.smarttrafficsignal.dto.TrafficDataDTO;
import com.signal.smarttrafficsignal.model.Intersection;
import com.signal.smarttrafficsignal.model.SignalTimingHistory;
import com.signal.smarttrafficsignal.repository.IntersectionRepository;
import com.signal.smarttrafficsignal.repository.SignalTimingHistoryRepository;
import com.signal.smarttrafficsignal.repository.SignalTimingRepository;
import com.signal.smarttrafficsignal.repository.TrafficDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    @Autowired
    private SignalTimingHistoryRepository historyRepository;

    private final IntersectionRepository intersectionRepository;
    private final TrafficDataRepository trafficDataRepository;
    private final SignalTimingRepository signalTimingRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Intersection> intersections = intersectionRepository.findAll();
        List<Map<String, Object>> dashboardData = new ArrayList<>();

        for (Intersection i : intersections) {
            Optional<TrafficDataDTO> trafficOpt = trafficDataRepository
                    .findTopByIntersectionOrderByTimestampDesc(i)
                    .map(data -> new TrafficDataDTO(i.getName(), data.getCongestionLevel(), data.getTimestamp().toString()));

            Optional<SignalTimingDTO> signalOpt = signalTimingRepository
                    .findByIntersection(i)
                    .map(sig -> new SignalTimingDTO(i.getName(), sig.getGreenTime(), sig.getRedTime(), sig.getYellowTime(), sig.getLastUpdated().toString()));

            Map<String, Object> entry = new HashMap<>();
            entry.put("id", i.getId());
            entry.put("intersection", i.getName());
            entry.put("latitude", i.getLatitude());
            entry.put("longitude", i.getLongitude());
            entry.put("congestion", trafficOpt.map(TrafficDataDTO::getCongestionLevel).orElse(-1));
            entry.put("green", signalOpt.map(SignalTimingDTO::getGreenTime).orElse(0));
            entry.put("red", signalOpt.map(SignalTimingDTO::getRedTime).orElse(0));
            entry.put("yellow", signalOpt.map(SignalTimingDTO::getYellowTime).orElse(0));

            dashboardData.add(entry);
        }

        model.addAttribute("dashboardData", dashboardData);
        return "dashboard";
    }

    @GetMapping("/signal-history-dashboard/{id}")
    public String viewSignalHistory(
            @PathVariable Long id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Intersection intersection = intersectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intersection not found"));

        LocalDateTime startDateTime = (startDate != null) ? startDate.atStartOfDay() : LocalDate.MIN.atStartOfDay();
        LocalDateTime endDateTime = (endDate != null) ? endDate.atTime(23, 59, 59) : LocalDateTime.now();

        Pageable pageable = PageRequest.of(page, 10, Sort.by("recordedAt").descending());
        Page<SignalTimingHistory> pageData = historyRepository.findByIntersectionAndRecordedAtBetween(intersection, startDateTime, endDateTime, pageable);

        model.addAttribute("intersection", intersection);
        model.addAttribute("historyList", pageData.getContent());
        model.addAttribute("totalPages", pageData.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "signal-history";
    }

    @GetMapping("/api/live-signal-history/{id}")
    @ResponseBody
    public List<LiveSignalHistoryDTO> getLiveHistory(@PathVariable Long id) {
        Intersection intersection = intersectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intersection not found"));

        List<SignalTimingHistory> last10 = historyRepository
                .findTop10ByIntersectionOrderByRecordedAtDesc(intersection);

        Collections.reverse(last10);

        return last10.stream()
                .map(h -> new LiveSignalHistoryDTO(
                        h.getRecordedAt(),
                        h.getGreenTime(),
                        h.getRedTime(),
                        h.getYellowTime(),
                        h.getReason() // Ensure LiveSignalHistoryDTO has this
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/map-view")
    public String showMap(Model model) {
        List<Intersection> intersections = intersectionRepository.findAll();
        model.addAttribute("intersections", intersections);
        return "maps-view";
    }
}
