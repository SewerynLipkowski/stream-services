package com.backbase.stream.compositions.legalentity.http;

import com.backbase.stream.compositions.legalentity.core.mapper.LegalEntityMapperImpl;
import com.backbase.stream.compositions.legalentity.core.model.LegalEntityIngestResponse;
import com.backbase.stream.compositions.legalentity.core.service.LegalEntityIngestionService;
import com.backbase.stream.compositions.legalentity.model.LegalEntityPullIngestionRequest;
import com.backbase.stream.compositions.legalentity.model.LegalEntityPushIngestionRequest;
import com.backbase.stream.legalentity.model.LegalEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LegalEntityControllerTest {
    @Mock
    LegalEntityMapperImpl mapper;

    @Mock
    LegalEntityIngestionService legalEntityIngestionService;

    LegalEntityController controller;

    @BeforeEach
    void setUp() {
        controller = new LegalEntityController(
                legalEntityIngestionService,
                mapper);
    }

    @Test
    void testPullIngestion_Success() {
        Mono<LegalEntityPullIngestionRequest> requestMono = Mono.just(
                new LegalEntityPullIngestionRequest().withLegalEntityExternalId("externalId"));

        when(legalEntityIngestionService.ingestPull(any())).thenReturn(
                Mono.just(LegalEntityIngestResponse.builder()
                        .legalEntity(new LegalEntity())
                        .build()));

        controller.pullIngestLegalEntity(requestMono, null).block();
        verify(legalEntityIngestionService).ingestPull(any());
    }

    @Test
    void testPushIngestion_Success() {
        Mono<LegalEntityPushIngestionRequest> requestMono = Mono.just(
                new LegalEntityPushIngestionRequest().withLegalEntity(new com.backbase.stream.compositions.legalentity.model.LegalEntity()));

        when(legalEntityIngestionService.ingestPush(any())).thenReturn(
                Mono.just(LegalEntityIngestResponse.builder()
                        .legalEntity(new LegalEntity())
                        .build()));

        controller.pushIngestLegalEntity(requestMono, null).block();
        verify(legalEntityIngestionService).ingestPush(any());
    }
}
