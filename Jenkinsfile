pipeline {
    agent any
    environment{
        name = 'Man'
    }
    parameters {
        string(name: 'Greeting', defaultValue: '', description: 'Enter the value')
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
            }
        }
    }
    post{
       always{
           echo 'This is completed'
       }
    }
}
