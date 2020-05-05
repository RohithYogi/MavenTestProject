pipeline {
  environment {
    registry = "imt2016072/calculator"
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
  agent none
  stages {
    stage('DockerHub') {
      stages{
        stage('Building image') {
          steps{
            script {
              dockerImage = docker.build registry + ":$BUILD_NUMBER"
            }
          }
        }
        stage('Deploy Image') {
          steps{
            script {
              docker.withRegistry( '', registryCredential ) {
                dockerImage.push()
              }
            }
          }
        }
      }
      
    }
  }
}
