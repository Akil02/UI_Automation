pipeline {
    agent any
    environment{
        name = true
    }
    parameters {
        string(name: 'Greeting', defaultValue: 'Hello', description: 'How should I greet the world?')
    }
    stages {
        stage('Adding') {
            steps {
                echo 'This is starting'
            }
        }
      stage('Compile') {
            when{
                expression {
                   "$name" == true && "${params.Greeting}" == 'Hello'
                }
            }
            steps {
                echo 'This is compiling'
            }
        }
      stage('Run') {
            steps {
                echo 'This is running'
            }
        }
    }
    post{
       always{
           echo 'This is completed'
       }
    }
}
