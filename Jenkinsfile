pipeline {
    agent any
    environment{
        name = true
    }
    parameters {
        booleanParam(name: 'Greeting', defaultValue: true, description: 'How should I greet the boolean?')
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
                   "$name" == true && "$params.Greeting" == true
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
