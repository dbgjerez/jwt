node {
	def server = Artifactory.newServer
	def gradle = Artifactory.newGradleBuild()
	def info = Artifactory.newBuildInfo()

	stage('Pull') { 
		checkout scm
	}
	
   	stage('Build') {
		gradle.run rootDir: '', buildFile: 'build.gradle', tasks: 'clean build', buildInfo: info
   	}

}
