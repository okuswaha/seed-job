pipelineJob('pipeline from script') {
    definition {
        cps {
            script('''
                node {
                   def mvnHome
                   stage('Preparation') {
                       echo 'Getting Project from Git'
                       git 'https://github.com/okuswaha/struts13'
                       echo 'successfully pulled project from git'
                     
                   }
                   stage('Build') {
                       echo ' building using maven'
                       //sh 'mvn clean install'
                      
                   }
                   stage('Results') {
                        def fileName = 'words.yaml'
                        if(fileExists(fileName)) {
                            def yaml = readYaml file:fileName
                            println yaml.containers
                            println yaml.containers.images
                            println yaml.containers.images[0].imageName
                            
                            yaml.containers.images.eachWithIndex { repo , i -> println "$i : " + repo.imageName }
                        }
                        
                       
                   }
                }
            ''')
            sandbox()
        }
    }
}
