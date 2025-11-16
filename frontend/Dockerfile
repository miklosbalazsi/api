# Use an official Nginx image as a parent image
FROM nginx:alpine

# Set the working directory in the container
WORKDIR /usr/share/nginx/html

# Copy the built frontend application into the Nginx container
COPY dist/ .

# Add a custom Nginx configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose the port the application runs on
EXPOSE 80

# Nginx will automatically serve the static files

# Add a label to name the Docker image and version it
LABEL name="frontend-service" version="1.0.0"
