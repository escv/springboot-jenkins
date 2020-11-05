pipeline {
    agent {
        docker {
            image 'gradle:jdk11'
            args '-p 3000:3000'
        }
    }
    environment {
        CI = 'true'
        HOME = '.'
        NEXUS_VERSION = "nexus3"
		NEXUS_PROTOCOL = "http"
		NEXUS_URL = "andres-mbp.fritz.box:8081"
		NEXUS_REPOSITORY = "jenkins-snapshot"
		NEXUS_CREDENTIAL_ID = "nexus-jenkins"
		GROUP_ID = "de.andre.springboot"
		ARTIFACT_ID = "springboot-jenkins"
    }
    stages {
        stage('Build') {
            steps {
                sh 'gradle build --no-daemon'
            }
        }

    }

}
