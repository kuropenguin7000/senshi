package com.dungeon.meshi.senshi.data.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configurable
@EnableJpaRepositories(basePackages = "com.dungeon.meshi.senshi.repository")
public class JpaConfig {
}
