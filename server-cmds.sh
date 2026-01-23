#!/usr/bin/env bash

export IMAGE="$1"
echo "Deploying image: $IMAGE"
docker-compose -f docker-compose.yaml up -d
echo "operation successful"
