FROM node:lts-bullseye-slim
COPY . ./
ENV PUBLIC_URL=/
RUN npm run build
RUN npm install -g serve
EXPOSE 3000
CMD ["serve", "-s", "build"]
