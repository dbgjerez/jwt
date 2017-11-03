node {
	def server = Artifactory.server('ARTIFACTORY')
	def gradle = Artifactory.newGradleBuild()
	def info = Artifactory.newBuildInfo()

	stage('Pull') { 
		checkout scm
	}
	
	stage('Configuración') {
		gradle.useWrapper = true
	}

   	stage('Build') {
		gradle.run rootDir: '', buildFile: 'build.gradle', tasks: 'clean build', buildInfo: info
   	}

}
