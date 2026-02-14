#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('build app') {
            steps {
                script {
                    echo "building the app"
                }
            }
        }
        stage('build image') {
             steps {
                 script {
                    echo "building the docker image"
             }
        }
     }
        stage('deploy') {
            steps {
                script {
                    echo "deploying the docker image"
                    withKubeConfig([credentialsId: 'lke-credentials', serverUrl: 'https://fb899627-e4c7-40f8-954f-01aa102227d9.uk-lon-1-gw.linodelke.net']) {
                        sh 'kubectl create deployment nginx-deployment --image=nginx'
                    }
                }
            }
        }
    }
}