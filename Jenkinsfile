pipeline {
    agent any
    environment{
        name = true
    }
    stages {
        stage('Adding') {
            steps {
                echo 'This is starting'
            }
        }
      stage('Compile') {
            when{
                ${name} == false
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
