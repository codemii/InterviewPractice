package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class AppConfig {
    private Map<String, Integer> allowedTpsPerApi;

    @Value("${application.allowedtpsperapi}")
    private String apiMapAsString;

    public Map<String, Integer> getAllowedTpsPerApi() {
        this.allowedTpsPerApi = convert(apiMapAsString);
        return allowedTpsPerApi;
    }

    private Map<String, Integer> convert(String source) {
        return Arrays.stream(source.split(","))
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(a -> a[0], a -> Integer.parseInt(a[1])));
    }
}