package com.ramiromd.fundingsourcesservice.data.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
abstract public class CreateFundingSourceDto {

    /**
     * Funding source custom name ...
     */
    protected String name;
}
