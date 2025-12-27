
pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    }
    stages {
        stage('Build jar') {
            steps {
                script {
                    echo "Building App..."
                    sh 'mvn package'
                }
            }
        }
            stage('Build image') {
                steps {
                    script {
                        echo "Building DOCKER image..."
                        withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                            sh 'docker build -t objectobjectlady/jenkins-java-example:jje-2.0 .'
                            sh "echo $PASS | docker login -u $USER --password-stdin"
                            sh 'docker push objectobjectlady/jenkins-java-example:jje-2.0'
                        }
                    }
                }
            }
        stage('deploy') {
            steps {
                script {
                    echo "Deploying App..."
                }
            }
        }
    }
}