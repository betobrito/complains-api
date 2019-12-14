node {

    def jdkHome
    def mvnHome
    def gitBranch

    stage('Configure') {
        gitHome = git branch: 'master', credentialsId: 'betobrito', url: 'git@github.com:betobrito/complains-api.git'
        gitBranch = sh returnStdout: true, script: "git branch"
        jdkHome = tool 'JDK11'
        mvnHome = tool 'M3'
        env.PATH = "${jdkHome}/bin:${mvnHome}/bin:${env.PATH}"
    }

    if (gitBranch.contains('master')) {

        stage('Check env') {
            sh "java -version"
            sh "mvn -v"
        }

        stage('Build') {
            sh 'mvn -B -V -U -e clean package'
        }

        stage('Archive') {
            junit allowEmptyResults: true, testResults: '**/target/**/TEST*.xml'
        }
    }
}
