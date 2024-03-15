pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'jdk-11.0.20'
    }
    environment{
        name = 'Man'
    }
    parameters {
        string(name: 'Greeting', defaultValue: '', description: 'Enter the value')
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
                echo 'This is starting'
            }
        }
      stage('Compile') {
            when{
                expression {
                   "$name" == 'Man'
                }
            }
            steps {
                echo 'This is compiling'
            }
        }
      stage('Run') {
            when{
                expression {
                   "${params.Greeting}" == 'Akil'
                }
            }
            steps {
                echo "This is checking parameter ${params.Greeting}"
                sh 'mvn clean test'
            }
        }
    }
    post{
       always{
           echo 'This is completed'
       }
    }
}
