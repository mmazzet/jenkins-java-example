#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    }
    environment {
        DOCKER_REPO_SERVER = '288842392988.dkr.ecr.eu-west-1.amazonaws.com'
        DOCKER_REPO = "${DOCKER_REPO_SERVER}/jenkins-java-example"
    }
    stages {
        stage('Increment version') {
            steps {
                script {
                    echo 'Incrementing App version...'

                    sh "mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit"
                    def matcher = readFile('pom.xml') =~ /<version>(.+)<\/version>/
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }
        stage('Build jar') {
            steps {
                script {
                    echo "Building App..."
                    sh 'mvn clean package'
                }
            }
        }
        stage('Build image') {
            steps {
                script {
                    echo "Building DOCKER image..."
                    withCredentials([usernamePassword(credentialsId: 'ecr-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh "docker build -t ${DOCKER_REPO}:${IMAGE_NAME} ."
                        sh "echo $PASS | docker login -u $USER --password-stdin ${DOCKER_REPO_SERVER}"
                        sh "docker push ${DOCKER_REPO}:${IMAGE_NAME}"
                    }
                }
            }
        }
        stage('deploy') {
            environment {
                AWS_ACCESS_KEY_ID = credentials('jenkins_aws_access_key_id')
                AWS_SECRET_ACCESS_KEY = credentials('jenkins_aws_secret_access_key')
                APP_NAME = 'jenkins-java-example'
            }
            steps {
                script {
                    echo "deploying the docker image"
                    sh 'aws eks update-kubeconfig --name demo-cluster --region eu-west-1'
                    sh 'envsubst < kubernetes/deployment.yaml | kubectl apply -f -'
                    sh 'envsubst < kubernetes/service.yaml | kubectl apply -f -'
                }
            }
        }
         stage('Commit Version Update'){
            steps {
                script {
                    echo "Testing no loop is triggered..."
                    withCredentials([usernamePassword(credentialsId: 'github-token', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'git config --global user.email "jenkins@example.com"'
                        sh 'git config --global user.name "jenkins"'
                        sh 'git status'
                        sh 'git branch'
                        sh 'git config --list'
                        sh "git remote set-url origin https://${USER}:${PASS}@github.com/mmazzet/jenkins-java-example.git"
                        sh 'git add .'
                        sh 'git commit -m "ci:version increment"'
                        sh 'git push origin HEAD:final_ci-cd_pipeline_using_ecr'
                    }
                }
            }
        }
    }
}