def gv
pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    }

    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage('Build jar') {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage('Build image') {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}