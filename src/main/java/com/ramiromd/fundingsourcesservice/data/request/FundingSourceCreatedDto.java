package com.ramiromd.fundingsourcesservice.data.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FundingSourceCreatedDto {

    private Long id;

    private String name;

    private String type;

    private LocalDateTime createdAt;
}
