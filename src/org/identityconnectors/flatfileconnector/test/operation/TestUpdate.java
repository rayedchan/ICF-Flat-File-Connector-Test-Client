package org.identityconnectors.flatfileconnector.test.operation;
import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.flatfileconnector.test.common.BundleProperties;
import org.identityconnectors.framework.api.APIConfiguration;
import org.identityconnectors.framework.api.ConfigurationProperties;
import org.identityconnectors.framework.api.ConnectorFacade;
import org.identityconnectors.framework.api.ConnectorFacadeFactory;
import org.identityconnectors.framework.api.ConnectorInfo;
import org.identityconnectors.framework.api.ConnectorInfoManager;
import org.identityconnectors.framework.api.ConnectorInfoManagerFactory;
import org.identityconnectors.framework.api.RemoteFrameworkConnectionInfo;
import org.identityconnectors.framework.api.operations.APIOperation;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.AttributeBuilder;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.OperationOptions;
import org.identityconnectors.framework.common.objects.OperationOptionsBuilder;
import org.identityconnectors.framework.common.objects.ResultsHandler;
import org.identityconnectors.framework.common.objects.Uid;
import org.identityconnectors.framework.common.objects.filter.AndFilter;
import org.identityconnectors.framework.common.objects.filter.ContainsAllValuesFilter;
import org.identityconnectors.framework.common.objects.filter.Filter;
import org.identityconnectors.framework.common.objects.filter.NotFilter;
public class TestUpdate extends Test {
    public TestUpdate() {
        super();
    }    
    private void update(){
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
                
                //lets call the update operation on the facade... i.e update the user with account ID 1 to 10
                connectorFacade.update(ObjectClass.ACCOUNT, new Uid("1"), updateAttribute("10","FirstName"), null);
                
                //lets enable account ID 1
                connectorFacade.update(ObjectClass.ACCOUNT, new Uid("1"), enable("Enable","true"), null);
                
                //lets disable account ID 2
                connectorFacade.update(ObjectClass.ACCOUNT, new Uid("2"), enable("Enable","false"), null);
                
            } 
        }
    public static void main(String[] args){
        new TestUpdate().update();
    }
}
