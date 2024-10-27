# Użycie ARG do ustawienia obrazu bazowego
ARG IMAGE_NAME
FROM ${IMAGE_NAME}

# Ustaw katalog roboczy
WORKDIR /app

# Skopiuj pliki projektu do obrazu
COPY . .

# Zainstaluj zależności i zbuduj aplikację
RUN npm install && npm run build

# Użyj prostego serwera do serwowania aplikacji
RUN npm install -g serve

# Expose port
EXPOSE 7010

# Uruchom serwer aplikacji na porcie 7010
CMD ["serve", "-s", "dist", "-l", "7010"]
