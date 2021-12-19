package com.backbase.stream.compositions.productcatalog.core.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties("backbase.stream.compositions.product")
public class ProductCatalogConfigurationProperties {
    private Boolean enableCompletedEvents = true;
    private Boolean enableFailedEvents = true;
    private String productCatalogIntegrationUrl = "http://product-catalog-ingestion-integration:8080";
}