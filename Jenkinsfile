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
            input {
                message "Select the deployment environment: "
                ok "Confirm"
                parameters {
                    choice(name: 'ENVIRONMENT-ONE', choices: ['development', 'staging', 'production', 'teapot'], description: 'Test description env-one')
                    choice(name: 'ENVIRONMENT-TWO', choices: ['development', 'staging', 'production', 'teapot'], description: 'Test description env-two')
                }
            }
            steps {
                script {
                    gv.deployApp()
                    echo "Deploying to ${ENVIRONMENT-ONE}"
                    echo "Deploying to ${ENVIRONMENT-TWO}"
                }
            }
        }
    }
}