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
    }
    stages {
        stage('Build') {
            steps {
                sh 'gradle build --no-daemon'
            }
        }
        //stage('dist') {
        //    steps {
        //        sh 'npm run build'
        //    }
        //}
        //stage('docker') {
        //    agent {
        //        label 'master'
        //    }
        //    steps {
        //        sh 'docker build . -t angular-jenkins:latest'
        //        sh 'docker run -d -p 80:80 angular-jenkins:latest'
        //    }
        //}
    }
    post {
        always {
        	rtUpload (
			    serverId: 'local',
			    spec: '''{
			          "files": [
			            {
			              "pattern": "build/libs/*",
			              "target": "local/froggy-files/"
			            }
			         ]
			    }''',
			 
			    // Optional - Associate the uploaded files with the following custom build name and build number,
			    // as build artifacts.
			    // If not set, the files will be associated with the default build name and build number (i.e the
			    // the Jenkins job name and number).
			    buildName: 'holyFrog',
			    buildNumber: '42'
			)
            //archiveArtifacts artifacts: 'build/libs/*', fingerprint: true
        }
    }
}
