pipeline {
	//Donde se va a ejecutar el Pipeline
	agent {
		label 'Slave_Induccion'
	}

    //Opciones espec�ficas de Pipeline dentro del Pipeline
	options {
		buildDiscarder(logRotator(numToKeepStr: '3')) //N�mero maximo de ejecuciones a guardar
		disableConcurrentBuilds() //No permitir compilaciones simultaneas
	}

	//Una secci�n que define las herramientas "preinstaladas" en Jenkins
	tools {
		jdk 'JDK11_Centos' //Preinstalada en la Configuraci�n del Master
		gradle 'Gradle5.6_Centos' //Preinstalada en la Configuraci�n del Master
	}

    //Acciones autom�ticas
	triggers {
		pollSCM('@hourly') //Periodo de tiempo en el que revisa el repositorio para ejecutar pipeline
		                    //cada vez que encuentre un cambio
	}

    //Aqu� comienzan los "items" del Pipeline
	stages{
		stage('Checkout') {
			steps{
				echo "------------>Checkout<------------"
				checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [],
                    gitTool: 'Default',
                    submoduleCfg: [],
                    userRemoteConfigs: [[
                        credentialsId: 'GitHub_sky98',           
						url: 'https://github.com/sky98/mi-primer-titulo'
				    ]]
				])
				sh 'gradle --b ./ADN-cursos-back/java-arquitectura-hexagonal/microservicio/build.gradle clean' //Asegurar no tener datos basura de compilaciones anteriores
			}
		}

		stage('Compile') {
			steps{
				echo "------------>Compile<------------"
				sh 'gradle --b ./ADN-cursos-back/java-arquitectura-hexagonal/microservicio/build.gradle compileJava'
			}
		}

		stage('Unit Tests And Coverage') {
			steps{
				echo "------------>Unit Tests<------------"
				sh 'gradle --b ./ADN-cursos-back/java-arquitectura-hexagonal/microservicio/build.gradle test'
				junit '**/build/test-results/test/*.xml' //Agregar los resultados del test a Junit
			    sh 'gradle --b  ./ADN-cursos-back/java-arquitectura-hexagonal/microservicio/build.gradle jacocoTestReport'
			}
		}

		stage('Static Code Analysis') {
			steps{
				echo '------------>Static Code Analysis<------------'
				withSonarQubeEnv('Sonar') {
					sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
				}
			}
		}

		stage('Build') {
			steps {
				echo "------------>Build<------------"
                sh 'gradle --b  ./ADN-cursos-back/java-arquitectura-hexagonal/microservicio/build.gradle build -x test' //Construir sin tarea test que se ejecut� previamente
			}
		}
	}

	post {
		always {
			echo 'This will always run'
		}
		success {
			echo 'This will run only if successful'
		}
		failure {
			echo 'This will run only if failed'
			//send notifications about a Pipeline to an email
			mail (to: 'rober.sehuanez@ceiba.com.co',
			      subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
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