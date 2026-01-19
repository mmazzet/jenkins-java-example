
pipeline {
    agent any
    stages {
        stage("test") {
            steps {
                script {
                    echo "testing the app"
                    echo "executing the pipeline for branch $BRANCH_NAME"
                    echo "Testing multibranch autobuild"
                }
            }
        }
        stage('build') {
            when {
                expression {
                    BRANCH_NAME == 'mainline'
                }
            }
            steps {
                script {
                    echo "building the app"
                }
            }
        }
        stage('deploy') {

            when {
                expression {
                    BRANCH_NAME == 'mainline'
                }
            }
            steps {
                script {
                    echo "deploying the app"
                }
            }
        }
    }
}