#!/usr/bin/env groovy
pipeline {
    agent any
    stages {
        stage("test") {
            steps {
                script {
                    echo "testing the app"
                }
            }
        }
        stage('build') {
            steps {
                script {
                    echo "building the app"
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                def dockerCmd = 'docker run -d -p 3080:3080 objectobjectlady/my-repo:1.0'
                    sshagent(['ec2-server-key']) {
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@34.247.93.182 ${dockerCmd}"
                    }
                }
            }
        }
    }
}