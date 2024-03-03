FROM node:latest as build

WORKDIR /frontend

COPY . .

RUN npm install

RUN npm install -g @angular/cli

CMD ["ng", "serve", "--host", "0.0.0.0"]
