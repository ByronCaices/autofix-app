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
                    bash "mvn clean install"
                }
            }
        }
        stage("Test"){
            steps{
                dir("autofix-backend"){
                    bash "mvn test"
                }
            }
        }        
        stage("Build and Push Docker Image"){
            steps{
                dir("autofix-backend"){
                    script{
                         withDockerRegistry(credentialsId: 'docker-credentials'){
                            bash "docker build -t bcaices/spring-image ."
                            bash "docker push bcaices/spring-image"
                        }
                    }                    
                }
            }
        }
    }
}