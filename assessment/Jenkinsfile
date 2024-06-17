pipeline{
stage('Unit Test') {
           agent {
               docker {
                  image 'maven:3.6.3-openjdk-11'
                   args '-v $HOME/.m2:/root/.m2 --entrypoint='
               }
           }
           steps {
               script {
                   FAILED_STAGE=env.STAGE_NAME
                   echo "Unit Test "
          configFileProvider([configFile(fileId: 'cb7044ed-a4cb-4e78-94ed-439911540544', variable: 'ARTIFACTORY_CRED')]) {
                   sh '''
                   mvn -s "${ARTIFACTORY_CRED}" test
           '''}
               }
           }
       }
        stage('Code Compile') {
            agent {
                docker {
                    image 'maven:3.6.3-openjdk-11'
                    args '-v $HOME/.m2:/root/.m2 --entrypoint='
                }
            }
            steps {
                script {
                    FAILED_STAGE=env.STAGE_NAME
                   echo "Code Compile stage"
         configFileProvider([configFile(fileId: '', variable: 'ARTIFACTORY_CRED')]) {
                        sh '''
                   mvn -s "${ARTIFACTORY_CRED}" clean package -Dmaven.test.skip=false
          '''}
                   stash name: "service-jar", includes: "target/*.jar"
                }
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    FAILED_STAGE=env.STAGE_NAME
                   echo "Docker image build"
                    unstash "service-jar"
          docker.withRegistry("${REG_NAME}", 'creds'){
         def customImage = docker.build("${SERVICE_NAME}:${PROFILE}-${BUILD_NUMBER}")
              customImage.push()
                   }
                }
            }
        }
        stage("Push image to facets") {
            sh docker push --
        }

}