package com.ramiromd.fundingsourcesservice.util.helper;

import java.util.Arrays;
import java.util.List;

public class CreditCardNumbers {

    public static List<String> getVisaNumbers() {
        String[] list = {
                "4338-4049-5199-7724",
                "4763-4012-0554-7978",
                "4336-5157-4152-5269",
                "4853-1245-4416-8790",
                "4919-3750-3466-2740"
        };

        return Arrays.asList(list);
    }

    public static List<String> getMasterCardNumbers() {
        String[] list = {
                "5438-2501-6476-9202",
                "5563-1503-9875-3971",
        };

        return Arrays.asList(list);
    }

    public static List<String> getAmexNumbers() {
        String[] list = {
                "3428-3071-3218-6112",
                "3429-5559-9120-4991",
                "3494-7543-1182-3305",
        };

        return Arrays.asList(list);
    }

}
