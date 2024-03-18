FROM node:14

WORKDIR /app/backend

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 3030

ENV MONGO_URI=mongodb://mongo:27017/COMP3123_Assignment1

CMD ["npm", "start"]

