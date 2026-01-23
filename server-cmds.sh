#!/usr/bin/env bash

docker-compose -f docker-compose.yaml up -d
echo "operation successful"
export TEST=testvalue