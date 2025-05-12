pipeline{
    agent any
    tools{
        maven 'MAVEN_3_9_9'
        jdk 'JDK_17'
    }
    stages{
        stage('Compile Stage'){
            steps{
                echo 'Compiling the code...'
                withMaven(maven: 'MAVEN_3_9_9'){
                    script{
                        bat 'mvn clean compile'
                    }
                }
            }
        }
        stage('Testing Stage'){
            steps{
                echo 'Running tests...'
                withMaven(maven: 'MAVEN_3_9_9'){
                    script{
                        bat 'mvn test'
                    }
                }
            }
        }
        stage('Package Stage'){
            steps{
                echo 'Packaging the application...'
                withMaven(maven: 'MAVEN_3_9_9'){
                    script{
                        bat 'mvn package'
                    }
                }
            }
        }
    } 
}