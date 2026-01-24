#!/usr/bin/env groovy
library identifier: 'jenkins-shared-lib@mainline', retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/mmazzet/jenkins-shared-lib.git',
    credentialsId: 'github-token'
    ]
)

pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    }
    stages {
        stage('INCREMENT VERSION') {
            steps {
                script {
                    echo 'incrementing app version'
                    sh "mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit"
                    def matcher = readFile('pom.xml') =~ /<version>(.+)<\/version>/
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "objectobjectlady/jenkins-java-example:${version}-${BUILD_NUMBER}"
                }
            }
        }
        stage('BUILD JAR') {
            steps {
                script {
                echo 'building application jar'
                    buildJar()
                }
            }
        }
        stage('BUILD AND PUSH IMAGE') {
            steps {
                script {
                echo 'building docker image...'
                    buildImage (env.IMAGE_NAME)
                    dockerLogin()
                    dockerPush (env.IMAGE_NAME)
                }
            }
        }
        stage('DEPLOY') {
            steps {
                script {
                    echo 'deploying docker image to EC2...'

                    def sshOpts = "-o StrictHostKeyChecking=no"
                    def remote  = "ec2-user@34.247.93.182"

                    sshagent(['ec2-server-key']) {
                        sh """
                            scp ${sshOpts} server-cmds.sh ${remote}:/home/ec2-user
                            scp ${sshOpts} docker-compose.yaml ${remote}:/home/ec2-user
                            ssh ${sshOpts} ${remote} 'bash ./server-cmds.sh ${IMAGE_NAME}'
                        """
                    }
                }
            }
        }
        stage('Commit Version Update'){
            steps {
                script {
                    echo "Testing no loop is triggered..."
                    withCredentials([usernamePassword(credentialsId: 'github-token', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh """
                            git remote set-url origin https://${USER}:${PASS}@github.com/mmazzet/jenkins-java-example.git
                            git add .
                            git commit -m "ci:version increment"
                            git push origin HEAD:jenkins-shared-lib
                        """
                    }
                }
            }
        }
    }
}