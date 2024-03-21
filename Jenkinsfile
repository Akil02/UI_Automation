pipeline {
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
                echo "PATH = ${M2_HOME}/bin:${PATH}"
                echo "M2_HOME = /opt/maven"
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
                bat 'mvn clean test'
            }
        }
    }
    post{
       always{
           echo 'This is completed'
       }
    }
}
