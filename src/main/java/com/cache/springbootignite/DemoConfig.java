package com.cache.springbootignite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.communication.tcp.TcpCommunicationSpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.apache.ignite.springdata22.repository.config.EnableIgniteRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableIgniteRepositories
public class DemoConfig {
    @Bean
    public Ignite igniteInstance() {
        IgniteConfiguration cfg = new IgniteConfiguration();

        cfg.setClientMode(true);

        return Ignition.start(cfg);
    }
    
//    @Bean
    public Ignite igniteInstance2() {
        IgniteConfiguration cfg = new IgniteConfiguration();

        // Setting some custom name for the node.
        cfg.setIgniteInstanceName("springDataNode");

        // Enabling peer-class loading feature.
        cfg.setPeerClassLoadingEnabled(true);

        // Defining and creating a new cache to be used by Ignite Spring Data
        // repository.
        CacheConfiguration ccfg = new CacheConfiguration("Country");

        // Setting SQL schema for the cache.
        ccfg.setIndexedTypes(Long.class, Country.class);
        

        // Setting SQL schema for the cache.
        ccfg.setIndexedTypes(Long.class, Country.class);

        cfg.setCacheConfiguration(ccfg);

        return Ignition.start(cfg);
    }
    
//    @Bean(name = "igniteConfiguration")
    public IgniteConfiguration igniteConfiguration() {
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setIgniteInstanceName("testIgniteInstance");
        //igniteConfiguration.setClientMode(true);
        igniteConfiguration.setPeerClassLoadingEnabled(true);
        igniteConfiguration.setLocalHost("127.0.0.1");

        TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"));
        tcpDiscoverySpi.setIpFinder(ipFinder);
        tcpDiscoverySpi.setLocalPort(47500);
        // Changing local port range. This is an optional action.
        tcpDiscoverySpi.setLocalPortRange(9);
        //tcpDiscoverySpi.setLocalAddress("localhost");
        igniteConfiguration.setDiscoverySpi(tcpDiscoverySpi);

        TcpCommunicationSpi communicationSpi = new TcpCommunicationSpi();
        communicationSpi.setLocalAddress("localhost");
        communicationSpi.setLocalPort(48100);
        communicationSpi.setSlowClientQueueLimit(1000);
        igniteConfiguration.setCommunicationSpi(communicationSpi);

        igniteConfiguration.setCacheConfiguration(cacheConfiguration());

        return igniteConfiguration;

    }
    
//    @Bean(name = "cacheConfiguration")
    public CacheConfiguration[] cacheConfiguration() {
        List<CacheConfiguration> cacheConfigurations = new ArrayList<>();           
            // Defining and creating a new cache to be used by Ignite Spring Data
            // repository.
        
            CacheConfiguration ccfg = new CacheConfiguration("Country");
            // Setting SQL schema for the cache.
            ccfg.setIndexedTypes(Long.class, Country.class);
            
           
            // Setting SQL schema for the cache.
            ccfg.setIndexedTypes(Long.class, Country.class);

            

        return cacheConfigurations.toArray(new CacheConfiguration[cacheConfigurations.size()]);
    }
}