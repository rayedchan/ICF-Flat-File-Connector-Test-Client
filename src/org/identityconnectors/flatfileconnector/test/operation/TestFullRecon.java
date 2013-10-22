package org.identityconnectors.flatfileconnector.test.operation;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.identityconnectors.framework.api.APIConfiguration;
import org.identityconnectors.framework.api.ConfigurationProperties;
import org.identityconnectors.framework.api.ConnectorFacade;
import org.identityconnectors.framework.api.ConnectorFacadeFactory;
import org.identityconnectors.framework.api.ConnectorInfo;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.OperationOptions;
import org.identityconnectors.framework.common.objects.OperationOptionsBuilder;

public class TestFullRecon extends Test{
    public TestFullRecon() {
        super();
    }
    
    void search(){
        //get a list of ConnectorInfo from ConnectorInfoManager
        List<ConnectorInfo> cInfos = this.getCInfoManager().getConnectorInfos();
        //iterate through the list              
        for(ConnectorInfo cInfo:cInfos){   
            //get the APIconfig
            APIConfiguration apiConfig = cInfo.createDefaultAPIConfiguration();
        
            //set pool configurations
            setPoolConfigurations(apiConfig);
        
            //get the configProps i.e reference to Configuration on SPI side                        
            ConfigurationProperties configProps = apiConfig.getConfigurationProperties();
            
            //set up config props
            this.setUpConfigurationProperties(configProps);                     
            
            //get a reference to ConnectorFacadeFactory
            ConnectorFacadeFactory facadeFactory = ConnectorFacadeFactory.getInstance();
        
            //create the connector facade (nothing but reference to our Connector on SPI side)
            ConnectorFacade connectorFacade = facadeFactory.newInstance(apiConfig);
            
            //Create an empty operation options..
            //In the connector bundle executeQuery implementation, I am checking if there is no extra
            //options, I do full recon, so here I am sending empty options
            OperationOptionsBuilder optionBuilder = new OperationOptionsBuilder();
            Collection<String> c = new HashSet<String>();
            optionBuilder.setAttributesToGet(c);
            OperationOptions options = optionBuilder.build();
            connectorFacade.search(ObjectClass.ACCOUNT, null, new FlatFileResultHandler(), options);
        } 
    }
    
    public static void main(String[] args){
        new TestFullRecon().search();
    }
}
