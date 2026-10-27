package br.com.aula10.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QualityGateServiceTest {

    private final QualityGateService qualityGateService = new QualityGateService();

    @Test
    void deveAprovarPullRequestQuandoCumprirRegrasSonarQube() {
        assertTrue(qualityGateService.aprovarPullRequestSonar(85.0, 0, 30));
        assertTrue(qualityGateService.aprovarPullRequestSonar(80.0, 0, 0));
    }

    @Test
    void deveRejeitarPullRequestComBaixaCoberturaBugsCriticosOuDebtAlto() {
        assertFalse(qualityGateService.aprovarPullRequestSonar(79.9, 0, 20));
        assertFalse(qualityGateService.aprovarPullRequestSonar(90.0, 1, 20));
        assertFalse(qualityGateService.aprovarPullRequestSonar(90.0, 0, 60));
        assertFalse(qualityGateService.aprovarPullRequestSonar(90.0, 0, -5));
    }

    @Test
    void deveAutorizarDeployNoDesafioIntegradoApenasQuandoTodosCriteriosPassarem() {
        assertTrue(qualityGateService.autorizarDeploy(true, true, 92.5, 0));

        assertFalse(qualityGateService.autorizarDeploy(false, true, 92.5, 0));
        assertFalse(qualityGateService.autorizarDeploy(true, false, 92.5, 0));
        assertFalse(qualityGateService.autorizarDeploy(true, true, 85.0, 0));
        assertFalse(qualityGateService.autorizarDeploy(true, true, 75.0, 0));
        assertFalse(qualityGateService.autorizarDeploy(true, true, 95.0, 1));
    }
}
