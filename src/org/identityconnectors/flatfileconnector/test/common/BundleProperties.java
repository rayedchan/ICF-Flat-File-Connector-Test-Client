package org.identityconnectors.flatfileconnector.test.common;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.identityconnectors.common.IOUtil;
import org.identityconnectors.framework.common.exceptions.ConnectorException;

public class BundleProperties {
    
    final String dirLocation = "D:\\MyData\\OracleGeneral\\ICF\\connector_server_java-1.2.6195\\connector_server_java-1.2.6195\\bundles";
    final String jarName = "org.identityconnectors.genericunix.GenericUnixConnector-1.0";    
    final String jarRelativeUrl = jarName + ".jar";
    final String connectorClassName = "org.identityconnectors.genericunix.GenericUnixConnector";

    File dir = new File(dirLocation);
    URL url;

    public BundleProperties() {
        try {
            url = IOUtil.makeURL(dir, jarRelativeUrl);
        } catch (final IOException ioe) {
            System.out.println("URL not framed properly");
            ioe.printStackTrace();
            throw ConnectorException.wrap(ioe);
        }
    }

    public String getDirLocation() {
        return dirLocation;
    }



    public String getJarName() {
        return jarName;
    }

    public String getJarRelativeUrl() {
        return jarRelativeUrl;
    }

    public String getConnectorClassName() {
        return connectorClassName;
    }

    public File getDir() {
        return dir;
    }

    public URL getUrl() {
        return url;
    }

}
