#!/usr/bin/env groovy
library identifier: 'jenkins-shared-lib@mainline', retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/mmazzet/jenkins-shared-lib.git',
    credentialsId: 'github-token'
    ]
)

def gv

pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    }
    environment{
        IMAGE_NAME = 'objectobjectlady/jenkins-java-example:jje-3.9'
    }
    stages {
        stage('BUILD JAR') {
            steps {
                script {
                echo 'building application jar'
                    buildJar()
                }
            }
        }
        stage('BUILD AND PUSH IMAGE') {
            steps {
                script {
                echo 'building docker image...'
                    buildImage (env.IMAGE_NAME)
                    dockerLogin()
                    dockerPush (env.IMAGE_NAME)
                }
            }
        }
        stage('DEPLOY') {
            steps {
                script {
                    echo 'deploying docker image to EC2...'
                    def dockerCmd = "docker run -d -p 8080:8080 ${IMAGE_NAME}"
                    sshagent(['ec2-server-key']) {
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@34.247.93.182 ${dockerCmd}"
                    }
                }
            }
        }
    }
}