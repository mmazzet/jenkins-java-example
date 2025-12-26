CODE_CHANGES = getGitChanges()
pipeline {
    agent any

    parameters {
        choice(name: 'VERSION', choices: ['1.1.1', '2.2.2', '3.3.3'], description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }


    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                echo "Deploying version ${params.VERSION}"

            }
        }
    }
}