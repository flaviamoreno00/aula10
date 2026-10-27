package br.com.aula10.integration;

import br.com.aula10.model.Usuario;
import br.com.aula10.service.Calculadora;
import br.com.aula10.service.QualityGateService;
import br.com.aula10.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Teste de integracao executado pelo perfil Maven -Pintegration-tests (Failsafe Plugin).
 */
class PipelineIntegrationIT {

    @Test
    void deveValidarFluxoIntegradoDeUsuariosCalculosEQualityGate() {
        UserService userService = new UserService();
        userService.carregarUsuarios();

        List<Usuario> usuariosBiz = userService.usuariosComEmailBiz();
        Calculadora calculadora = new Calculadora();
        int dobroUsuariosBiz = calculadora.multiplicar(usuariosBiz.size(), 2);

        assertEquals(6, dobroUsuariosBiz);

        QualityGateService qualityGateService = new QualityGateService();
        assertTrue(qualityGateService.aprovarPullRequestSonar(100.0, 0, 10));
        assertTrue(qualityGateService.autorizarDeploy(true, true, 100.0, 0));
    }
}
