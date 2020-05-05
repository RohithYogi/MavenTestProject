pipeline {
  environment {
    registry = "imt2016072/calculator"
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean install'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }
    stage('DockerHub') {
      stages{
        stage('Build Image') {
          steps{
            script {
              dockerImage = docker.build registry + ":$BUILD_NUMBER"
            }
          }
        }
        stage('Push Image') {
          steps{
            script {
              docker.withRegistry( '', registryCredential ) {
                dockerImage.push()
              }
            }
          }
        }
        stage('Deploy Image') {
          agent any
          steps {
            script {
              step([$class: "RundeckNotifier",
              rundeckInstance: "rundeck",
              options: """
                BUILD_VERSION=$BUILD_NUMBER
              """,
              jobId: "941329f8-ef6c-4f1c-8ccc-2cf6dc2727c8"])
            }
          }
        }
      }
    }
  }
}
    

