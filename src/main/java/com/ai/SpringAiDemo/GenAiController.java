package com.ai.SpringAiDemo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class GenAiController {

    private final ChartService chartService;
    private final ImageService imageService;

    public GenAiController(ChartService chartService, ImageService imageService) {
        this.chartService = chartService;
        this.imageService = imageService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chartService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt) {
        return chartService.getResponseOptions(prompt);
    }

    // Note: URLs are only valid for 60 minutes after the image has been generated.

    @GetMapping("generate-image")
    public void generateImage(HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt);
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        response.sendRedirect(imageUrl);
    }

    @GetMapping("generate-multiple-image")
    public List<String> generateMultipleImages(HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse = imageService.generateMultipleImage(prompt);

        // Streams to get urls from imageResponse
        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();

        return imageUrls;
    }

    @GetMapping("generate-image-with-options")
    public List<String> generateImageWithOptions(HttpServletResponse response,
                              @RequestParam String prompt,
                              @RequestParam(defaultValue = "hd") String quality,
                              @RequestParam(defaultValue = "1") int n,
                              @RequestParam(defaultValue = "1024") int width,
                              @RequestParam(defaultValue = "1024") int height
                              ) throws IOException {
        ImageResponse imageResponse = imageService.generateImageWithOptions(prompt, quality, n, width, height );
        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();

        return imageUrls;
    }
}
