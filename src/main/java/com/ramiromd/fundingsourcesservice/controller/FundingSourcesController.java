package com.ramiromd.fundingsourcesservice.controller;

import com.ramiromd.fundingsourcesservice.data.request.contract.CreateFundingSourceInterface;
import com.ramiromd.fundingsourcesservice.data.response.FundingSourceCreatedDto;
import com.ramiromd.fundingsourcesservice.service.funding.FundingSourceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sources")
public class FundingSourcesController {

    final private FundingSourceService sourceService;

    public FundingSourcesController(FundingSourceService sourceService) {
        this.sourceService = sourceService;
    }

    @PostMapping(value="/", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public FundingSourceCreatedDto create(
            @RequestBody CreateFundingSourceInterface source,
            @RequestHeader("X-USER-ID") String userId
    ) throws Exception {
        return this.sourceService.create(source, userId);
    }
}
