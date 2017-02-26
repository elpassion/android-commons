#!/usr/bin/env bash

if [ "$TRAVIS_BRANCH" == "master" ]; then
    openssl aes-256-cbc -d -a -k "$secring_key" -in secring.gpg.enc -out secring.gpg
    openssl aes-256-cbc -d -a -k "$setup_key" -in setup.properties.enc -out setup.properties
fi
