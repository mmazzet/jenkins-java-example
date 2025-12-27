def buildJar() {
    echo "Building App..."
    sh 'mvn package'
}

def buildImage() {
    echo "Building DOCKER image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t objectobjectlady/jenkins-java-example:jje-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push objectobjectlady/jenkins-java-example:jje-2.0'
    }
}

def deployApp() {
    echo "Deploying App..."

}

return this