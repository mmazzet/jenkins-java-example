#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    }
    stages {
        stage('Increment version') {
            steps {
                script {
                    echo 'Incrementing App version...'

                    sh "mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit"
                    def matcher = readFile('pom.xml') =~ /<version>(.+)<\/version>/
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }
        stage('Build jar') {
            steps {
                script {
                    echo "Building App..."
                    sh 'mvn clean package'
                }
            }
        }
        stage('Build image') {
            steps {
                script {
                    echo "Building DOCKER image..."
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh "docker build -t objectobjectlady/jenkins-java-example:${IMAGE_NAME} ."
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh "docker push objectobjectlady/jenkins-java-example:${IMAGE_NAME}"
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo "Deploying image App..."
                }
            }
        }
    }
}