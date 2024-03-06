pipeline {
    agent any
    environment{
        name = true
    }
    parameters {
        string(name: 'Greeting', defaultValue: '', description: 'Done the changes check once')
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
                   "$name" == true
                }
            }
            steps {
                echo 'This is compiling'
            }
        }
      stage('Run') {
            when{
                expression {
                   "${params.Greeting}" == Akil
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
