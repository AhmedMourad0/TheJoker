package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import inc.ahmedmourad.joker.Joker;

@Api(name = "myApi", version = "v1", namespace = @ApiNamespace(
		ownerDomain = "backend.builditbigger.gradle.udacity.com",
		ownerName = "backend.builditbigger.gradle.udacity.com"
))
public class Endpoint {
	@ApiMethod(name = "getJokeBean")
	public Bean getJokeBean() {
		return new Bean(Joker.getJoke());
	}
}
