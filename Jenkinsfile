properties([pipelineTriggers([githubPush()])])

pipeline {
    environment {
        def CURRENT_PENDING_STAGE = ''
    }

    agent {
        label 'android'
    }

    stages {
        stage('Build') {
            steps {
                script { CURRENT_PENDING_STAGE = STAGE_NAME }
                notifyGithub(CURRENT_PENDING_STAGE, 'In progress..','PENDING')

                echo 'Cloning repository..'
                git url: 'https://github.com/imbactbulletz/favorite-places'

                dir("app/src/main/res/values/") {
                    echo 'Copying necessary build resources.'
                    sh 'cp ~/api_keys.xml .'
                }

                echo 'Performing build..'
                sh 'chmod +x gradlew'
                sh './gradlew clean build'

                notifyGithub(CURRENT_PENDING_STAGE,'Passed!','SUCCESS')
            }
        }

        stage('Code Inspection') {
            steps {
                script { CURRENT_PENDING_STAGE = STAGE_NAME }
                notifyGithub(CURRENT_PENDING_STAGE, 'In progress..','PENDING')

                echo 'Performing Android lint..'
                sh './gradlew lint'

                echo 'Performing Code Style lint'
                sh './gradlew ktlint'

                recordIssues enabledForFailure: true, tools: [androidLintParser(pattern: "**/lint-results.xml"), ktlint(pattern: "**/reports/ktlint/*.xml")], qualityGates: [[threshold:1, type: 'TOTAL', unstable: false]]
                notifyGithub(CURRENT_PENDING_STAGE,'Passed!','SUCCESS')
            }
        }
    }

    post {
        always {
            deleteDir()
        }

        failure {
            notifyGithub(CURRENT_PENDING_STAGE, 'Failed.', 'FAILURE')
        }
    }
}

def notifyGithub(context, message, status) {
    githubNotify account: 'imbactbulletz', credentialsId: 'imbactbulletz-github-login',
        context: context, description: message, repo: 'favorite-places', sha: env.GIT_COMMIT,
        status: status
}
