#!/bin/bash

# Read the version from version.txt
VERSION=$(cat version.txt)

# Update the backend Dockerfile
sed -i "s/LABEL name=\"backend-service\" version=\"[^"]*\"/LABEL name=\"backend-service\" version=\"$VERSION\"/" backend/Dockerfile

# Update the frontend Dockerfile
sed -i "s/LABEL name=\"frontend-service\" version=\"[^"]*\"/LABEL name=\"frontend-service\" version=\"$VERSION\"/" frontend/Dockerfile

# Print a success message
echo "Updated Dockerfiles to version $VERSION"

