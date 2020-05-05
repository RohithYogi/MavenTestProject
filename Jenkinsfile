pipeline {
  environment {
    registry = "imt2016072/calculator"
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
  agent any
  stages {
    stage('CI - Maven') {
      agent {
        docker {
          image 'maven:3.6.3-jdk-11'
          args '-v /root/.m2:/root/.m2' 
        }
      }
      stages {
        stage('Build') {
          steps {
            sh 'mvn -B -DskipTests clean package'
          }
        }
        stage('Test') {
          steps {
            sh 'mvn test'
          }
           post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
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
      }
    }
  }
}
    

