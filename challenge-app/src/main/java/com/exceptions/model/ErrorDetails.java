package com.exceptions.model;

import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ErrorDetails {
    private static final String TIMEZONE = "America/Sao_Paulo";
    private String title;
    private int status;
    private String detail;
    @Builder.Default
    private String timestamp = LocalDateTime.now().atZone(ZoneId.of(TIMEZONE)).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    private String developerMessage;
}
