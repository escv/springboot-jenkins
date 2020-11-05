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
            archiveArtifacts artifacts: 'build/libs/*', fingerprint: true
        }
    }
}
