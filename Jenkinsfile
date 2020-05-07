pipeline {
  environment {
    registry = "imt2016072/calculator"
    registryCredential = 'dockerhub'
    dockerImage = ''
    dockerImageLatest = ''
  }
  agent any
  stages {
    stage('Cloning - Git'){
      steps{
        git 'https://github.com/RohithYogi/MavenTestProject.git'
      }
    }
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
              dockerImageLatest = docker.build registry +":latest"
            }
          }
        }
        stage('Push Image') {
          steps{
            script {
              docker.withRegistry( '', registryCredential ) {
                dockerImage.push()
                dockerImageLatest.push()
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
          jobId: "83d9ec96-9797-4bf8-ba37-e3e0573f3d75"])
        }
      }
    }
  }
}
    

