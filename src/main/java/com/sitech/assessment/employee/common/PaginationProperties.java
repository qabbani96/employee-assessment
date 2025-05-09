package com.sitech.assessment.employee.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pagination")
@Data
public class PaginationProperties {
    private int size;
}
