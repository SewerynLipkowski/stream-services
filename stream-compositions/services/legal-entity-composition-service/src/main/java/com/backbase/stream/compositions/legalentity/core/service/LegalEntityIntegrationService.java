package com.backbase.stream.compositions.legalentity.core.service;

import com.backbase.stream.compositions.integration.legalentity.api.LegalEntityIntegrationApi;
import com.backbase.stream.compositions.integration.legalentity.model.GetLegalEntityRequest;
import com.backbase.stream.compositions.integration.legalentity.model.LegalEntity;
import com.backbase.stream.compositions.legalentity.core.model.LegalEntityIngestPullRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@AllArgsConstructor
public class LegalEntityIntegrationService {
    private final LegalEntityIntegrationApi legalEntityIntegrationApi;

    public Flux<LegalEntity> retrieveLegalEntities(LegalEntityIngestPullRequest ingestPullRequest) {
        return legalEntityIntegrationApi.getLegalEntities(prepareRequest(ingestPullRequest));
    }

    private GetLegalEntityRequest prepareRequest(LegalEntityIngestPullRequest ingestPullRequest) {
        return new GetLegalEntityRequest()
                .externalId(ingestPullRequest.getLegalEntityExternalId());
    }
}