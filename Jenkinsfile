pipeline {   	
	//Donde se va a ejecutar el pipeline
	agent{
		label 'Slave_Induccion'
	}
	
	//Opciones especificas de pipelines dentro del pipeline
	options {
		//Mantener artefactos y salida de consola para el # especifico de ejecuciones recientes del Pipeline.
		buildDiscarder(logRotator(numToKeepStr: '3'))
		//No permitir ejecuciones concurrentes de Pipeline
		disableConcurrentBuilds()
	}

	//Una seccion que define las herramientas “preinstaladas” en Jenkins
 	tools {
	 	jdk 'JDK8_Centos' //Preinstalada en la Configuracion del Master
	 	gradle 'Gradle4.5_Centos' //Preinstalada en la Configuracion del Master
	 }

 	//Aqui comienzan los items del Pipeline
 	stages{

 		stage('Checkout') {
	 		steps{
	 			echo "------------>Checkout<------------"
	 			
	 			checkout([$class: 'GitSCM',
	 			branches: [[name: '*/master']],
	 			doGenerateSubmoduleConfigurations: false,
	 			extensions: [],
	 			gitTool: 'Git_Centos', submoduleCfg: [],
	 			userRemoteConfigs: [[credentialsId:'GitHub_james077', url:'https://github.com/james077/ADNCeiba.git']]])
	 			
	 		}
 		}

 		stage('clean') {
			steps{
				echo "------------>Clean<------------"
				sh 'gradle --b ./build.gradle clean'
			}
		}
 		
 		stage('Compile') {
			steps{
				echo "------------>Compile<------------"
				sh 'gradle --b ./build.gradle compileJava'
			}
		}
		stage('Unit Tests') {
			steps{
				echo "------------>Unit Tests<------------"
				sh 'gradle --b ./build.gradle test'
			}
		}
		stage('Integration Tests') {
			steps {
				echo "------------>Integration Tests<------------"
				
			}
		}
		stage('Jacoco Reports') {
			steps {
			  echo "------------>Jacoco Reports<------------"
			  sh 'gradle --b ./build.gradle jacocoTestReport'
			}
		}
		stage('Static Code Analysis') {
	      steps{
	        echo '------------>Análisis de código estático<------------'
	        withSonarQubeEnv('Sonar') {
	          sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
	        }
	      }
	    }
		stage('Build') {
			steps {
				echo "------------>Build<------------"
				sh 'gradle --b ./build.gradle build -x test'
			}
		}
	}
	post {
		always {
			echo 'This will always run'
		}
		success {
			echo 'This will run only if successful'
			junit '**/build/test-results/test/*.xml'
		}
 		failure {
 			echo 'This will run only if failed'
 			mail (to: 'james.martinez@ceiba.com.co',subject: "Failed Pipeline:${currentBuild.fullDisplayName}",
			body: "Something is wrong with ${env.BUILD_URL}")
 		}
 		unstable {
 			echo 'This will run only if the run was marked as unstable'
 		}
		 changed {
		 	echo 'This will run only if the state of the Pipeline has changed'
		 	echo 'For example, if the Pipeline was previously failing but is now successful'
		 }
	}


}