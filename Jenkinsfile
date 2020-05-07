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
    stage('Deploy') {
      agent any
      steps {
        script {
          step([$class: "RundeckNotifier",
          rundeckInstance: "rundeck",
          options: """
            BUILD_VERSION=$BUILD_NUMBER
          """,
          jobId: "38084a7b-de6d-4fb6-b3b3-aafa8e6587ba"])
        }
      }
    }
  }
}
    

