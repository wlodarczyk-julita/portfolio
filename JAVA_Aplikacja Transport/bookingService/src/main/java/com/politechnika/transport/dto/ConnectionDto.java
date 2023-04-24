package com.politechnika.transport.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class ConnectionDto {
    @NotEmpty
    @Size(min = 1, max = 100)
    private String trainName;
    private String startStation;
    private String finalStation;
    private int capacity;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime deparature;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime arrive;
    @NotNull
    private double price;
}
