ICF Flat File Connector Test Client
===================================
This is a stand-alone java client to test the ICF Flat File Connector. This directly communicates with the Java Connector Server. Make sure the ICF Flat File Connector jar file is in your Java Connector Server "bundles" directory.  
  
org.identityconnectors.flatfileconnector.test.operation.Test:  
Update setupConnectorServer() (connectorServerhost, connectorServerPort, connectorServerKey) and setUpConfigurationProperties() (targetFile, lookupReconFile) with appropriate values. Make sure you created the output.txt file.  
  
Run main methods to test the connector.  