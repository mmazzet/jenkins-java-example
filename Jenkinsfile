def gv
pipeline {
    agent any

    parameters {
        choice(name: 'VERSION', choices: ['1.1.1', '2.2.2', '3.3.3'], description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }


    stages {
        stage('init') {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    gv.buildApp()
                }
            }
        }
        stage('Test') {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}