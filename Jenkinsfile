pipeline{
    agent any
    tools{
        maven "maven"

    }
    stages{
        stage("Build JAR File"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/ByronCaices/autofix-app']])
                dir("autofix-backend"){
                    sh "mvn clean install"
                }
            }
        }
        stage("Test"){
            steps{
                dir("autofix-backend"){
                    sh "mvn test"
                }
            }
        }        
        stage("Build and Push Docker Image"){
            steps{
                dir("autofix-backend"){
                    script{
                         withDockerRegistry(credentialsId: 'docker-credentials'){
                            sh "docker build -t bcaices/spring-image ."
                            sh "docker push bcaices/spring-image"
                        }
                    }                    
                }
            }
        }
    }
}