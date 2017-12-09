node {

    stage ("Build") {
        echo '*** Build Starting ***'
        openshiftBuild apiURL: 'https://openshift.default.svc.cluster.local', authToken: '', bldCfg: 'hello-nodejs', buildName: '', checkForTriggeredDeployments: 'false', commitID: '', namespace: '', showBuildLogs: 'false', verbose: 'false', waitTime: ''
        openshiftVerifyBuild apiURL: 'https://openshift.default.svc.cluster.local', authToken: '', bldCfg: 'hello-nodejs', checkForTriggeredDeployments: 'false', namespace: '', verbose: 'false'
        echo '*** Build Complete ***'
    }
    stage ("Deploy") {
        echo '*** Deployment Starting ***'
        openshiftDeploy apiURL: 'https://openshift.default.svc.cluster.local', authToken: '', depCfg: 'hello-nodejs', namespace: '', verbose: 'false', waitTime: ''
        openshiftVerifyDeployment apiURL: 'https://openshift.default.svc.cluster.local', authToken: '', depCfg: 'hello-nodejs', namespace: '', replicaCount: '1', verbose: 'false', verifyReplicaCount: 'false', waitTime: ''
        echo '*** Deployment Complete ***'
    }
    stage ("Verify") {
        echo '*** Service Verification Starting ***'
        openshiftVerifyService apiURL: 'https://openshift.default.svc.cluster.local', authToken: '', namespace: '', svcName: 'hello-nodejs', verbose: 'false'
        echo '*** Service Verification Complete ***'
    }
}
