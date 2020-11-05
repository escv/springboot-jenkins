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
                        echo "*** ${NEXUS_VERSION} File: ${artifactPath}, group: ${GROUP_ID}, artifact ${ARTIFACT_ID}";
​
                        nexusArtifactUploader(
                            nexusVersion: 'nexus3',
                            protocol: 'http',
                            nexusUrl: 'andres-mbp.fritz.box:8081',
                            groupId: 'de.andre.springboot',
                            version: '0.0.1-SNAPSHOT',
                            repository: 'jenkins-snapshot',
                            credentialsId: 'nexus-jenkins',
                            artifacts: [
                                [artifactId: 'springboot-jenkins',
                                classifier: '',
                                file: 'build/libs/springboot-jenkins.jar',
                                type: 'jar']
                            ]
                        )
                        echo "Published"
​
                    } else {
                        echo "Oje";
                    }
                }
            }
        }
    }

}
