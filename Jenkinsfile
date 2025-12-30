#!/usr/bin/env groovy
@Library('jenkins-shared-lib')

def gv

pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage ("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage('build jar') {
            steps {
                script {
                    buildJar()
                }
            }
        }
        stage('build image') {
            steps {
                script {
                    buildImage()
                }
            }
        }
        stage('deploy - this will deploy using script.groovy') {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}