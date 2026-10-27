package br.com.aula10.service;

/**
 * Servico responsavel por validar as regras de Quality Gate (SonarQube) e do Desafio Integrado.
 */
public class QualityGateService {

    private static final double COBERTURA_MINIMA_SONAR = 80.0;
    private static final double COBERTURA_MINIMA_DESAFIO = 85.0;
    private static final int DEBT_MAXIMO_MINUTOS = 60;

    /**
     * Avalia as regras do Quality Gate do SonarQube (Atividade 3):
     * - Cobertura minima: 80%
     * - Zero bugs criticos
     * - Debt tecnico menor que 1h (60 minutos)
     *
     * @param coberturaPercentual percentual de cobertura (0 a 100)
     * @param bugsCriticos        quantidade de bugs criticos
     * @param debtTecnicoMinutos  divida tecnica em minutos
     * @return true se o PR for aprovado no Quality Gate, false se for rejeitado
     */
    public boolean aprovarPullRequestSonar(double coberturaPercentual, int bugsCriticos, int debtTecnicoMinutos) {
        if (coberturaPercentual < COBERTURA_MINIMA_SONAR) {
            return false;
        }
        if (bugsCriticos > 0) {
            return false;
        }
        return debtTecnicoMinutos >= 0 && debtTecnicoMinutos < DEBT_MAXIMO_MINUTOS;
    }

    /**
     * Avalia se o deploy pode ser realizado conforme o Desafio Integrado:
     * - Todos os testes (unitarios e integracao) devem passar
     * - Cobertura > 85%
     * - Zero vulnerabilidades no OWASP Dependency-Check
     *
     * @param testesUnitariosOk    resultado dos testes unitarios
     * @param testesIntegracaoOk   resultado dos testes de integracao
     * @param coberturaPercentual  cobertura de codigo medida pelo JaCoCo
     * @param vulnerabilidadesOwasp total de vulnerabilidades detectadas pelo OWASP Dependency-Check
     * @return true se o deploy estiver autorizado, false caso contrario
     */
    public boolean autorizarDeploy(
            boolean testesUnitariosOk,
            boolean testesIntegracaoOk,
            double coberturaPercentual,
            int vulnerabilidadesOwasp) {
        if (!testesUnitariosOk || !testesIntegracaoOk) {
            return false;
        }
        if (coberturaPercentual <= COBERTURA_MINIMA_DESAFIO) {
            return false;
        }
        return vulnerabilidadesOwasp == 0;
    }
}
