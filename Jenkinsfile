def app
pipeline {
  agent any
  environment {
      AWS_ACCOUNT_ID="337901474843"
      AWS_DEFAULT_REGION="us-east-1"
      IMAGE_REPO_NAME="equitas-it"
      IMAGE_TAG="dept-micro-service"
      // IMAGE_TAG="dept-micro-service_${env.BUILD_ID}"
      REPOSITORY_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}"
  }
  tools { 
        maven 'Maven 3.3.9' 
        jdk 'jdk8' 
  }

  stages {
  
   stage ('Initialize') {
        steps {
            sh '''
                echo "PATH = ${PATH}"
                echo "M2_HOME = ${M2_HOME}"
                echo "sonar_srvr_url = ${sonar_srvr_url}"
                echo "sonar_token = ${sonar_token}"
            ''' 
        }
    }
  
    stage ('Build') {
      steps {
        sh 'mvn clean install'
      }
    }

    stage('Sonarqube') {

      steps {
              //sh 'mvn sonar:sonar -Dsonar.host.url=${sonar_srvr_url} -Dsonar.login=${sonar_token} -Dsonar.qualitygate.wait=true'
               echo 'skipping sonar scan'
      }
    }
   stage('Docker Build') {
      steps {      	
      	script{
      	 app = docker.build("${IMAGE_REPO_NAME}:${IMAGE_TAG}")
      	}
      }
    }

   stage('Logging into AWS ECR') {
        steps {
            script {
              sh "aws ecr get-login-password --region ${AWS_DEFAULT_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com"
            }
        }
   }

       // Uploading Docker images into AWS ECR
   stage('Pushing to ECR') {
    steps{
        script {
               sh "docker tag ${IMAGE_REPO_NAME}:${IMAGE_TAG} ${REPOSITORY_URI}:$IMAGE_TAG"
               sh "docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}:${IMAGE_TAG}"
        }
       }
     }
    
    stage ('Deploy') {
      steps {

       echo 'image deployed test message'
      }
    }
  }
}
