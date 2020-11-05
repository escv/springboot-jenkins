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

         stage("publish to nexus") {
            steps {
                script {
                    // Rhttps://dzone.com/articles/jenkins-publish-maven-artifacts-to-nexus-oss-using

                    filesByGlob = findFiles(glob: "build/libs/*.jar");
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${GROUP_ID}, artifact ${ARTIFACT_ID}";
​
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: 'de.andre.springboot',
                            version: '0.0.1-SNAPSHOT',
                            repository: 'nexus-jenkins',
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: ARTIFACT_ID,
                                classifier: '',
                                file: artifactPath,
                                type: 'jar']
                            ]
                        );
​
                    } else {
                        error "*** File: could not be found";
                    }
                }
            }
        }
    }

}
