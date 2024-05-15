pipeline {
  agent any

  environment {
    MAVEN_ARGS=" -B -e -U"
  }

  stages {

      

        stage('OWASP Dependency-Check') {
            steps {
                dependencyCheck additionalArguments: '''
                            -o './'
                            -s './'
                            -f 'ALL'
                            --prettyPrint''', odcInstallation: 'OWASP-DP-CHECK'

                dependencyCheckPublisher pattern: 'dependency-check-report.xml'
            }
        }
 }
}  
