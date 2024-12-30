package com.ai.SpringAiDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenAiController {

    ChartService chartService;

    public GenAiController(ChartService chartService) {
        this.chartService = chartService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chartService.getResponse(prompt);
    }
}
