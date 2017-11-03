node {
	def gradle = Artifactory.newGradleBuild()
	def info

	stage('Pull') { 
		checkout scm
	}
	
   	stage('Configuración') {
		gradle.tool = "Gradle-4.4"
   	}
	
	stage('Build'){
		gradle.run buildFile: 'build.gradle', tasks: 'clean', buildInfo: info		
	}

}
