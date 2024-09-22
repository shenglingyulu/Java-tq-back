package com.fczx.tqsandbox.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;

public class dockerdemo {
    public static void main(String[] args) {
        DockerClient dockerClient= DockerClientBuilder.getInstance().build();

    }
}
