pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
                junit 'target/surefire-reports/**/*.xml'
            }
        }

        stage('PMD Analysis') {
            steps {
                sh 'mvn pmd:check'
            }
        }

        stage('Quality Gate') {
            steps {
                sh 'mvn checkstyle:check jacoco:report jacoco:check'
            }
        }

        stage('Deploy to Staging') {
            when {
                branch 'main'
            }
            steps {
                sh 'docker build -t myapp:${GIT_COMMIT} .'
                sh 'kubectl apply -f k8s/deployment.yaml'
                sh 'kubectl rollout status deployment/myapp --timeout=120s'
            }
            post {
                failure {
                    echo 'Falha detectada no deploy! Executando rollback automatico...'
                    sh 'kubectl rollout undo deployment/myapp'
                }
            }
        }
    }

    post {
        failure {
            slackSend channel: '#alerts', message: "Build Failed: ${env.JOB_NAME}"
        }
    }
}
