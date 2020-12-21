FROM node:13.12.0-alpine as build
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json ./
COPY package-lock.json ./
RUN npm ci --silent
RUN npm install react-scripts@3.4.1 -g --silent
COPY . ./
RUN npm run build

# production environment
FROM nginx:stable-alpine
COPY --from=build /app/build /usr/share/nginx/html
COPY --from=build /app/env2js.sh /usr/share/nginx/

RUN apk add --no-cache bash
RUN chmod +x /usr/share/nginx/env2js.sh

CMD ["/bin/bash", "-c", "/usr/share/nginx/env2js.sh && nginx -g \"daemon off;\""]
#CMD ["nginx", "-g", "daemon off;"]