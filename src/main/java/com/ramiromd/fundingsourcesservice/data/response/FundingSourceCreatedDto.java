package com.ramiromd.fundingsourcesservice.data.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FundingSourceCreatedDto {

    private Long id;

    private String name;

    private String type;

    private LocalDateTime createdAt;
}
