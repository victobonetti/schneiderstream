version: '3.7'
services:
  mysql:
    image: mysql:5.7
    container_name: mysql
    restart: always
    environment:
      MYSQL_DATABASE: SHNEIDERSTREAM
      MYSQL_USER: root
      MYSQL_PASSWORD: masterkey
    ports:
      - 3306:3306
    volumes:
      - ./data:/var/lib/mysql