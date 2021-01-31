package com.cache.springbootignite;

import java.util.List;
import javax.cache.Cache;
import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import org.springframework.stereotype.Repository;

/*
 * This repository is associated with the Country table of ignite via the 
 * @RepositoryConfig (cacheName = "Country") annotation. 
 */
@RepositoryConfig (cacheName = "Country")
@Repository
public interface CountryRepository extends IgniteRepository<Country, String> {

    public List<Cache.Entry<String,Country>> findByPopulationGreaterThanEqualOrderByPopulationDesc(int population);
}