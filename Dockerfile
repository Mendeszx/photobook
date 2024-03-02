FROM node:latest as build

WORKDIR /frontend

COPY . .

RUN npm install

RUN npm run build --prod

FROM nginx:latest

COPY --from=build /frontend/dist/frontend-photobook /usr/share/nginx/html

EXPOSE 80
