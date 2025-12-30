#!/usr/bin/env groovy
@Library('jenkins-shared-lib')

def gv

pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    }
    stages {
        stage ("INIT") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage('BUILD JAR') {
            steps {
                script {
                    buildJar()
                }
            }
        }
        stage('BUILD IMAGE') {
            steps {
                script {
                    buildImage()
                }
            }
        }
        stage('DEPLOY') {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}