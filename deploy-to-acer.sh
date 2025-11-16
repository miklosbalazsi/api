#!/bin/bash

# Variables
HOST=acer
DOCKER_COMPOSE_FILE=docker-compose.yml

# Step 1: Build and tag Docker images
docker compose build

# Step 2: Push images to Docker Hub (replace <username> with your Docker Hub username)
docker tag backend-service:latest miklosbalazsi/backend-service:latest
docker tag frontend-service:latest miklosbalazsi/frontend-service:latest
docker push miklosbalazsi/backend-service:latest
docker push miklosbalazsi/frontend-service:latest

# Step 3: Copy docker-compose.yml to the remote host
scp $DOCKER_COMPOSE_FILE $HOST:/home/$USER/

# Step 4: SSH into the remote host and start the services
ssh $HOST "docker compose -f /home/$USER/$DOCKER_COMPOSE_FILE pull && docker compose -f /home/$USER/$DOCKER_COMPOSE_FILE up -d"
