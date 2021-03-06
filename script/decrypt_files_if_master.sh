#!/usr/bin/env bash

if [ "master" = `git rev-parse --abbrev-ref HEAD` ]; then
    openssl aes-256-cbc -d -k "$SECRING_KEY" -in secring.gpg.enc -out secring.gpg
    openssl aes-256-cbc -d -k "$SETUP_KEY" -in setup.properties.enc -out setup.properties
fi
